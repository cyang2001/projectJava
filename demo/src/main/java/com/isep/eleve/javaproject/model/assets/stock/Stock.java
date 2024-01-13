package com.isep.eleve.javaproject.model.assets.stock;

import java.math.BigDecimal;

import com.isep.eleve.javaproject.Tools.Constants.ASSET_TYPE;
import com.isep.eleve.javaproject.model.Asset;

public class Stock extends Asset{

  public Stock(String assetName, int portfolioId, int quantity, BigDecimal price, int ownerId, ASSET_TYPE assetType) {
    super(assetName, portfolioId, quantity, price, ownerId, assetType);
  }

  @Override
  public void calculateValue() {
    this.setValue(this.getPrice().multiply(new BigDecimal(this.getQuantity())));
  }
  
}
