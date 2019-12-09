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
package edu.kit.ocrd.workspace;

import com.github.jscancella.conformance.BagLinter;
import com.github.jscancella.domain.Bag;
import com.github.jscancella.domain.Manifest;
import com.github.jscancella.exceptions.CorruptChecksumException;
import com.github.jscancella.exceptions.FileNotInPayloadDirectoryException;
import com.github.jscancella.exceptions.InvalidBagitFileFormatException;
import com.github.jscancella.exceptions.InvalidPayloadOxumException;
import com.github.jscancella.exceptions.MaliciousPathException;
import com.github.jscancella.exceptions.MissingBagitFileException;
import com.github.jscancella.exceptions.MissingPayloadDirectoryException;
import com.github.jscancella.exceptions.MissingPayloadManifestException;
import com.github.jscancella.exceptions.PayloadOxumDoesNotExistException;
import com.github.jscancella.exceptions.UnparsableVersionException;
import com.github.jscancella.hash.BagitChecksumNameMapping;
import com.github.jscancella.hash.Hasher;
import com.github.jscancella.hash.StandardHasher;
import com.github.jscancella.reader.BagReader;
import com.github.jscancella.verify.BagVerifier;
import com.github.jscancella.writer.BagWriter;
import com.github.jscancella.writer.internal.BagCreator;
import com.github.jscancella.writer.internal.CreateTagManifestsVistor;
import com.github.jscancella.writer.internal.ManifestWriter;
import com.github.jscancella.writer.internal.MetadataWriter;
import edu.kit.ocrd.exception.BagItException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility handling BagIt containers.
 */
public class BagItUtil {

  /**
   * Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(BagItUtil.class);
  /**
   * Error message multiple OCR-D identifier
   */
  public static final String MULTIPLE_OCR_D_IDENTIFIER = "Error: Multiple OCRD identifiers defined!";
  /**
   * Error message multiple OCR-D identifier
   */
  public static final String MULTIPLE_METS_LOCATIONS = "Error: Multiple METS locations defined!";
  /**
   * Key inside Bagit container defining OCRD identifier.
   */
  public static final String X_OCRD_IDENTIFIER = "Ocrd-Identifier";
  /**
   * Key inside Bagit container defining location of the METS file.
   */
  public static final String X_OCRD_METS = "Ocrd-Mets";
  /**
   * Default location of the METS file.
   */
  public static final String BAG_SOFTWARE_AGENT = "Bag-Software-Agent";
  /**
   * Default location of the METS file.
   */
  public static final String METS_LOCATION_DEFAULT = "data/mets.xml";
  /**
   * Key for profiler file.
   */
  public static final String PROFILE_IDENTIFIER = "BagIt-Profile-Identifier";
  /**
   * Default location of profiler.
   */
  public static final String PROFILE_IDENTIFIER_LOCATION = "https://ocr-d.github.io/bagit-profile.json";
  /**
   * Manifestation Depth
   */
  public static final String OCRD_MANIFESTATION = "Ocrd-Manifestation-Depth";
  /**
   * Default location of profiler.
   */
  public static final String OCRD_MANIFESTATION_DEFAULT = "partial";

  /**
   * Build BagIt container of a payload directory.
   *
   * @param payLoadPath Path to payload directory.
   * @param pathToMetadataDir Path to metadata directory.
   * @param ocrdIdentifier OCR-D Identifier of the bag.
   *
   * @return Bag of directory.
   * @throws BagItException Error building bag.
   */
  public static Bag buildBag(final File payLoadPath, final File pathToMetadataDir, final String ocrdIdentifier) throws BagItException {
    Bag bag = null;
    try {
      Path folder = Paths.get(payLoadPath.getAbsolutePath());
      List<String> algorithms = Arrays.asList(StandardHasher.SHA512.getBagitAlgorithmName());
      boolean includeHiddenFiles = false;
      bag = BagCreator.bagInPlace(folder, algorithms, includeHiddenFiles);
      bag.getMetadata().add(X_OCRD_IDENTIFIER, ocrdIdentifier);
      bag.getMetadata().add(PROFILE_IDENTIFIER, PROFILE_IDENTIFIER_LOCATION);
      bag.getMetadata().add(X_OCRD_METS, METS_LOCATION_DEFAULT);
      bag.getMetadata().add(OCRD_MANIFESTATION, OCRD_MANIFESTATION_DEFAULT);
      String softwareAgent = String.format("BagItUtil %s from path '%s' with identifier '%s'",
              new BagItUtil().getClass().getPackage().getImplementationVersion(), payLoadPath.getPath(), ocrdIdentifier);
      bag.getMetadata().add(BAG_SOFTWARE_AGENT, softwareAgent);
      addTagDirectory(bag, pathToMetadataDir);
    } catch (NoSuchAlgorithmException | IOException ex) {
      LOGGER.error("Can't create Bag!", ex);
      throw new BagItException(ex.getMessage());
    }
    return bag;
  }

  /**
   * Build BagIt container of a payload directory.
   *
   * @param payLoadPath Path to payload directory.
   *
   * @return Bag of directory.
   * @throws BagItException Error building bag.
   */
  public static Bag buildBag(final File payLoadPath) throws BagItException {
    return buildBag(payLoadPath, UUID.randomUUID().toString());
  }

  /**
   * Build BagIt container of a payload directory.
   *
   * @param payLoadPath Path to payload directory.
   * @param ocrdIdentifier Identifier for the document.
   *
   * @return Bag of directory.
   * @throws BagItException Error building bag.
   */
  public static Bag buildBag(final File payLoadPath, final String ocrdIdentifier) throws BagItException {
    return buildBag(payLoadPath, null, ocrdIdentifier);
  }

  /**
   * Creating BagIt container of a bagIt directory.
   *
   * @param pathToBag Path to bagIt directory.
   *
   * @return Bag of directory.
   * @throws BagItException Error reading bag.
   */
  public static Bag readBag(final Path pathToBag) throws BagItException {
    LOGGER.debug("Read BagIt...");
    Bag bag = null;
    try {
      bag = BagReader.read(pathToBag);
    } catch (IOException | UnparsableVersionException | MaliciousPathException | InvalidBagitFileFormatException ex) {
      LOGGER.error("Can't read Bag!", ex);
      throw new BagItException(ex.getMessage());
    }
    validateBagit(bag);

    return bag;
  }

  /**
   * Validate BagIt container.
   *
   * @param bag Bag to validate.
   *
   * @return true or false
   * @throws BagItException Error validating bag.
   */
  public static boolean validateBagit(final Bag bag) throws BagItException {
    boolean valid = true;
    LOGGER.debug("Validate Bag!");
    try {
      BagVerifier.quicklyVerify(bag);
    } catch (IOException | InvalidPayloadOxumException | PayloadOxumDoesNotExistException ex) {
      LOGGER.error("PayLoadOxum is invalid: ", ex);
      throw new BagItException(ex.getMessage());
    }
    /////////////////////////////////////////////////////////////////
    // Check for Profile and validate it
    /////////////////////////////////////////////////////////////////
    List<String> url2Profile = bag.getMetadata().get("BagIt-Profile-Identifier");
    Iterator<String> profileIterator = url2Profile.iterator();
    try {
      if (profileIterator.hasNext()) {
        InputStream inputStream4Profile = new URL(profileIterator.next()).openStream();
        BagLinter.checkAgainstProfile(inputStream4Profile, bag);
      }
    } catch (Exception ex) {
      LOGGER.error("Container does not match the defined profile!", ex);
      throw new BagItException(ex.getMessage());
    }
    /////////////////////////////////////////////////////////////////
    // Verify completeness
    /////////////////////////////////////////////////////////////////
    boolean ignoreHiddenFiles = false;
    try {
      BagVerifier.isComplete(bag, ignoreHiddenFiles);
    } catch (IOException | MissingPayloadManifestException | MissingBagitFileException | MissingPayloadDirectoryException | FileNotInPayloadDirectoryException | MaliciousPathException | InvalidBagitFileFormatException ex) {
      LOGGER.error("Bag is not complete!", ex);
      throw new BagItException(ex.getMessage());
    }
    /////////////////////////////////////////////////////////////////
    // Verify validity
    /////////////////////////////////////////////////////////////////
    try {
      BagVerifier.isValid(bag, ignoreHiddenFiles);
    } catch (IOException | MissingPayloadManifestException | MissingBagitFileException | MissingPayloadDirectoryException | FileNotInPayloadDirectoryException | MaliciousPathException | CorruptChecksumException | InvalidBagitFileFormatException | NoSuchAlgorithmException ex) {
      LOGGER.error("Bag is not valid!", ex);
      throw new BagItException(ex.getMessage());
    }
    printBagItInformation(bag);
    return valid;
  }

  /**
   * Print some information about the BagIt container.
   *
   * @param bag Instance holding BagIt container.
   */
  public static void printBagItInformation(final Bag bag) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("Version: {}", bag.getVersion());
      bag.getMetadata().getAll().forEach((entry) -> {
        LOGGER.debug("{} : {}", entry.getKey(), entry.getValue());
      });
    }
  }

  /**
   * Determines the path to the METS file.
   *
   * @param bag BagIt container.
   *
   * @return Relative path to METS file.
   */
  public static String getPathToMets(final Bag bag) throws BagItException {
    List<String> listOfEntries = bag.getMetadata().get(X_OCRD_METS);
    String pathToMets = METS_LOCATION_DEFAULT;
    if (listOfEntries != null) {
      if (listOfEntries.size() > 1) {
        LOGGER.error("There are multiple location for METS defined!");
        for (String item : listOfEntries) {
          LOGGER.warn("Found: {}", item);
        }
        throw new BagItException(MULTIPLE_METS_LOCATIONS);
      }
      pathToMets = listOfEntries.get(0);
    }
    LOGGER.trace("Path to METS: {}", pathToMets);
    return pathToMets;
  }

  /**
   * Determines the path to the METS file.
   *
   * @param bag BagIt container.
   *
   * @return Relative path to METS file.
   */
  public static String getOcrdIdentifierOfBag(final Bag bag) throws BagItException {
    List<String> listOfEntries = bag.getMetadata().get(X_OCRD_IDENTIFIER);
    String ocrdIdentifier = null;
    if (listOfEntries != null) {
      if (listOfEntries.size() > 1) {
        LOGGER.error("There are multiple OCRD identifiers defined!");
        for (String item : listOfEntries) {
          LOGGER.warn("Found: {}", item);
        }
        throw new BagItException(MULTIPLE_OCR_D_IDENTIFIER);
      }
      ocrdIdentifier = listOfEntries.get(0);
    }
    LOGGER.trace("OCRD identifier: {}", ocrdIdentifier);
    return ocrdIdentifier;
  }

  /**
   * Add a directory to bag as tag directory.
   *
   * Copy directory in bag.
   *
   * @param bag Bag
   * @param tagDirectory directory to add.
   */
  public static void addTagDirectory(Bag bag, final File tagDirectory) throws NoSuchAlgorithmException, IOException {
    Path bagitRootPath = bag.getRootDir();
    if (tagDirectory != null) {
      LOGGER.trace("addTagDir '{}' to bag '{}'", tagDirectory.getPath(), bag.getRootDir().toString());

      FileUtils.copyDirectory(tagDirectory, Paths.get(bagitRootPath.toString(), tagDirectory.getName()).toFile());
    }
    boolean includeHiddenFiles = false;
    List<String> algorithms = new ArrayList<>();
    for (Manifest manifest : bag.getPayLoadManifests()) {
      algorithms.add(manifest.getBagitAlgorithmName());
    }
    final Map<Manifest, Hasher> tagFilesMap = new ConcurrentHashMap<>();

    for (final String algorithm : algorithms) {
      final Manifest manifest = new Manifest(algorithm);
      final Hasher hasher = BagitChecksumNameMapping.get(algorithm);
      tagFilesMap.put(manifest, hasher);
    }
    final CreateTagManifestsVistor tagVistor = new CreateTagManifestsVistor(tagFilesMap, includeHiddenFiles);
    Files.walkFileTree(bagitRootPath, tagVistor);
    // Remove all tagmanifest... files. They are not allowed in tagmanifest files.
    for (Manifest key : tagFilesMap.keySet()) {
      Set<Path> removeFromFileToChecksumMap = new HashSet<>();
      Map<Path, String> fileToChecksumMap = key.getFileToChecksumMap();
      for (Path item : fileToChecksumMap.keySet()) {
        if (item.toString().startsWith(Paths.get(bagitRootPath.toString(), "tagmanifest-").toString())) {
          removeFromFileToChecksumMap.add(item);
        }
      }
      for (Path removeItem : removeFromFileToChecksumMap) {
        fileToChecksumMap.remove(removeItem);
      }
    }
    // Clear existing tag manifests first.
    bag.getTagManifests().clear();
    // Add the new ones.
    bag.getTagManifests().addAll(tagFilesMap.keySet());
    ManifestWriter.writePayloadManifests(bag.getPayLoadManifests(), bag.getRootDir(), bag.getRootDir(), bag.getFileEncoding());
    MetadataWriter.writeBagMetadata(bag.getMetadata(), bag.getVersion(), bag.getRootDir(), bag.getFileEncoding());
    ManifestWriter.writeTagManifests(bag.getTagManifests(), bag.getRootDir(), bag.getRootDir(), bag.getFileEncoding());
  }

}
