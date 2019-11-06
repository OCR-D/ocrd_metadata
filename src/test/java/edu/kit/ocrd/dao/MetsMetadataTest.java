/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kit.ocrd.dao;

import java.util.ArrayList;
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
public class MetsMetadataTest {

  public final String title = "title";

  public final String subTitle = "subTitle";

  public final String year = "2019";

  public final String author = "author";

  public final String licence = "noLicence";

  public final String noOfPages = "5";

  public MetsMetadataTest() {
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
   * Test of getTitle method, of class MetsMetadata.
   */
  @Test
  public void testSetAndGetTitle() {
    System.out.println("getTitle");
    MetsMetadata instance = new MetsMetadata();
    String expResult = null;
    String result = instance.getTitle();
    assertEquals(expResult, result);
    expResult = title;
    instance.setTitle(title);
    result = instance.getTitle();
    assertEquals(expResult, result);
  }

  /**
   * Test of getSubTitle method, of class MetsMetadata.
   */
  @Test
  public void testSetAndGetSubTitle() {
    System.out.println("getSubTitle");
    MetsMetadata instance = new MetsMetadata();
    String expResult = null;
    String result = instance.getSubTitle();
    assertEquals(expResult, result);
    expResult = subTitle;
    instance.setSubTitle(subTitle);
    result = instance.getSubTitle();
    assertEquals(expResult, result);
  }

  /**
   * Test of getModsIdentifier method, of class MetsMetadata.
   */
  @Test
  public void testSetAndGetModsIdentifier() {
    System.out.println("getModsIdentifier");
    MetsMetadata instance = new MetsMetadata();
    List<ModsIdentifier> expResult = new ArrayList<>();
    List<ModsIdentifier> result = instance.getModsIdentifier();
    assertEquals(expResult, result);
    expResult = new ArrayList<>();
    expResult.add(new ModsIdentifier("type", "identifier"));
    instance.setModsIdentifier(expResult);
    result = instance.getModsIdentifier();
    assertEquals(expResult, result);
  }

  /**
   * Test of getPages method, of class MetsMetadata.
   */
  @Test
  public void testSetAndGetPages() {
    System.out.println("getPages");
    MetsMetadata instance = new MetsMetadata();
    List<PageFeatures> expResult = new ArrayList<>();
    List<PageFeatures> result = instance.getPages();
    assertEquals(expResult, result);
    expResult = new ArrayList<>();
    expResult.add(new PageFeatures());
    instance.setPages(expResult);
    result = instance.getPages();
    assertEquals(expResult, result);
  }

  /**
   * Test of getYear method, of class MetsMetadata.
   */
  @Test
  public void testSetAndGetYear() {
    System.out.println("getYear");
    MetsMetadata instance = new MetsMetadata();
    String expResult = null;
    String result = instance.getYear();
    assertEquals(expResult, result);
    expResult = year;
    instance.setYear(year);
    result = instance.getYear();
    assertEquals(expResult, result);
  }

  /**
   * Test of getLicence method, of class MetsMetadata.
   */
  @Test
  public void testSetAndGetLicence() {
    System.out.println("getLicence");
    MetsMetadata instance = new MetsMetadata();
    String expResult = null;
    String result = instance.getLicence();
    assertEquals(expResult, result);
    expResult = licence;
    instance.setLicence(licence);
    result = instance.getLicence();
    assertEquals(expResult, result);
  }

  /**
   * Test of getLanguage method, of class MetsMetadata.
   */
  @Test
  public void testSetAndGetLanguage() {
    System.out.println("getLanguage");
    MetsMetadata instance = new MetsMetadata();
    List<String> expResult = null;
    List<String> result = instance.getLanguage();
    assertEquals(expResult, result);
    expResult = new ArrayList<>();
    expResult.add("ger");
    instance.setLanguage(expResult);
    result = instance.getLanguage();
    assertEquals(expResult, result);
  }

  /**
   * Test of getAuthor method, of class MetsMetadata.
   */
  @Test
  public void testSetAndGetAuthor() {
    System.out.println("getAuthor");
    MetsMetadata instance = new MetsMetadata();
    String expResult = null;
    String result = instance.getAuthor();
    assertEquals(expResult, result);
    expResult = author;
    instance.setAuthor(author);
    result = instance.getAuthor();
    assertEquals(expResult, result);
  }

  /**
   * Test of getNoOfPages method, of class MetsMetadata.
   */
  @Test
  public void testSetAndGetNoOfPages() {
    System.out.println("getNoOfPages");
    MetsMetadata instance = new MetsMetadata();
    int expResult = 0;
    int result = instance.getNoOfPages();
    assertEquals(expResult, result);
    expResult = 22;
    instance.setNoOfPages(expResult);
    result = instance.getNoOfPages();
    assertEquals(expResult, result);
  }

  /**
   * Test of getClassification method, of class MetsMetadata.
   */
  @Test
  public void testSetAndGetClassification() {
    System.out.println("getClassification");
    MetsMetadata instance = new MetsMetadata();
    List<String> expResult = null;
    List<String> result = instance.getClassification();
    assertEquals(expResult, result);
    expResult = new ArrayList<>();
    expResult.add("Sachbuch");
    instance.setClassification(expResult);
    result = instance.getClassification();
    assertEquals(expResult, result);
  }

  /**
   * Test of getGenre method, of class MetsMetadata.
   */
  @Test
  public void testSetAndGetGenre() {
    System.out.println("getGenre");
    MetsMetadata instance = new MetsMetadata();
    List<String> expResult = null;
    List<String> result = instance.getGenre();
    assertEquals(expResult, result);
    expResult = new ArrayList<>();
    expResult.add("genre");
    instance.setGenre(expResult);
    result = instance.getGenre();
    assertEquals(expResult, result);
  }

  /**
   * Test of getPublisher method, of class MetsMetadata.
   */
  @Test
  public void testSetAndGetPublisher() {
    System.out.println("getPublisher");
    MetsMetadata instance = new MetsMetadata();
    String expResult = null;
    String result = instance.getPublisher();
    assertEquals(expResult, result);
    expResult = "publisher";
    instance.setPublisher(expResult);
    result = instance.getPublisher();
    assertEquals(expResult, result);
  }

  /**
   * Test of getPhysicalDescription method, of class MetsMetadata.
   */
  @Test
  public void testSetAndGetPhysicalDescription() {
    System.out.println("getPhysicalDescription");
    MetsMetadata instance = new MetsMetadata();
    String expResult = null;
    String result = instance.getPhysicalDescription();
    assertEquals(expResult, result);
    expResult = "physicalDescription";
    instance.setPhysicalDescription(expResult);
    result = instance.getPhysicalDescription();
    assertEquals(expResult, result);
  }
}
