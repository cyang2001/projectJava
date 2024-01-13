package com.isep.eleve.javaproject.factory;

import java.math.BigDecimal;

import com.isep.eleve.javaproject.Tools.Constants.ASSET_TYPE;
import com.isep.eleve.javaproject.model.Asset;

/**
 * The AssetFactory interface represents a factory for creating assets.
 */
public interface AssetFactory {
  /**
   * Creates an asset with the specified parameters.
   *
   * @param assetName    the name of the asset
   * @param portfolioId  the ID of the portfolio
   * @param quantity     the quantity of the asset
   * @param price        the price of the asset
   * @param interestRate the interest rate of the asset
   * @param ownerId      the ID of the owner
   * @return the created asset
   */
  Asset createAsset(String assetName, int portfolioId, int quantity, BigDecimal price, BigDecimal interestRate, int ownerId, ASSET_TYPE assetType);
  
}
