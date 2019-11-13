/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kit.ocrd.workspace.provenance;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class ProvenanceActivity
 */
public class ProvenanceActivityTest {
  
  private String id = "myId";
  private String type = "myType";
  private String label = "myLabel";
  private Date startDate = new Date();
  private Date endDate = new Date(startDate.getTime() + 2000);
  
  public ProvenanceActivityTest() {
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
   * Test of getId method, of class ProvenanceActivity.
   */
  @Test
  public void testSetAndGetId() {
    System.out.println("testSetAndGetId");
    ProvenanceActivity instance = new ProvenanceActivity();
    String expResult = null;
    String result = instance.getId();
    assertEquals(expResult, result);
    expResult = id;
    instance.setId(expResult);
    result = instance.getId();
    assertEquals(expResult, result);
    assertNull(instance.getType());
    assertNull(instance.getLabel());
    assertNull(instance.getStartDate());
    assertNull(instance.getEndDate());
  }

  /**
   * Test of getId method, of class ProvenanceActivity.
   */
  @Test
  public void testSetAndGetType() {
    System.out.println("testSetAndGetType");
    ProvenanceActivity instance = new ProvenanceActivity();
    String expResult = null;
    String result = instance.getType();
    assertEquals(expResult, result);
    expResult = type;
    instance.setType(expResult);
    result = instance.getType();
    assertEquals(expResult, result);
    assertNull(instance.getId());
    assertNull(instance.getLabel());
    assertNull(instance.getStartDate());
    assertNull(instance.getEndDate());
  }

  /**
   * Test of getId method, of class ProvenanceActivity.
   */
  @Test
  public void testSetAndGetLabel() {
    System.out.println("testSetAndGetLabel");
    ProvenanceActivity instance = new ProvenanceActivity();
    String expResult = null;
    String result = instance.getLabel();
    assertEquals(expResult, result);
    expResult = label;
    instance.setLabel(expResult);
    result = instance.getLabel();
    assertEquals(expResult, result);
    assertNull(instance.getType());
    assertNull(instance.getId());
    assertNull(instance.getStartDate());
    assertNull(instance.getEndDate());
  }

  /**
   * Test of toString method, of class ProvenanceActivity.
   */
  @Test
  public void testToString() {
    System.out.println("toString");
    String startTimeAsString = startDate.toString();
    String endTimeAsString = endDate.toString();
    ProvenanceActivity instance = new ProvenanceActivity();
    String result = instance.toString();
    assertTrue(result.contains("null"));
    instance.setId(id);
    result = instance.toString();
    assertTrue(result.contains(id));
    assertFalse(result.contains(type));
    assertFalse(result.contains(label));
    assertFalse(result.contains(startTimeAsString));
    assertFalse(result.contains(endTimeAsString));
    assertTrue(result.contains("null"));
    instance.setType(type);
    result = instance.toString();
    assertTrue(result.contains(id));
    assertTrue(result.contains(type));
    assertFalse(result.contains(label));
    assertFalse(result.contains(startTimeAsString));
    assertFalse(result.contains(endTimeAsString));
    assertTrue(result.contains("null"));
    instance.setLabel(label);
    result = instance.toString();
    assertTrue(result.contains(id));
    assertTrue(result.contains(type));
    assertTrue(result.contains(label));
    assertFalse(result.contains(startTimeAsString));
    assertFalse(result.contains(endTimeAsString));
    assertTrue(result.contains("null"));
    instance.setStartDate(startDate);
    result = instance.toString();
    assertTrue(result.contains(id));
    assertTrue(result.contains(type));
    assertTrue(result.contains(label));
    assertTrue(result.contains(startTimeAsString));
    assertFalse(result.contains(endTimeAsString));
    assertTrue(result.contains("null"));
    instance.setEndDate(endDate);
    result = instance.toString();
    assertTrue(result.contains(id));
    assertTrue(result.contains(type));
    assertTrue(result.contains(label));
    assertTrue(result.contains(startTimeAsString));
    assertTrue(result.contains(endTimeAsString));
    assertFalse(result.contains("null"));
  }
  
}
