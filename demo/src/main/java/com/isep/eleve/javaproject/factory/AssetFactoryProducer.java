package com.isep.eleve.javaproject.factory;

import java.util.Map;

public class AssetFactoryProducer {

  private final Map<String, AssetFactory> factories;

  public AssetFactoryProducer(Map<String, AssetFactory> factories) {
    this.factories = factories;
  }
  public AssetFactory getFactory(String assetType) {
    AssetFactory factory = factories.get(assetType);
        if (factory == null) {
            throw new IllegalArgumentException("Unknown asset type: " + assetType);
        }
        return factory;
  }
}
