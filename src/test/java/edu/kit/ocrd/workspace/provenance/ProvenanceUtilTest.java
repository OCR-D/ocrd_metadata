/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kit.ocrd.workspace.provenance;

import edu.kit.ocrd.workspace.entity.ProvenanceMetadata;
import java.io.File;
import java.util.List;
import org.fzk.tools.xml.JaxenUtil;
import org.jdom.Document;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hartmann-v
 */
public class ProvenanceUtilTest {
  
  public ProvenanceUtilTest() {
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
   * Test of extractWorkflows method, of class ProvenanceUtil.
   */
  @Test
  public void testExtractWorkflows() throws Exception {
    System.out.println("extractWorkflows");
    File metsFile = new File("src/test/resources/provenance/mets.xml");
    assertTrue("File exists!", metsFile.exists());
    File provFile = new File("src/test/resources/provenance/ocrd_provenance.xml");
    assertTrue("File exists!", provFile.exists());
    Document provDocument = JaxenUtil.getDocument(provFile);
    Document metsDocument = JaxenUtil.getDocument(metsFile);
    String resourceId = "anyResourceId";
    List<ProvenanceMetadata> result = ProvenanceUtil.extractWorkflows(provDocument, metsDocument, resourceId);
    assertEquals(12, result.size());
  }

  /**
   * Test of extractWorkflows method, of class ProvenanceUtil.
   */
  @Test
  public void testExtractWorkflowsWithInvalidDate() throws Exception {
    System.out.println("extractWorkflows");
    File metsFile = new File("src/test/resources/provenance/mets.xml");
    assertTrue("File exists!", metsFile.exists());
    File provFile = new File("src/test/resources/provenance/invalid_date_ocrd_provenance.xml");
    assertTrue("File exists!", provFile.exists());
    Document provDocument = JaxenUtil.getDocument(provFile);
    Document metsDocument = JaxenUtil.getDocument(metsFile);
    String resourceId = "anyResourceId";
   
    List<ProvenanceMetadata> result = ProvenanceUtil.extractWorkflows(provDocument, metsDocument, resourceId);
    assertEquals(12, result.size());
  }
  
}
