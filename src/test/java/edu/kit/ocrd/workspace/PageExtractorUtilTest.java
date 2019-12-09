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
//    Path basePath = Paths.get("src/test/resources/text/");
//    String pageUrl = "";
//    String resourceId = "";
//    List<TextRegion> expResult = null;
//    List<TextRegion> result = PageExtractorUtil.extractTextRegions(basePath, pageUrl, resourceId);
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
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
