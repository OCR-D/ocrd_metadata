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
package edu.kit.ocrd.workspace.provenance;

import edu.kit.ocrd.workspace.MetsUtil;
import edu.kit.ocrd.workspace.entity.ProvenanceMetadata;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.fzk.tools.xml.JaxenUtil;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility handling METS document.
 */
public class ProvenanceUtil {

  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(ProvenanceUtil.class);
  /**
   * Namespaces used inside METS documents.
   */
  private static final Namespace[] namespaces = {
    Namespace.getNamespace("prov", "http://www.w3.org/ns/prov#"),
    Namespace.getNamespace("ocrd", "http://www.ocr-d.de")
  };

  private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
  /**
   * Mets document.
   */
  private Document metsDocument;
  /**
   * Provenance document.
   */
  private Document provDocument;
  /**
   * All activies of type 'ocrd:processor'.
   */
  Map<String, ProvenanceActivity> processorActivities;
  /**
   * All entities of type 'ocrd:mets__referencedFile'.
   */
  Map<String, ProvenanceEntity> referencedFileEntities;
  /**
   * All entities of type 'ocrd:parameter__file'.
   */
  Map<String, ProvenanceEntity> parameterFileEntities;

  private ProvenanceUtil(final Document provDocument, final Document metsDocument) {
    this.provDocument = provDocument;
    this.metsDocument = metsDocument;
    processorActivities = extractActivities("ocrd:processor");
    referencedFileEntities = extractEntities("ocrd:mets__referencedFileGroup");
    parameterFileEntities = extractEntities("ocrd:parameter__file");
    List<String> allFileIds = new ArrayList<>();
    for (ProvenanceEntity item : referencedFileEntities.values()) {
      allFileIds.add(item.getValue());
    }
  }

  /**
   * Extract provenance metadata from provenance and METS document.
   *
   * @param provDocument Provenance document.
   * @param metsDocument METS document.
   * @param resourceId Resource ID of METS document.
   *
   * @return List with all found files.
   */
  public static List<ProvenanceMetadata> extractWorkflows(final Document provDocument, final Document metsDocument, final String resourceId) throws Exception {
    LOGGER.info("Extract provenance metadata from METS ocrd_provenance.xml");
    List<ProvenanceMetadata> provenanceMetadataList = new ArrayList<>();
    ProvenanceUtil provUtil = new ProvenanceUtil(provDocument, metsDocument);
    Map<String, ProvenanceActivity> extractWorkflows = provUtil.extractActivities("ocrd:workflow");
    ProvenanceMetadata provenanceMetadata = new ProvenanceMetadata();
    for (ProvenanceActivity workflow : extractWorkflows.values()) {
      provenanceMetadata.setResourceId(resourceId);
      provenanceMetadata.setWorkflowId(workflow.getId());
      provenanceMetadata.setStartWorkflow(workflow.getStartDate());
      provenanceMetadata.setDurationWorkflow(provUtil.getDuration(workflow.getStartDate(), workflow.getEndDate()));

      provenanceMetadataList.addAll(provUtil.extractProcessorsForWorkflow(provenanceMetadata));
    }

    return provenanceMetadataList;
  }

  /**
   * Extract processor metadata from provenance document for given workflow.
   *
   * @param provMetadata provenance metadata of workflow.
   * @return List with all found files.
   */
  private List<ProvenanceMetadata> extractProcessorsForWorkflow(final ProvenanceMetadata provMetadata) {
    LOGGER.info("Extract processor metadata from METS ocrd_provenance.xml");
    List<ProvenanceMetadata> provenanceMetadata = new ArrayList<>();
    String[] processorIds = JaxenUtil.getAttributesValues(provDocument, "//prov:wasStartedBy/prov:activity[@prov:ref='" + provMetadata.getWorkflowId() + "']/../prov:trigger/@prov:ref", namespaces);
    for (String id : processorIds) {
      ProvenanceActivity processor = processorActivities.get(id);
      ProvenanceMetadata processorMetadata = (ProvenanceMetadata) provMetadata.clone();
      processorMetadata.setProcessorLabel(processor.getLabel());
      processorMetadata.setStartProcessor(processor.getStartDate());
      processorMetadata.setDurationProcessor(getDuration(processor.getStartDate(), processor.getEndDate()));
      // Add content of parameter file if available
      for (String parameterId : parameterFileEntities.keySet()) {
        List parameterNodeList = JaxenUtil.getNodes(provDocument, "//prov:used[./prov:activity/@prov:ref='" + id + "' and "
                + "./prov:entity/@prov:ref='" + parameterId + "']", namespaces);
        if (!parameterNodeList.isEmpty()) {
          processorMetadata.setParameterFile(parameterFileEntities.get(parameterId).getValue());
          // only one parameter file per processor.
          break;
        }
      }
      // Add input filegrps 
      String[] usedIds = JaxenUtil.getAttributesValues(provDocument, "//prov:used[./prov:activity/@prov:ref='" + id + "']/prov:entity/@prov:ref", namespaces);
      Set<String> inputGroups = new HashSet<>();
      for (String fileId : usedIds) {
        ProvenanceEntity referencedFile = referencedFileEntities.get(fileId);
        if (referencedFile != null) {
          inputGroups.add(referencedFile.getValue());
        }
      }
      processorMetadata.setInputFileGrps(inputGroups.toString());
      // Add output filegrps 
      String[] wasGeneratedIds = JaxenUtil.getAttributesValues(provDocument, "//prov:wasGeneratedBy[./prov:activity/@prov:ref='" + id + "']/prov:entity/@prov:ref", namespaces);
      Set<String> outputGroups = new HashSet<>();
      for (String fileId : wasGeneratedIds) {
        ProvenanceEntity referencedFile = referencedFileEntities.get(fileId);
        if (referencedFile != null) {
          outputGroups.add(referencedFile.getValue());
        }
      }
      processorMetadata.setOutputFileGrps(outputGroups.toString());
      provenanceMetadata.add(processorMetadata);
    }
    return provenanceMetadata;
  }

  /**
   * Extract all activities of a given type from document.
   *
   * @param type Type of activity.
   *
   * @return Map with all found activities.
   */
  private Map<String, ProvenanceActivity> extractActivities(final String type) {
    LOGGER.info("Extract activities from METS ocrd_provenance.xml");
    Map<String, ProvenanceActivity> activityList = new HashMap<>();
    List nodes = JaxenUtil.getNodes(provDocument, "//prov:type[text()='" + type + "']/../.", namespaces);
    for (Object activityNode : nodes) {
      Element fileGrpElement = (Element) activityNode;
      String id = JaxenUtil.getAttributeValue(fileGrpElement, "./@prov:id", namespaces);
      String startTime = JaxenUtil.getNodeValue(fileGrpElement, "./prov:startTime", namespaces);
      String endTime = JaxenUtil.getNodeValue(fileGrpElement, "./prov:endTime", namespaces);
      String label = JaxenUtil.getNodeValue(fileGrpElement, "./prov:label", namespaces);
      Date startWorkflow = null;
      Date endWorkflow = null;
      try {
        startWorkflow = sdf.parse(startTime.replaceAll("Z$", "+0000"));
        endWorkflow = sdf.parse(endTime.replaceAll("Z$", "+0000"));
      } catch (ParseException ex) {
        LOGGER.error("Error parsing date!", ex);
      }
      ProvenanceActivity activity = new ProvenanceActivity();
      activity.setId(id);
      activity.setType(type);
      activity.setLabel(label);
      activity.setStartDate(startWorkflow);
      activity.setEndDate(endWorkflow);
      activityList.put(id, activity);
    }
    return activityList;
  }

  /**
   * Extract all entities of a given type from document.
   *
   * @param type Type of entity.
   *
   * @return Map with all found entities.
   */
  private Map<String, ProvenanceEntity> extractEntities(final String type) {
    LOGGER.info("Extract entities from METS ocrd_provenance.xml");
    Map<String, ProvenanceEntity> entityList = new HashMap<>();
    List nodes = JaxenUtil.getNodes(provDocument, "//prov:type[text()='" + type + "']/../.", namespaces);
    for (Object entityNode : nodes) {
      Element fileGrpElement = (Element) entityNode;
      String id = JaxenUtil.getAttributeValue(fileGrpElement, "./@prov:id", namespaces);
      String value = JaxenUtil.getNodeValue(fileGrpElement, "./prov:value", namespaces);
      ProvenanceEntity entity = new ProvenanceEntity();
      entity.setId(id);
      entity.setType(type);
      entity.setValue(value);
      entityList.put(id, entity);
    }
    return entityList;
  }

  /**
   * Create map linking all fileIDs to its file group.
   *
   * @param fileIdList List with all fileIDs.
   *
   * @return Mapping of file ID to file group.
   */
  private Map<String, String> createMapID2Group(final List<String> fileIdList) {
    LOGGER.info("Create mapping fileID to file group from METS ocrd_provenance.xml");
    Map<String, String> fileMap = new HashMap<>();
    for (String fileId : fileIdList) {
      String fileGrp = JaxenUtil.getAttributeValue(metsDocument, "//mets:file[@ID='" + fileId + "']/../@USE", MetsUtil.getNamespaces());
      fileMap.put(fileId, fileGrp);
    }
    return fileMap;
  }

  /**
   * Calculate duration in [s] between 2 dates
   *
   * @param startDate startDate of duration.
   * @param endDate end date of duration.
   * @return duration in [s]
   */
  private Long getDuration(Date startDate, Date endDate) {
    Long duration = 0l;
    if ((startDate != null) && (endDate != null)) {
      duration = startDate.getTime() - endDate.getTime();
      duration = (Math.abs(duration) + 500l) / 1000l;
    }
    return duration;
  }
}
