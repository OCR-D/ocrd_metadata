/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kit.ocrd.workspace;

import edu.kit.ocrd.workspace.entity.TextRegion;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
public class PageExtractorUtilTest {
  
  public PageExtractorUtilTest() {
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
   * Test of extractAllTextRegions method, of class PageExtractorUtil.
   */
  @Test
  public void testConstructor() throws Exception {
    assertNotNull(new PageExtractorUtil());
  }  

  /**
   * Test of extractAllTextRegions method, of class PageExtractorUtil.
   */
  @Test
  public void testExtractAllTextRegions() throws Exception {
    System.out.println("extractAllTextRegions");
    File metsFile = new File("src/test/resources/text/mets.xml");
    assertTrue("File exists!", metsFile.exists());
    String resourceId = "resourceId";
    List<TextRegion> expResult = null;
    List<TextRegion> result = PageExtractorUtil.extractAllTextRegions(metsFile, resourceId);
    assertEquals(53, result.size());
   }

  /**
   * Test of extractTextRegions method, of class PageExtractorUtil.
   */
  @Test
  public void testExtractTextRegions() throws Exception {
    System.out.println("extractTextRegions");
    File metsFile = new File("src/test/resources/all/data/mets.xml");
    assertTrue("File exists!", metsFile.exists());
    String resourceId = "OnlyForTests";
    List<TextRegion> result = PageExtractorUtil.extractAllTextRegions(metsFile, resourceId);
    assertEquals(5, result.size());
    for (TextRegion pmd : result) {
      assertTrue(pmd.getOrder() >= 0);
      assertTrue(pmd.getOrder() < 5);
      assertEquals("region000" + pmd.getOrder().toString(), pmd.getRegion());
      assertTrue(pmd.getPageUrl().startsWith("OCR-D-OCR-"));
      assertEquals("OCR-D-IMG/OCR-D-IMG_0001.jpg", pmd.getImageUrl());
      assertEquals(resourceId, pmd.getResourceId());
      assertNull(pmd.getVersion());
    }
  }

  /**
   * Test of main method, of class PageExtractorUtil.
   */
  @Test
  public void testMain() throws Exception {
//    System.out.println("main");
//    String[] args = null;
//    PageExtractorUtil.main(args);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
  }
  
}
