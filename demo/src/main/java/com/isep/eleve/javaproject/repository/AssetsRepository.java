package com.isep.eleve.javaproject.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.isep.eleve.javaproject.model.Asset;

@Repository
public class AssetsRepository {
  private static final String EXTERNAL_FILE_PATH = System.getProperty("user.home") + File.separator + "App" + File.separator + "databaseAssets.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Find all assets
     * @return List<Asset>
     * @throws IOException
     */
    public List<Asset> findAll() throws IOException {
        File file = new File(EXTERNAL_FILE_PATH);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                return objectMapper.readValue(fis, TypeFactory.defaultInstance().constructCollectionType(List.class, Asset.class));
            }
        }
        return new ArrayList<>();
    }
    /**
     * Save asset
     * @param portfolio
     * @throws IOException
     */
    public void save(Asset asset) throws IOException {
        List<Asset> assets = findAll();
        assets.add(asset);
        writeAssetsToFile(assets);
    }
    /**
     * Find asset by asset id
     * @param assetId
     * @return asset
     * @throws IOException
     */
    public Asset findByAssetId(int assetId) throws IOException {
        List<Asset> assets = findAll();
        return assets.stream().filter(a -> a.getAssetId() == assetId).findFirst().orElse(null);
    }
    /**
     * Find assets by owner id
     * @param ownerId
     * @return List<Asset>
     * @throws IOException
     */
    public List<Asset> findByOwnerId(int ownerId) throws IOException {
        List<Asset> assets = findAll();
        return assets.stream().filter(a -> a.getOwnerId() == ownerId).collect(Collectors.toList());
    }
    private void writeAssetsToFile(List<Asset> assets) throws IOException {
        File file = new File(EXTERNAL_FILE_PATH);
        File parentDir = file.getParentFile();
        if (!parentDir.exists() && !parentDir.mkdirs()) {
            throw new IOException("Failed to create directory: " + parentDir);
        }
        try (FileOutputStream fos = new FileOutputStream(file)) {
            objectMapper.writeValue(fos, assets);
        }
    }
}
