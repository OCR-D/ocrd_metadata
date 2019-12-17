/*
 * Copyright 2019 Karlsruhe Institute of Technology.
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
package edu.kit.ocrd.workspace.entity;

import org.springframework.data.annotation.Id;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;

/**
 * Contains one text region of one page.
 */
@Document("sectionDocument")
@HashIndex(fields = {"resourceId"})
public class TextRegion implements IBaseEntity {

  /**
   * ID of the document.
   */
  @Id
  private String id;

  /**
    * Resource ID of METS document.
    */
   private String resourceId;
  /**
   * Version of the document. (Start with version 1 increment version number.)
   */
  private Integer version;
   /**
    * Order of the page.
    */
   private Integer order;
   
  /** 
   * image URL of the referenced image inside page.xml.
   */ 
  private String imageUrl;
   
  /** 
   * page URL of the page.xml.
   */ 
  private String pageUrl;
   
  /** 
   * ID of the region.
   */ 
  private String region;
  /** 
   * Features of the GroundTruth metadata format.
   */
  private String text;
  
  /**
   * Confidence level of given text.
   */
  private float confidence;


  @Override
  public String getId() {
    return id;
  }

  @Override
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Get resource ID of METS document.
   * 
   * @return the resourceId
   */
  @Override
  public String getResourceId() {
    return resourceId;
  }

  /**
   * Set resource ID of METS document.
   * 
   * @param resourceId the resourceId to set
   */
  @Override
  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  @Override
  public Integer getVersion() {
    return version;
  }

  @Override
  public void setVersion(Integer version) {
    this.version = version;
  }

  /**
   * Get order of the page.
   * @return the order
   */
  public Integer getOrder() {
    return order;
  }

  /**
   * Set order of the page.
   * @param order the order to set
   */
  public void setOrder(Integer order) {
    this.order = order;
  }

  /**
   * Get page URL of page.xml.
   * @return the URL of the image file.
   */
  public String getPageUrl() {
    return pageUrl;
  }

  /**
   * Set page URL of page.xml.
   * @param pageUrl the URL of the image file to set
   */
  public void setPageUrl(String pageUrl) {
    this.pageUrl = pageUrl;
  }

  /**
   * Get image URL of the referenced image inside page.xml.
   * @return the URL of the image file.
   */
  public String getImageUrl() {
    return imageUrl;
  }

  /**
   * Set image URL of the referenced image inside page.xml.
   * @param imageUrl the URL of the image file to set
   */
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  /**
   * Get id of the region.
   * @return the region
   */
  public String getRegion() {
    return region;
  }

  /**
   * Set id of the region.
   * @param region the region to set
   */
  public void setRegion(String region) {
    this.region = region;
  }

  /**
   * Get text of the region.
   * @return the text
   */
  public String getText() {
    return text;
  }

  /**
   * Set text of the region.
   * @param text the text to set
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * Get confidence level of the text.
   * @return the confidence
   */
  public float getConfidence() {
    return confidence;
  }

  /**
   * Set confidence level of the text.
   * @param confidence the confidence to set
   */
  public void setConfidence(float confidence) {
    this.confidence = confidence;
  }
   @Override
  public String toString() {
    String buffer = String.format("Resource ID: '%s', PageUrl: '%s', ImageUrl: '%s', Region ID: '%s', Order: '%s', Confidence Level: '%s', Text: '%s'", 
            resourceId, pageUrl, imageUrl, region, order, confidence, text);
    return buffer;
  }
}
