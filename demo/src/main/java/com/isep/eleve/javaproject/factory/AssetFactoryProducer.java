package com.isep.eleve.javaproject.factory;

import java.util.Map;

import com.isep.eleve.javaproject.Tools.Constants.ASSET_TYPE;

/**
 * The AssetFactoryProducer class is responsible for producing asset factories based on the asset type.
 */
public class AssetFactoryProducer {

  private final Map<ASSET_TYPE, AssetFactory> factories;

  /**
   * Constructs an AssetFactoryProducer with the specified map of asset factories.
   *
   * @param factories the map of asset factories
   */
  public AssetFactoryProducer(Map<ASSET_TYPE, AssetFactory> factories) {
    this.factories = factories;
  }

  /**
   * Retrieves the asset factory based on the given asset type.
   *
   * @param assetType the asset type
   * @return the asset factory
   * @throws IllegalArgumentException if the asset type is unknown
   */
  public AssetFactory getFactory(ASSET_TYPE assetType) {
    AssetFactory factory = factories.get(assetType);
    if (factory == null) {
      throw new IllegalArgumentException("Unknown asset type: " + assetType);
    }
    return factory;
  }
}
