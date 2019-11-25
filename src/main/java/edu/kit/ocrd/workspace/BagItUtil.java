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

import edu.kit.ocrd.exception.BagItException;
import gov.loc.repository.bagit.conformance.BagLinter;
import gov.loc.repository.bagit.creator.BagCreator;
import gov.loc.repository.bagit.creator.CreateTagManifestsVistor;
import gov.loc.repository.bagit.domain.Bag;
import gov.loc.repository.bagit.domain.Manifest;
import gov.loc.repository.bagit.exceptions.CorruptChecksumException;
import gov.loc.repository.bagit.exceptions.FileNotInPayloadDirectoryException;
import gov.loc.repository.bagit.exceptions.InvalidBagitFileFormatException;
import gov.loc.repository.bagit.exceptions.InvalidPayloadOxumException;
import gov.loc.repository.bagit.exceptions.MaliciousPathException;
import gov.loc.repository.bagit.exceptions.MissingBagitFileException;
import gov.loc.repository.bagit.exceptions.MissingPayloadDirectoryException;
import gov.loc.repository.bagit.exceptions.MissingPayloadManifestException;
import gov.loc.repository.bagit.exceptions.UnparsableVersionException;
import gov.loc.repository.bagit.exceptions.UnsupportedAlgorithmException;
import gov.loc.repository.bagit.exceptions.VerificationException;
import gov.loc.repository.bagit.hash.Hasher;
import gov.loc.repository.bagit.hash.StandardSupportedAlgorithms;
import gov.loc.repository.bagit.hash.SupportedAlgorithm;
import gov.loc.repository.bagit.reader.BagReader;
import gov.loc.repository.bagit.util.PathUtils;
import gov.loc.repository.bagit.verify.BagVerifier;
import gov.loc.repository.bagit.writer.BagWriter;
import gov.loc.repository.bagit.writer.ManifestWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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
      List<SupportedAlgorithm> algorithms = Arrays.asList(StandardSupportedAlgorithms.SHA512);
      boolean includeHiddenFiles = false;
      bag = BagCreator.bagInPlace(folder, algorithms, includeHiddenFiles);
      bag.getMetadata().add(X_OCRD_IDENTIFIER, ocrdIdentifier);
      bag.getMetadata().add(PROFILE_IDENTIFIER, PROFILE_IDENTIFIER_LOCATION);
      bag.getMetadata().add(X_OCRD_METS, METS_LOCATION_DEFAULT);
      String softwareAgent = System.out.printf("BagItUtil %s from path '%s' with idenifier '%s'",
              new BagItUtil().getClass().getPackage().getImplementationVersion(), payLoadPath.getPath(), ocrdIdentifier).toString();
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
    BagReader reader = new BagReader();
    try {
      bag = reader.read(pathToBag);
    } catch (IOException | UnparsableVersionException | MaliciousPathException | UnsupportedAlgorithmException | InvalidBagitFileFormatException ex) {
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
    if (BagVerifier.canQuickVerify(bag)) {
      try {
        BagVerifier.quicklyVerify(bag);
      } catch (IOException | InvalidPayloadOxumException ex) {
        LOGGER.error("PayLoadOxum is invalid: ", ex);
        throw new BagItException(ex.getMessage());
      }
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
    BagVerifier verifierCompleteness = new BagVerifier();
    try {
      verifierCompleteness.isComplete(bag, ignoreHiddenFiles);
    } catch (IOException | MissingPayloadManifestException | MissingBagitFileException | MissingPayloadDirectoryException | FileNotInPayloadDirectoryException | InterruptedException | MaliciousPathException | UnsupportedAlgorithmException | InvalidBagitFileFormatException ex) {
      LOGGER.error("Bag is not complete!", ex);
      throw new BagItException(ex.getMessage());
    }
    /////////////////////////////////////////////////////////////////
    // Verify validity
    /////////////////////////////////////////////////////////////////
    BagVerifier verifierValidity = new BagVerifier();
    
    try {
      verifierValidity.isValid(bag, ignoreHiddenFiles);
    } catch (IOException | MissingPayloadManifestException | MissingBagitFileException | MissingPayloadDirectoryException | FileNotInPayloadDirectoryException | InterruptedException | MaliciousPathException | CorruptChecksumException | VerificationException | UnsupportedAlgorithmException | InvalidBagitFileFormatException ex) {
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
    if (tagDirectory != null) {
      Path bagitRootPath = bag.getRootDir();
      boolean includeHiddenFiles = false;
      List<SupportedAlgorithm> algorithms = new ArrayList<>();
      for (Manifest manifest : bag.getPayLoadManifests()) {
        algorithms.add(manifest.getAlgorithm());
      }
      final Map<Manifest, MessageDigest> tagFilesMap = Hasher.createManifestToMessageDigestMap(algorithms);
      final CreateTagManifestsVistor tagVistor = new CreateTagManifestsVistor(tagFilesMap, includeHiddenFiles);
      BagItUtil.copyFolder(tagDirectory, Paths.get(bagitRootPath.toString(), tagDirectory.getName()).toFile());
      Files.walkFileTree(bagitRootPath, tagVistor);
      // Remove all tagmanifest... files. They are not allowed in tagmanifest files.
       for (Manifest key : tagFilesMap.keySet()) {
        System.out.println(key);
        Map<Path, String> newFileToChecksumMap = new HashMap<>();
        Map<Path, String> fileToChecksumMap = key.getFileToChecksumMap();
        for (Path item : fileToChecksumMap.keySet()) {
          if (!item.toString().startsWith(Paths.get(bagitRootPath.toString(), "tagmanifest").toString())) {
            newFileToChecksumMap.put(item, fileToChecksumMap.get(item));
          }
          key.setFileToChecksumMap(newFileToChecksumMap);
        }
        
      }
      bag.getTagManifests().addAll(tagFilesMap.keySet());
      ManifestWriter.writeTagManifests(bag.getTagManifests(), PathUtils.getBagitDir(bag), bag.getRootDir(), bag.getFileEncoding());
    }
    
  }

  /**
   * This function recursively copy all the sub folder and files from
   * sourceFolder to destinationFolder
   *
   */
  private static void copyFolder(File sourceFolder, File destinationFolder) throws IOException {
    //Check if sourceFolder is a directory or file
    //If sourceFolder is file; then copy the file directly to new location
    if (sourceFolder.isDirectory()) {
      //Verify if destinationFolder is already present; If not then create it
      if (!destinationFolder.exists()) {
        destinationFolder.mkdir();
        System.out.println("Directory created :: " + destinationFolder);
      }

      //Get all files from source directory
      String files[] = sourceFolder.list();

      //Iterate over all files and copy them to destinationFolder one by one
      for (String file : files) {
        File srcFile = new File(sourceFolder, file);
        File destFile = new File(destinationFolder, file);

        //Recursive function call
        copyFolder(srcFile, destFile);
      }
    } else {
      //Copy the file content from one place to another 
      Files.copy(sourceFolder.toPath(), destinationFolder.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
      System.out.println("File copied :: " + destinationFolder);
    }
  }
  
}
