/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kit.ocrd.workspace.provenance;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class ProvenanceEntity.
 */
public class ProvenanceEntityTest {
  
  private String id = "myId";
  private String type = "myType";
  private String value = "myValue";
  
  public ProvenanceEntityTest() {
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
   * Test of getId method, of class ProvenanceEntity.
   */
  @Test
  public void testSetAndGetId() {
    System.out.println("testSetAndGetId");
    ProvenanceEntity instance = new ProvenanceEntity();
    String expResult = null;
    String result = instance.getId();
    assertEquals(expResult, result);
    expResult = id;
    instance.setId(expResult);
    result = instance.getId();
    assertEquals(expResult, result);
    assertNull(instance.getType());
    assertNull(instance.getValue());
  }

  /**
   * Test of getId method, of class ProvenanceEntity.
   */
  @Test
  public void testSetAndGetType() {
    System.out.println("testSetAndGetType");
    ProvenanceEntity instance = new ProvenanceEntity();
    String expResult = null;
    String result = instance.getType();
    assertEquals(expResult, result);
    expResult = type;
    instance.setType(expResult);
    result = instance.getType();
    assertEquals(expResult, result);
    assertNull(instance.getId());
    assertNull(instance.getValue());
  }

  /**
   * Test of getId method, of class ProvenanceEntity.
   */
  @Test
  public void testSetAndGetValue() {
    System.out.println("testSetAndGetValue");
    ProvenanceEntity instance = new ProvenanceEntity();
    String expResult = null;
    String result = instance.getValue();
    assertEquals(expResult, result);
    expResult = value;
    instance.setValue(expResult);
    result = instance.getValue();
    assertEquals(expResult, result);
    assertNull(instance.getType());
    assertNull(instance.getId());
  }

  /**
   * Test of toString method, of class ProvenanceEntity.
   */
  @Test
  public void testToString() {
    System.out.println("toString");
    ProvenanceEntity instance = new ProvenanceEntity();
    String result = instance.toString();
    assertTrue(result.contains("null"));
    instance.setId(id);
    result = instance.toString();
    assertTrue(result.contains(id));
    assertFalse(result.contains(type));
    assertFalse(result.contains(value));
    assertTrue(result.contains("null"));
    instance.setType(type);
    result = instance.toString();
    assertTrue(result.contains(id));
    assertTrue(result.contains(type));
    assertFalse(result.contains(value));
    assertTrue(result.contains("null"));
    instance.setValue(value);
    result = instance.toString();
    assertTrue(result.contains(id));
    assertTrue(result.contains(type));
    assertTrue(result.contains(value));
    assertFalse(result.contains("null"));
  }
  
}
