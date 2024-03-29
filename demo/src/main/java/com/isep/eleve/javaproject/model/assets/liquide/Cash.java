
package com.isep.eleve.javaproject.model.assets.liquide;

import java.math.BigDecimal;

import com.isep.eleve.javaproject.Tools.Constants.ASSET_TYPE;
import com.isep.eleve.javaproject.model.Asset;


/**
 * Represents a cash asset.
 * Extends the LiquidAssets class.
 * @version 1.0
 * @see Assets
 * @author Chen YANG
 */
public class Cash extends Asset {
  /**
   * Constructs a Cash object with the specified parameters.
   *
   * @param assetName   the name of the asset
   * @param portfolioId the ID of the portfolio
   * @param quantity    the quantity of cash
   * @param price       the price of the cash
   * @param ownerId     the ID of the owner
   */
  public Cash(String assetName, int portfolioId, int quantity, BigDecimal price, int ownerId, ASSET_TYPE assetType) {
    super(assetName, portfolioId, quantity, price, ownerId, assetType);
  }

  /**
   * Calculates the value of the cash asset.
   * For cash, the value is simply the amount of cash available.
   */
  @Override
  public void calculateValue() {
    this.setValue(this.getPrice());
  }



}
