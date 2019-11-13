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

import edu.kit.ocrd.workspace.entity.*;
import java.util.Date;

/**
 * This class contains ground truth metadata about one page inside a METS document.
 * These are: <p><ul>
 *    <li>Resource Identifier</li>
 *    <li>Order</li>
 *    <li>PAGEID of page</li>
 *    <li>FEATURE of page</li>
 *    </ul></p>
 */
public class ProvenanceActivity {
  /**
   * id of activity
   */
  private String id;
  /**
   * Label of the activity.
   */
  private String label;
  /**
   * Type of the activity. 
   */
  private String type;
   /**
   * Start date of the activity. 
   */
  private Date startDate;
   /**
   * End date of the activity. 
   */
  private Date endDate;
  /**
   * Get resourceID of document.
   * @return resourceID of document.
   */
  public String getId() {
    return id;
  }
  /**
   * Set resourceID of document.
   * @param id ResourceID of document
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Get PAGEID of page.
   * 
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * Set PAGEID of page.
   * 
   * @param type the type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Get oder of page.
   * 
   * @return the label
   */
  public String getLabel() {
    return label;
  }

  /**
   * Set label of page.
   * 
   * @param label the label to set
   */
  public void setLabel(String label) {
    this.label = label;
  }
  
  @Override
  public String toString() {
     return "Activity [id=" + id + ", label=" + label + ", type=" + type + ", start=" + startDate + ", end=" + endDate + "]";
  }

  /**
   * @return the startDate
   */
  public Date getStartDate() {
    return startDate;
  }

  /**
   * @param startDate the startDate to set
   */
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  /**
   * @return the endDate
   */
  public Date getEndDate() {
    return endDate;
  }

  /**
   * @param endDate the endDate to set
   */
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
}
