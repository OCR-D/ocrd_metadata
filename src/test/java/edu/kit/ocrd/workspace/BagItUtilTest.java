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
package edu.kit.ocrd.workspace;

import com.github.jscancella.domain.Bag;
import com.github.jscancella.exceptions.InvalidBagitFileFormatException;
import com.github.jscancella.exceptions.MaliciousPathException;
import com.github.jscancella.exceptions.UnparsableVersionException;
import com.github.jscancella.reader.BagReader;
import com.github.jscancella.writer.BagWriter;
import edu.kit.ocrd.exception.BagItException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * Test BagItUtil.
 */
public class BagItUtilTest {

  public BagItUtilTest() {
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
   * Test of buildBag method, of class BagItUtil.
   */
  @Test
  public void testBuildBag() {
    System.out.println("buildBagInvalidDirectory");
    File payLoadPath = new File("/file/not/exists.ocrd.zip");
    try {
      Bag result = BagItUtil.buildBag(payLoadPath);
      assertTrue(false);
    } catch (BagItException bie) {
      assertTrue(bie.getMessage().contains("/file/not/exists"));
    }
  }

  /**
   * Test of buildBag method, of class BagItUtil.
   */
  @Test
  public void testBuildBag1() throws IOException {
    System.out.println("buildBag");
    File testDir = new File("src/test/resources/bagit/test");
    File srcDir = new File("src/test/resources/bagit/data");
    FileUtils.copyDirectory(srcDir, testDir);
    try {
      Bag result = BagItUtil.buildBag(testDir);
      Stream<Path> list = Files.list(testDir.toPath());
      assertEquals(5, list.count());
      assertTrue(true);
    } catch (BagItException bie) {
      assertTrue(false);
    }
    FileUtils.deleteDirectory(testDir);
  }

  /**
   * Test of buildBag method, of class BagItUtil.
   */
  @Test
  public void testBuildBag2() throws IOException, NoSuchAlgorithmException {
    System.out.println("buildBagWithMetadataDir");
    File testDir = new File("src/test/resources/bagit/test");
    File writeDir = new File("src/test/resources/bagit/write");
    File metadataDir = new File("src/test/resources/provenance");
    File srcDir = new File("src/test/resources/bagit/data");
    FileUtils.copyDirectory(srcDir, testDir);
    try {
      Bag result = BagItUtil.buildBag(testDir, metadataDir, "anyIdentifier");
      BagWriter.write(result, writeDir.toPath());
      Stream<Path> list = Files.list(writeDir.toPath());
      assertTrue(Paths.get(writeDir.getAbsolutePath(), "tagmanifest-sha512.txt").toFile().exists());
      assertEquals(6, list.count());
      List<String> readLines = FileUtils.readLines(Paths.get(writeDir.getAbsolutePath(), "tagmanifest-sha512.txt").toFile(), Charset.defaultCharset());
      assertEquals(6, readLines.size());
      boolean valid = false;
      for (String line: readLines) {
        if (line.contains("bagit.txt"))
          valid = true;
      }
      assertTrue(valid);
      valid = false;
      for (String line: readLines) {
        if (line.contains("bag-info.txt"))
          valid = true;
      }
      assertTrue(valid);
      valid = false;
      for (String line: readLines) {
        if (line.contains("manifest-sha512.txt"))
          valid = true;
      }
      assertTrue(valid);
      valid = false;
      for (String line: readLines) {
        if (line.contains("invalid_date_ocrd_provenance.xml"))
          valid = true;
      }
      assertTrue(valid);
      valid = false;
      for (String line: readLines) {
        if (line.contains("mets.xml"))
          valid = true;
      }
      assertTrue(valid);
      valid = false;
      for (String line: readLines) {
        if (line.contains("ocrd_provenance.xml"))
          valid = true;
      }
      assertTrue(valid);
      assertTrue(BagItUtil.validateBagit(result));
    } catch (BagItException bie) {
      assertTrue(false);
    }
    FileUtils.deleteDirectory(testDir);
    FileUtils.deleteDirectory(writeDir);
  }

  /**
   * Test of buildBag method, of class BagItUtil.
   */
  @Test
  public void testBuildBag3() throws IOException {
    System.out.println("buildBagWithInvalidMetadataDir");
    File testDir = new File("src/test/resources/bagit/test");
    File metadataDir = new File("src/test/resources/notExists");
    File srcDir = new File("src/test/resources/bagit/data");
    FileUtils.copyDirectory(srcDir, testDir);
    try {
      Bag result = BagItUtil.buildBag(testDir, metadataDir, "anyIdentifier");
      assertTrue(false);
    } catch (BagItException bie) {
      assertTrue(bie.getMessage().contains("src/test/resources/notExists"));
    }
    FileUtils.deleteDirectory(testDir);
  }

  /**
   * Test of readBag method, of class BagItUtil.
   */
  @Test
  public void testReadValidBag() {
    System.out.println("readValidBag");
    Path pathToBag = Paths.get("src/test/resources/bagit/", "validBag");
    Bag result = BagItUtil.readBag(pathToBag);
    assertTrue(true);
  }

  /**
   * Test of readBag method, of class BagItUtil.
   */
  @Test
  public void testReadInvalidBag1() {
    System.out.println("readInvalidBag1");

    try {
      Path pathToBag = Paths.get("src/test/resources/bagit/", "invalidChecksum");
      Bag result = BagItUtil.readBag(pathToBag);
      assertTrue(false);
    } catch (BagItException bie) {
      assertTrue(bie.getMessage().contains("invalidChecksum/data/mets.xml"));
    }
  }

  /**
   * Test of readBag method, of class BagItUtil.
   */
  @Test
  public void testReadInvalidBag2() {
    System.out.println("readInvalidBag2");

    try {
      Path pathToBag = Paths.get("src/test/resources/bagit/", "invalidOxum");
      Bag result = BagItUtil.readBag(pathToBag);
      assertTrue(false);
    } catch (BagItException bie) {
      assertTrue(bie.getMessage().contains("[18778]"));
    }
  }

  /**
   * Test of readBag method, of class BagItUtil.
   */
  @Test
  public void testReadInvalidBag3() {
    System.out.println("readInvalidBag3");

    try {
      Path pathToBag = Paths.get("src/test/resources/bagit/", "invalidVersion");
      Bag result = BagItUtil.readBag(pathToBag);
      assertTrue(false);
    } catch (BagItException bie) {
      assertTrue(bie.getMessage().contains("1.1"));
    }
  }

  /**
   * Test of readBag method, of class BagItUtil.
   */
  @Test
  public void testReadInvalidBag4() {
    System.out.println("readInvalidBag4");

    try {
      Path pathToBag = Paths.get("src/test/resources/bagit/", "notComplete");
      Bag result = BagItUtil.readBag(pathToBag);
      assertTrue(false);
    } catch (BagItException bie) {
      assertTrue(bie.getMessage().contains("notComplete/data/mets.xml"));
    }
  }

  /**
   * Test of readBag method, of class BagItUtil.
   */
  @Test
  public void testReadInvalidBag5() {
    System.out.println("readInvalidBag5");

    try {
      Path pathToBag = Paths.get("src/test/resources/bagit/", "notExists");
      Bag result = BagItUtil.readBag(pathToBag);
      assertTrue(false);
    } catch (BagItException bie) {
      assertTrue(bie.getMessage().contains("notExists/bagit.txt"));
    }
  }
//
//  /**
//   * Test of validateBagit method, of class BagItUtil.
//   */
//  @Test
//  public void testValidateBagit() {
//    System.out.println("validateBagit");
//    Bag bag = null;
//    boolean expResult = false;
//    boolean result = BagItUtil.validateBagit(bag);
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }

  /**
   * Test of printBagItInformation method, of class BagItUtil.
   */
  @Test
  public void testPrintBagItInformation() {
    System.out.println("printBagItInformation");
    Path pathToBag = Paths.get("src/test/resources/bagit/", "validBag");
    Bag bag = BagItUtil.readBag(pathToBag);
    BagItUtil.printBagItInformation(bag);
    assertTrue(true);
  }

  /**
   * Test of printBagItInformation method, of class BagItUtil.
   */
  @Test
  public void testBagItUtilClass() {
    System.out.println("create Instance");
    BagItUtil util = new BagItUtil();
    assertNotNull(util);
  }

  /**
   * Test of reading parameter from bag-info.txt, of class BagItUtil.
   */
  @Test
  public void testGetPathToMets() {
    System.out.println("testGetPathToMets");
    Path pathToBag = Paths.get("src/test/resources/bagit/", "pathToMets");
    Bag bag = BagItUtil.readBag(pathToBag);
    String pathToMets = BagItUtil.getPathToMets(bag);
    assertEquals("data/alternateMets.xml", pathToMets);
    pathToBag = Paths.get("src/test/resources/bagit/", "twoMetsWithProfile");
    try {
      bag = BagReader.read(pathToBag);
    } catch (IOException | UnparsableVersionException | MaliciousPathException | InvalidBagitFileFormatException ex) {
      throw new BagItException(ex.getMessage());
    }
    try {
      BagItUtil.getPathToMets(bag);
      assertTrue(Boolean.FALSE);
    } catch (BagItException bie) {
      assertEquals(bie.getMessage(), BagItUtil.MULTIPLE_METS_LOCATIONS);
      assertTrue(Boolean.TRUE);
    }
  }

  /**
   * Test of reading parameter from bag-info.txt, of class BagItUtil.
   */
  @Test
  public void testGetXOcrdIdentifier() {
    System.out.println("testGetXOcrdIdentifier");
    Path pathToBag = Paths.get("src/test/resources/bagit/", "validBag");
    Bag bag = BagItUtil.readBag(pathToBag);
    String ocrdIdentifierOfBag = BagItUtil.getOcrdIdentifierOfBag(bag);
    assertEquals("bagForTest", ocrdIdentifierOfBag);
    pathToBag = Paths.get("src/test/resources/bagit/", "twoOcrdIdentifier");
    try {
      bag = BagReader.read(pathToBag);
    } catch (IOException | UnparsableVersionException | MaliciousPathException | InvalidBagitFileFormatException ex) {
      throw new BagItException(ex.getMessage());
    }
    try {
      BagItUtil.getOcrdIdentifierOfBag(bag);
      assertTrue(Boolean.FALSE);
    } catch (BagItException bie) {
      assertEquals(bie.getMessage(), BagItUtil.MULTIPLE_OCR_D_IDENTIFIER);
      assertTrue(Boolean.TRUE);
    }
  }
}
