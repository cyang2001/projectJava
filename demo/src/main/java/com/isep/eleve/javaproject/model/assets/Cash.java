
package com.isep.eleve.javaproject.model.assets;

import java.math.BigDecimal;

import com.isep.eleve.javaproject.Tools.Constants.ASSET_TYPE;

import ch.qos.logback.core.joran.action.Action;

/**
 * Represents a cash asset.
 * Extends the LiquidAssets class.
 * @version 1.0
 * @see LiquidAssets
 * @author Chen YANG
 */
public class Cash extends LiquidAssets {
  /**
   * Constructs a Cash object with the specified parameters.
   *
   * @param assetName   the name of the asset
   * @param portfolioId the ID of the portfolio
   * @param quantity    the quantity of cash
   * @param price       the price of the cash
   * @param ownerId     the ID of the owner
   */
  public Cash(String assetName, int portfolioId, int quantity, BigDecimal price, int ownerId) {
    super(assetName, portfolioId, quantity, price, ownerId);
    this.setAssetType(ASSET_TYPE.CASH);
  }

  /**
   * Calculates the value of the cash asset.
   * For cash, the value is simply the amount of cash available.
   */
  @Override
  public void calculateValue() {
    this.setValue(this.getPrice());
  }

  public Enum changeType{


  }

  public void change(BigDecimal price, ) {
    
  }


}
