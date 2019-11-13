/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kit.ocrd.workspace.entity;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class ProvenanceMetadata
 */
public class ProvenanceMetadataTest {

  private Long durationProcessor = 12l;
  private Long durationWorkflow = 120l;
  private String id = "myProvMdId";
  private String inputFileGrps = "myInputFileGrps";
  private String outputFileGrps = "myOutputFileGrps";
  private String parameterFile = "myParameterFileContent";
  private String processorLabel = "myProcessorLabel, version 0.0.1";
  private String resourceId = "myResourceId";
  private Date startWorkflow = new Date();
  private Date startProcessor = new Date(startWorkflow.getTime() + 1500);
  private String workflowId = "myWorkflowId";

  public ProvenanceMetadataTest() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  /**
   * Test of getId method, of class ProvenanceMetadata.
   */
  @Test
  public void testSetAndGetId() {
    System.out.println("testSetAndGetId");
    ProvenanceMetadata instance = new ProvenanceMetadata();
    String expResult = null;
    String result = instance.getId();
    assertEquals(expResult, result);
    expResult = id;
    instance.setId(id);
    result = instance.getId();
    assertEquals(expResult, result);
    assertNull(instance.getDurationProcessor());
    assertNull(instance.getDurationWorkflow());
    assertNull(instance.getInputFileGrps());
    assertNull(instance.getOutputFileGrps());
    assertNull(instance.getParameterFile());
    assertNull(instance.getProcessorLabel());
    assertNull(instance.getResourceId());
    assertNull(instance.getStartProcessor());
    assertNull(instance.getStartWorkflow());
    assertNull(instance.getWorkflowId());
  }

  /**
   * Test of getResourceId method, of class ProvenanceMetadata.
   */
  @Test
  public void testSetAndGetResourceId() {
    System.out.println("testSetAndGetResourceId");
    ProvenanceMetadata instance = new ProvenanceMetadata();
    String expResult = null;
    String result = instance.getResourceId();
    assertEquals(expResult, result);
    expResult = resourceId;
    instance.setResourceId(resourceId);
    result = instance.getResourceId();
    assertEquals(expResult, result);
    assertNull(instance.getId());
    assertNull(instance.getDurationProcessor());
    assertNull(instance.getDurationWorkflow());
    assertNull(instance.getInputFileGrps());
    assertNull(instance.getOutputFileGrps());
    assertNull(instance.getParameterFile());
    assertNull(instance.getProcessorLabel());
    assertNull(instance.getStartProcessor());
    assertNull(instance.getStartWorkflow());
    assertNull(instance.getWorkflowId());
  }

  /**
   * Test of getResourceId method, of class ProvenanceMetadata.
   */
  @Test
  public void testSetAndGetWorkflowId() {
    System.out.println("testSetAndGetWorkflowId");
    ProvenanceMetadata instance = new ProvenanceMetadata();
    String expResult = null;
    String result = instance.getWorkflowId();
    assertEquals(expResult, result);
    expResult = workflowId;
    instance.setWorkflowId(workflowId);
    result = instance.getWorkflowId();
    assertEquals(expResult, result);
    assertNull(instance.getId());
    assertNull(instance.getDurationProcessor());
    assertNull(instance.getDurationWorkflow());
    assertNull(instance.getInputFileGrps());
    assertNull(instance.getOutputFileGrps());
    assertNull(instance.getParameterFile());
    assertNull(instance.getProcessorLabel());
    assertNull(instance.getResourceId());
    assertNull(instance.getStartProcessor());
    assertNull(instance.getStartWorkflow());
  }

  /**
   * Test of getResourceId method, of class ProvenanceMetadata.
   */
  @Test
  public void testSetAndGetDurationWorkflow() {
    System.out.println("testSetAndGetDurationWorkflow");
    ProvenanceMetadata instance = new ProvenanceMetadata();
    Long expResult = null;
    Long result = instance.getDurationWorkflow();
    assertEquals(expResult, result);
    expResult = durationWorkflow;
    instance.setDurationWorkflow(durationWorkflow);
    result = instance.getDurationWorkflow();
    assertEquals(expResult, result);
    assertNull(instance.getId());
    assertNull(instance.getDurationProcessor());
    assertNull(instance.getInputFileGrps());
    assertNull(instance.getOutputFileGrps());
    assertNull(instance.getParameterFile());
    assertNull(instance.getProcessorLabel());
    assertNull(instance.getResourceId());
    assertNull(instance.getStartProcessor());
    assertNull(instance.getStartWorkflow());
    assertNull(instance.getWorkflowId());
  }

  /**
   * Test of getResourceId method, of class ProvenanceMetadata.
   */
  @Test
  public void testSetAndGetStartWorkflow() {
    System.out.println("testSetAndGetStartWorkflow");
    ProvenanceMetadata instance = new ProvenanceMetadata();
    Date expResult = null;
    Date result = instance.getStartWorkflow();
    assertEquals(expResult, result);
    expResult = startWorkflow;
    instance.setStartWorkflow(startWorkflow);
    result = instance.getStartWorkflow();
    assertEquals(expResult, result);
    assertNull(instance.getId());
    assertNull(instance.getDurationProcessor());
    assertNull(instance.getDurationWorkflow());
    assertNull(instance.getInputFileGrps());
    assertNull(instance.getOutputFileGrps());
    assertNull(instance.getParameterFile());
    assertNull(instance.getProcessorLabel());
    assertNull(instance.getResourceId());
    assertNull(instance.getStartProcessor());
    assertNull(instance.getWorkflowId());
  }

  /**
   * Test of getResourceId method, of class ProvenanceMetadata.
   */
  @Test
  public void testSetAndGetDurationProcessor() {
    System.out.println("testSetAndGetDurationProcessor");
    ProvenanceMetadata instance = new ProvenanceMetadata();
    Long expResult = null;
    Long result = instance.getDurationProcessor();
    assertEquals(expResult, result);
    expResult = durationProcessor;
    instance.setDurationProcessor(durationProcessor);
    result = instance.getDurationProcessor();
    assertEquals(expResult, result);
    assertNull(instance.getId());
    assertNull(instance.getDurationWorkflow());
    assertNull(instance.getInputFileGrps());
    assertNull(instance.getOutputFileGrps());
    assertNull(instance.getParameterFile());
    assertNull(instance.getProcessorLabel());
    assertNull(instance.getResourceId());
    assertNull(instance.getStartProcessor());
    assertNull(instance.getStartWorkflow());
    assertNull(instance.getWorkflowId());
  }

  /**
   * Test of getResourceId method, of class ProvenanceMetadata.
   */
  @Test
  public void testSetAndGetStartProcessor() {
    System.out.println("testSetAndGetStartProcessor");
    ProvenanceMetadata instance = new ProvenanceMetadata();
    Date expResult = null;
    Date result = instance.getStartProcessor();
    assertEquals(expResult, result);
    expResult = startProcessor;
    instance.setStartProcessor(startProcessor);
    result = instance.getStartProcessor();
    assertEquals(expResult, result);
    assertNull(instance.getId());
    assertNull(instance.getDurationProcessor());
    assertNull(instance.getDurationWorkflow());
    assertNull(instance.getInputFileGrps());
    assertNull(instance.getOutputFileGrps());
    assertNull(instance.getParameterFile());
    assertNull(instance.getProcessorLabel());
    assertNull(instance.getResourceId());
    assertNull(instance.getStartWorkflow());
    assertNull(instance.getWorkflowId());
  }

  /**
   * Test of getResourceId method, of class ProvenanceMetadata.
   */
  @Test
  public void testSetAndGetParameterFile() {
    System.out.println("testSetAndGetParameterFile");
    ProvenanceMetadata instance = new ProvenanceMetadata();
    String expResult = null;
    String result = instance.getParameterFile();
    assertEquals(expResult, result);
    expResult = parameterFile;
    instance.setParameterFile(parameterFile);
    result = instance.getParameterFile();
    assertEquals(expResult, result);
    assertNull(instance.getId());
    assertNull(instance.getDurationProcessor());
    assertNull(instance.getDurationWorkflow());
    assertNull(instance.getInputFileGrps());
    assertNull(instance.getOutputFileGrps());
    assertNull(instance.getProcessorLabel());
    assertNull(instance.getResourceId());
    assertNull(instance.getStartProcessor());
    assertNull(instance.getStartWorkflow());
    assertNull(instance.getWorkflowId());
  }

  /**
   * Test of getResourceId method, of class ProvenanceMetadata.
   */
  @Test
  public void testSetAndGetInputFileGrps() {
    System.out.println("testSetAndGetInputFileGrps");
    ProvenanceMetadata instance = new ProvenanceMetadata();
    String expResult = null;
    String result = instance.getInputFileGrps();
    assertEquals(expResult, result);
    expResult = inputFileGrps;
    instance.setInputFileGrps(inputFileGrps);
    result = instance.getInputFileGrps();
    assertEquals(expResult, result);
    assertNull(instance.getId());
    assertNull(instance.getDurationProcessor());
    assertNull(instance.getDurationWorkflow());
    assertNull(instance.getOutputFileGrps());
    assertNull(instance.getParameterFile());
    assertNull(instance.getProcessorLabel());
    assertNull(instance.getResourceId());
    assertNull(instance.getStartProcessor());
    assertNull(instance.getStartWorkflow());
    assertNull(instance.getWorkflowId());
  }

  /**
   * Test of getResourceId method, of class ProvenanceMetadata.
   */
  @Test
  public void testSetAndGetOutputFileGrps() {
    System.out.println("testSetAndGetOutputFileGrps");
    ProvenanceMetadata instance = new ProvenanceMetadata();
    String expResult = null;
    String result = instance.getOutputFileGrps();
    assertEquals(expResult, result);
    expResult = outputFileGrps;
    instance.setOutputFileGrps(outputFileGrps);
    result = instance.getOutputFileGrps();
    assertEquals(expResult, result);
    assertNull(instance.getId());
    assertNull(instance.getDurationProcessor());
    assertNull(instance.getDurationWorkflow());
    assertNull(instance.getInputFileGrps());
    assertNull(instance.getParameterFile());
    assertNull(instance.getProcessorLabel());
    assertNull(instance.getResourceId());
    assertNull(instance.getStartProcessor());
    assertNull(instance.getStartWorkflow());
    assertNull(instance.getWorkflowId());
  }

  /**
   * Test of getResourceId method, of class ProvenanceMetadata.
   */
  @Test
  public void testSetAndGetProcessorLabel() {
    System.out.println("testSetAndGetProcessorLabel");
    ProvenanceMetadata instance = new ProvenanceMetadata();
    String expResult = null;
    String result = instance.getProcessorLabel();
    assertEquals(expResult, result);
    expResult = processorLabel;
    instance.setProcessorLabel(processorLabel);
    result = instance.getProcessorLabel();
    assertEquals(expResult, result);
    assertNull(instance.getId());
    assertNull(instance.getDurationProcessor());
    assertNull(instance.getDurationWorkflow());
    assertNull(instance.getInputFileGrps());
    assertNull(instance.getOutputFileGrps());
    assertNull(instance.getParameterFile());
    assertNull(instance.getResourceId());
    assertNull(instance.getStartProcessor());
    assertNull(instance.getStartWorkflow());
    assertNull(instance.getWorkflowId());
  }

  /**
   * Test of clone method, of class ProvenanceMetadata.
   */
  @Test
  public void testClone() {
    System.out.println("clone");
    ProvenanceMetadata instance = new ProvenanceMetadata();
    ProvenanceMetadata result = (ProvenanceMetadata) instance.clone();
    checkProvenanceMetadata(instance, result);
    instance.setDurationProcessor(durationProcessor);
    result = (ProvenanceMetadata) instance.clone();
    checkProvenanceMetadata(instance, result);
    instance.setDurationWorkflow(durationWorkflow);
    result = (ProvenanceMetadata) instance.clone();
    checkProvenanceMetadata(instance, result);
    instance.setId(id);
    result = (ProvenanceMetadata) instance.clone();
    checkProvenanceMetadata(instance, result);
    instance.setInputFileGrps(inputFileGrps);
    result = (ProvenanceMetadata) instance.clone();
    checkProvenanceMetadata(instance, result);
    instance.setOutputFileGrps(outputFileGrps);
    result = (ProvenanceMetadata) instance.clone();
    checkProvenanceMetadata(instance, result);
    instance.setParameterFile(parameterFile);
    result = (ProvenanceMetadata) instance.clone();
    checkProvenanceMetadata(instance, result);
    instance.setProcessorLabel(processorLabel);
     result = (ProvenanceMetadata) instance.clone();
   checkProvenanceMetadata(instance, result);
    instance.setStartProcessor(startProcessor);
    result = (ProvenanceMetadata) instance.clone();
    checkProvenanceMetadata(instance, result);
    instance.setStartWorkflow(startWorkflow);
    result = (ProvenanceMetadata) instance.clone();
    checkProvenanceMetadata(instance, result);
    instance.setWorkflowId(workflowId);
    result = (ProvenanceMetadata) instance.clone();
    checkProvenanceMetadata(instance, result);
  }

  private void checkProvenanceMetadata(ProvenanceMetadata expected, ProvenanceMetadata result) {
    assertEquals(expected.getDurationProcessor(), result.getDurationProcessor());
    assertEquals(expected.getDurationWorkflow(), result.getDurationWorkflow());
    assertEquals(null, result.getId());
    assertEquals(expected.getInputFileGrps(), result.getInputFileGrps());
    assertEquals(expected.getOutputFileGrps(), result.getOutputFileGrps());
    assertEquals(expected.getParameterFile(), result.getParameterFile());
    assertEquals(expected.getProcessorLabel(), result.getProcessorLabel());
    assertEquals(null, result.getResourceId());
    assertEquals(expected.getStartProcessor(), result.getStartProcessor());
    assertEquals(expected.getStartWorkflow(), result.getStartWorkflow());
    assertEquals(expected.getWorkflowId(), result.getWorkflowId());
  }

  private void checkToString(ProvenanceMetadata first) {
    String string = first.toString();
    assertTrue(string.contains(first.getDurationProcessor() == null ? "null" : first.getDurationProcessor().toString()));
    assertTrue(string.contains(first.getDurationWorkflow() == null ? "null" : first.getDurationWorkflow().toString()));
    assertTrue(string.contains(first.getInputFileGrps() == null ? "null" : first.getInputFileGrps()));
    assertTrue(string.contains(first.getOutputFileGrps() == null ? "null" : first.getOutputFileGrps()));
    assertTrue(string.contains(first.getParameterFile() == null ? "null" : first.getParameterFile()));
    assertTrue(string.contains(first.getProcessorLabel() == null ? "null" : first.getProcessorLabel()));
    assertTrue(string.contains(first.getStartProcessor() == null ? "null" : first.getStartProcessor().toString()));
    assertTrue(string.contains(first.getStartWorkflow() == null ? "null" : first.getStartProcessor().toString()));
    assertTrue(string.contains(first.getWorkflowId() == null ? "null" : first.getWorkflowId()));
  }

  /**
   * Test of toString method, of class ProvenanceMetadata.
   */
  @Test
  public void testToString() {
    System.out.println("toString");
    ProvenanceMetadata instance = new ProvenanceMetadata();
    checkToString(instance);
    instance.setDurationProcessor(durationProcessor);
    checkToString(instance);
    instance.setDurationWorkflow(durationWorkflow);
    checkToString(instance);
    instance.setId(id);
    checkToString(instance);
    instance.setInputFileGrps(inputFileGrps);
    checkToString(instance);
    instance.setOutputFileGrps(outputFileGrps);
    checkToString(instance);
    instance.setParameterFile(parameterFile);
    checkToString(instance);
    instance.setProcessorLabel(processorLabel);
   checkToString(instance);
    instance.setStartProcessor(startProcessor);
    checkToString(instance);
    instance.setStartWorkflow(startWorkflow);
    checkToString(instance);
    instance.setWorkflowId(workflowId);
    checkToString(instance);
  }

}
