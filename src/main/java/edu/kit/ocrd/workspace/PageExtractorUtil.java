/*
 * Copyright 2018 Karlsruhe Institute of Technology.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.kit.ocrd.workspace;

import edu.kit.ocrd.workspace.entity.TextRegion;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.fzk.tools.xml.JaxenUtil;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility handling METS document.
 */
public class PageExtractorUtil extends PageUtil {

  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(PageExtractorUtil.class);

  /**
   * Extract text equivalents from text regions of PAGE document.
   *
   * @param metsFile Mets file
   * @param resourceId Resource ID of METS document.
   *
   * @return List with all found files.
   */
  public static List<TextRegion> extractAllTextRegions(final File metsFile, String resourceId) throws Exception {
    List<TextRegion> textRegions = new ArrayList<>();
    Document metsDocument = JaxenUtil.getDocument(metsFile);
    List<String> pageUrls = MetsDocumentUtil.extractPageUrls(metsDocument);
    for (String pageUrl : pageUrls) {
      if (pageUrl.startsWith("http://")) {
        // ignore 
      } else {
        textRegions.addAll(PageExtractorUtil.extractTextRegions(metsFile.toPath().getParent(), pageUrl, resourceId));
      }
    }
    return textRegions;
  }

  /**
   * Extract text equivalents from text regions of PAGE document.
   *
   * @param basePath Path to workspace.
   * @param pageUrl 'URL' of page file (relative path to base path).
   * @param resourceId Resource ID of METS document.
   *
   * @return List with all found files.
   */
  public static List<TextRegion> extractTextRegions(final Path basePath, final String pageUrl, final String resourceId) throws Exception {
    List<TextRegion> textRegions = new ArrayList<>();
    File pageFile = Paths.get(basePath.toString(), pageUrl).toFile();
    Document pageDocument = JaxenUtil.getDocument(pageFile);
    String pageNamespace = XmlUtil.getNamespace(pageDocument);

    Namespace[] namespaces = {Namespace.getNamespace("page", pageNamespace)};
    String imageUrl = JaxenUtil.getValues(pageDocument, PageUtil.IMAGE_PATH, namespaces)[0];
    List textRegionNodes = JaxenUtil.getNodes(pageDocument, "//page:Page/page:TextRegion", namespaces);
    for (Object textRegionNode : textRegionNodes) {
      Element textRegionElement = (Element) textRegionNode;
      String regionId = JaxenUtil.getAttributeValue(textRegionElement, "./@id");
      String order = JaxenUtil.getAttributeValue(pageDocument, "//page:ReadingOrder/page:OrderedGroup/page:RegionRefIndexed[@regionRef='" + regionId + "']/@index", namespaces);
      String[] confidenceLevels = JaxenUtil.getAttributesValues(textRegionElement, "./page:TextEquiv/@conf", namespaces);
      String confidenceLevel = "1.0";
      if (confidenceLevels.length > 0) {
        confidenceLevel = confidenceLevels[0];
      }
      String text = "";
      String[] texts = JaxenUtil.getNodesValues(textRegionElement, "./page:TextEquiv/page:Unicode", namespaces);
      if (texts.length > 0) {
        text = texts[0];
      }
      if (!text.trim().isEmpty()) {
        TextRegion textRegion = new TextRegion();
        textRegion.setResourceId(resourceId);
        textRegion.setPageUrl(pageUrl);
        textRegion.setImageUrl(imageUrl);
        textRegion.setOrder(order);
        textRegion.setRegion(regionId);
        textRegion.setConfidence(Float.parseFloat(confidenceLevel));
        textRegion.setText(text);
        textRegions.add(textRegion);
        System.out.println(textRegion);
      } 
    }
    return textRegions;
  }
}
