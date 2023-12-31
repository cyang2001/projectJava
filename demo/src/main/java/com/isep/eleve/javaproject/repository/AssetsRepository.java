package com.isep.eleve.javaproject.repository;

import java.io.File;

import java.io.IOException;

import java.util.List;
import java.util.stream.Collectors;
import com.isep.eleve.javaproject.Tools.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.isep.eleve.javaproject.model.Asset;

/**
 * Asset repository
 * @version V1.3
 * @author Chen YANG
 */
@Repository
public class AssetsRepository {
  private static final String EXTERNAL_FILE_PATH = System.getProperty("user.home") + File.separator + "App" + File.separator + "databaseAssets.json";
  private final FileOperation fileOperation;

  /**
   * Constructs a new AssetsRepository with the specified FileOperation.
   *
   * @param fileOperation the FileOperation used for file operations.
   */
  @Autowired
  public AssetsRepository(FileOperation fileOperation) {
    this.fileOperation = fileOperation;
  }

  /**
   * Retrieves all assets from the file.
   *
   * @return a list of Asset objects.
   * @throws IOException if an I/O error occurs.
   */
  public List<Asset> findAll() throws IOException {
    return fileOperation.readListFromFile(EXTERNAL_FILE_PATH, Asset.class);
  }

  /**
   * Saves the specified asset to the file.
   *
   * @param asset the asset to be saved.
   * @throws IOException if an I/O error occurs.
   */
  public void save(Asset asset) throws IOException {
    List<Asset> assets = findAll();
    assets.add(asset);
    fileOperation.writeListToFile(EXTERNAL_FILE_PATH, assets);
  }

  /**
   * Retrieves the asset with the specified assetId from the file.
   *
   * @param assetId the ID of the asset to be retrieved.
   * @return the Asset object with the specified assetId, or null if not found.
   * @throws IOException if an I/O error occurs.
   */
  public Asset findByAssetId(int assetId) throws IOException {
    List<Asset> assets = findAll();
    return assets.stream().filter(a -> a.getAssetId() == assetId).findFirst().orElse(null);
  }

  /**
   * Retrieves all assets owned by the specified ownerId from the file.
   *
   * @param ownerId the ID of the owner.
   * @return a list of Asset objects owned by the specified ownerId.
   * @throws IOException if an I/O error occurs.
   */
  public List<Asset> findByOwnerId(int ownerId) throws IOException {
    List<Asset> assets = findAll();
    return assets.stream().filter(a -> a.getOwnerId() == ownerId).collect(Collectors.toList());
  }
  
  /**
   * Returns the external file path where the assets are stored.
   *
   * @return the external file path.
   */
  public String getExternalFilePath() {
    return EXTERNAL_FILE_PATH;
  }
}
