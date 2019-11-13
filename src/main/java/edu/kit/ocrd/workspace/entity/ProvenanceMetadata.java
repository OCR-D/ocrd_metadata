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
package edu.kit.ocrd.workspace.entity;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.HashIndex;
import java.util.Date;
import org.springframework.data.annotation.Id;

/**
 * This class contains information about the zipped Bagit containers. This is:
 * <p>
 * <ul>
 * <li>durationWorkflow
 * </ul><p>
 */
@Document("provenanceMetadata")
@HashIndex(fields = {"resourceId", "workflowId"})
public final class ProvenanceMetadata {

  /**
   * ID of XSD document.
   */
  @Id
  private String id;
  /**
   * Resource Identifier for Document.
   */
  private String resourceId;
  /**
   * Identifier of the workflow
   */
  private String workflowId;
  /**
   * Label of the processor
   */
  private String processorLabel;
  /**
   * Start date of workflow.
   */
  private Date startWorkflow;
  /**
   * Duration of the workflow.
   */
  private Long durationWorkflow;
  /**
   * Start date of processor.
   */
  private Date startProcessor;
  /**
   * Duration of the processor.
   */
  private Long durationProcessor;
  /**
   * Content of parameter file.
   */
  private String parameterFile;
  /**
   * Input file groups.
   */
  private String inputFileGrps;
  /**
   * Output file groups.
   */
  private String outputFileGrps;

  /**
   * Get database ID.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Set database ID. (Shouldn't be used.)
   *
   * @param id the id to set
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Get resourceID of document.
   *
   * @return resourceID of document.
   */
  public String getResourceId() {
    return resourceId;
  }

  /**
   * Set resourceID of document.
   *
   * @param resourceId ResourceID of document
   */
  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  /**
   * Get workflowId of the METS document.
   *
   * @return Resource ID of the METS document.
   */
  public String getWorkflowId() {
    return workflowId;
  }

  /**
   * Set workflowId of the METS document.
   *
   * @param workflowId Resource ID of the METS document.
   */
  public void setWorkflowId(String workflowId) {
    this.workflowId = workflowId;
  }

  /**
   * Get durationWorkflow.
   *
   * @return the durationWorkflow
   */
  public Long getDurationWorkflow() {
    return durationWorkflow;
  }

  /**
   * Set durationWorkflow.
   *
   * @param durationWorkflow the durationWorkflow to set
   */
  public void setDurationWorkflow(Long durationWorkflow) {
    this.durationWorkflow = durationWorkflow;
  }

  /**
   * Get start date of workflow.
   *
   * @return the startWorkflow
   */
  public Date getStartWorkflow() {
    return startWorkflow;
  }

  /**
   * Set start date of workflow.
   *
   * @param startWorkflow the startWorkflow to set
   */
  public void setStartWorkflow(Date startWorkflow) {
    this.startWorkflow = startWorkflow;
  }

  /**
   * Get start date of processor.
   *
   * @return the startProcessor
   */
  public Date getStartProcessor() {
    return startProcessor;
  }

  /**
   * Set start date of processor.
   *
   * @param startProcessor the startProcessor to set
   */
  public void setStartProcessor(Date startProcessor) {
    this.startProcessor = startProcessor;
  }

  /**
   * Get duration of processor in [s].
   *
   * @return the durationProcessor
   */
  public Long getDurationProcessor() {
    return durationProcessor;
  }

  /**
   * Set duration of processor in [s].
   */
  public void setDurationProcessor(Long durationProcessor) {
    this.durationProcessor = durationProcessor;
  }

  /**
   * Get content of parameter file.
   *
   * @return the parameterFile
   */
  public String getParameterFile() {
    return parameterFile;
  }

  /**
   * Set content of parameter file.
   *
   * @return the parameterFile
   */
  public void setParameterFile(String parameterFile) {
    this.parameterFile = parameterFile;
  }

  /**
   * Get input file groups as a comma separated list.
   *
   * @return the inputFileGrps
   */
  public String getInputFileGrps() {
    return inputFileGrps;
  }

  /**
   * Set input file groups as a comma separated list.
   *
   * @return the inputFileGrps
   */
  public void setInputFileGrps(String inputFileGrps) {
    this.inputFileGrps = inputFileGrps;
  }

  /**
   * Get output file groups as a comma separated list.
   *
   * @return the outputFileGrps
   */
  public String getOutputFileGrps() {
    return outputFileGrps;
  }

  /**
   * Set output file groups as a comma separated list.
   *
   * @return the outputFileGrps
   */
  public void setOutputFileGrps(String outputFileGrps) {
    this.outputFileGrps = outputFileGrps;
  }

  public Object clone() {
    ProvenanceMetadata provMd = new ProvenanceMetadata();

    provMd.durationProcessor = durationProcessor;
    provMd.durationWorkflow = durationWorkflow;
    provMd.inputFileGrps = inputFileGrps;
    provMd.outputFileGrps = outputFileGrps;
    provMd.parameterFile = parameterFile;
    provMd.processorLabel = processorLabel;
    provMd.resourceId = resourceId;
    provMd.startProcessor = startProcessor;
    provMd.startWorkflow = startWorkflow;
    provMd.workflowId = workflowId;

    return provMd;
  }

  /**
   * Get label of the processor containing name and version.
   * 
   * @return the processorLabel
   */
  public String getProcessorLabel() {
    return processorLabel;
  }

  /**
   * Set label of the processor containing name and version.
   * 
   * @param processorLabel the processorLabel to set
   */
  public void setProcessorLabel(String processorLabel) {
    this.processorLabel = processorLabel;
  }
  
  @Override
  public String toString() {
    String string = String.format("ProvenanceMetadata: workflow id=%s, start date=%s, duration=%s, processor=%s, start date=%s, duration=%s, parameter=%s, input groups=%s, output groups=%s", workflowId, startWorkflow, durationWorkflow, processorLabel, startProcessor, durationProcessor, parameterFile, inputFileGrps, outputFileGrps);
    return string;
  }

}
