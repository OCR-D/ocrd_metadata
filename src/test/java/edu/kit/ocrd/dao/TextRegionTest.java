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
package edu.kit.ocrd.dao;

import edu.kit.ocrd.workspace.entity.TextRegion;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test for TextRegion.
 */
public class TextRegionTest {

  public TextRegionTest() {
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
   * Test of set/getConfidence method, of class TextRegion.
   */
  @Test
  public void testSetAndGetConfidence() {
    System.out.println("getConfidence");
    TextRegion instance = new TextRegion();
    assertEquals(0.0f, instance.getConfidence(), 0.001f);
    float expResult = 0.5f;
    instance.setConfidence(expResult);
    float result = instance.getConfidence();
    assertEquals(expResult, result, 0.001f);
    assertNull(instance.getRegion());
    assertNull(instance.getPageUrl());
    assertNull(instance.getRegion());
    assertNull(instance.getResourceId());
    assertNull(instance.getText());
  }

  /**
   * Test of set/getImageUrl method, of class TextRegion.
   */
  @Test
  public void testSetAndGetImageUrl() {
    System.out.println("getImageUrl");
    TextRegion instance = new TextRegion();
    assertNull(instance.getImageUrl());
    String expResult = "imageUrl";
    instance.setImageUrl(expResult);
    String result = instance.getImageUrl();
    assertEquals(expResult, result);
    assertEquals(0.0f, instance.getConfidence(), 0.001f);
    assertNull(instance.getOrder());
    assertNull(instance.getPageUrl());
    assertNull(instance.getRegion());
    assertNull(instance.getResourceId());
    assertNull(instance.getText());
  }

  /**
   * Test of set/getOrder method, of class TextRegion.
   */
  @Test
  public void testSetAndGetOrder() {
    System.out.println("getOrder");
    TextRegion instance = new TextRegion();
    String result = instance.getOrder();
    assertNull(result);
    String expResult = "order";
    instance.setOrder(expResult);
    result = instance.getOrder();
    assertEquals(expResult, result);
    assertEquals(0.0f, instance.getConfidence(), 0.001f);
    assertNull(instance.getImageUrl());
    assertNull(instance.getPageUrl());
    assertNull(instance.getRegion());
    assertNull(instance.getResourceId());
    assertNull(instance.getText());
  }

  /**
   * Test of set/getPageUrl method, of class TextRegion.
   */
  @Test
  public void testSetAndGetPageUrl() {
    System.out.println("getPageUrl");
    TextRegion instance = new TextRegion();
    assertNull(instance.getPageUrl());
    String expResult = "pageUrl";
    instance.setPageUrl(expResult);
    String result = instance.getPageUrl();
    assertEquals(expResult, result);
    assertEquals(0.0f, instance.getConfidence(), 0.001f);
    assertNull(instance.getImageUrl());
    assertNull(instance.getOrder());
    assertNull(instance.getRegion());
    assertNull(instance.getResourceId());
    assertNull(instance.getText());
  }

  /**
   * Test of set/getRegion method, of class TextRegion.
   */
  @Test
  public void testSetAndGetRegion() {
    System.out.println("getRegion");
    TextRegion instance = new TextRegion();
    assertNull(instance.getRegion());
    String expResult = "region";
    instance.setRegion(expResult);
    String result = instance.getRegion();
    assertEquals(expResult, result);
    assertEquals(0.0f, instance.getConfidence(), 0.001f);
    assertNull(instance.getImageUrl());
    assertNull(instance.getOrder());
    assertNull(instance.getPageUrl());
    assertNull(instance.getResourceId());
    assertNull(instance.getText());
  }

  /**
   * Test of set/getResourceId method, of class TextRegion.
   */
  @Test
  public void testSetAndGetResourceId() {
    System.out.println("getResourceId");
    TextRegion instance = new TextRegion();
    assertNull(instance.getResourceId());
    String expResult = "resourceId";
    instance.setResourceId(expResult);
    String result = instance.getResourceId();
    assertEquals(expResult, result);
    assertEquals(0.0f, instance.getConfidence(), 0.001f);
    assertNull(instance.getImageUrl());
    assertNull(instance.getOrder());
    assertNull(instance.getPageUrl());
    assertNull(instance.getRegion());
    assertNull(instance.getText());
  }

  /**
   * Test of set/getText method, of class TextRegion.
   */
  @Test
  public void testSetAndGetText() {
    System.out.println("getText");
    TextRegion instance = new TextRegion();
    assertNull(instance.getText());
    String expResult = "text";
    instance.setText(expResult);
    String result = instance.getText();
    assertEquals(expResult, result);
    assertEquals(0.0f, instance.getConfidence(), 0.001f);
    assertNull(instance.getImageUrl());
    assertNull(instance.getOrder());
    assertNull(instance.getPageUrl());
    assertNull(instance.getResourceId());
    assertNull(instance.getRegion());
  }

  /**
   * Test of set/getText method, of class TextRegion.
   */
  @Test
  public void testToString() {
    System.out.println("toString");
    float confLevel = 0.7f;
    String order = "5";
    String region = "myRegion";
    String page = "myPage";
    String image = "myImage";
    String resource = "myResource";
    String text = "VeryLongText";
    TextRegion instance = new TextRegion();
    instance.setConfidence(confLevel);
    String result = instance.toString();
    assertTrue(result.contains(Float.toString(confLevel)));
    assertFalse(result.contains(order));
    assertFalse(result.contains(region));
    assertFalse(result.contains(page));
    assertFalse(result.contains(image));
    assertFalse(result.contains(resource));
    assertFalse(result.contains(text));
    instance.setOrder(order);
    result = instance.toString();
    assertTrue(result.contains(Float.toString(confLevel)));
    assertTrue(result.contains(order));
    assertFalse(result.contains(region));
    assertFalse(result.contains(page));
    assertFalse(result.contains(image));
    assertFalse(result.contains(resource));
    assertFalse(result.contains(text));
    instance.setRegion(region);
    result = instance.toString();
    assertTrue(result.contains(Float.toString(confLevel)));
    assertTrue(result.contains(order));
    assertTrue(result.contains(region));
    assertFalse(result.contains(page));
    assertFalse(result.contains(image));
    assertFalse(result.contains(resource));
    assertFalse(result.contains(text));
    instance.setPageUrl(page);
    result = instance.toString();
    assertTrue(result.contains(Float.toString(confLevel)));
    assertTrue(result.contains(order));
    assertTrue(result.contains(region));
    assertTrue(result.contains(page));
    assertFalse(result.contains(image));
    assertFalse(result.contains(resource));
    assertFalse(result.contains(text));
    instance.setImageUrl(image);
    result = instance.toString();
    assertTrue(result.contains(Float.toString(confLevel)));
    assertTrue(result.contains(order));
    assertTrue(result.contains(region));
    assertTrue(result.contains(page));
    assertTrue(result.contains(image));
    assertFalse(result.contains(resource));
    assertFalse(result.contains(text));
    instance.setResourceId(resource);
    result = instance.toString();
    assertTrue(result.contains(Float.toString(confLevel)));
    assertTrue(result.contains(order));
    assertTrue(result.contains(region));
    assertTrue(result.contains(page));
    assertTrue(result.contains(image));
    assertTrue(result.contains(resource));
    assertFalse(result.contains(text));

    instance.setText(text);
    result = instance.toString();
    assertTrue(result.contains(Float.toString(confLevel)));
    assertTrue(result.contains(order));
    assertTrue(result.contains(region));
    assertTrue(result.contains(page));
    assertTrue(result.contains(image));
    assertTrue(result.contains(resource));
    assertTrue(result.contains(text));
  }

}
