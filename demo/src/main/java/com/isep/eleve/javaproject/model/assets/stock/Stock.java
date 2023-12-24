package com.isep.eleve.javaproject.model.assets.stock;

import java.math.BigDecimal;

import com.isep.eleve.javaproject.model.Asset;

public class Stock extends Asset{

  public Stock(String assetName, int portfolioId, int quantity, BigDecimal price, int ownerId) {
    super(assetName, portfolioId, quantity, price, ownerId);
  }

  @Override
  public void calculateValue() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'calculateValue'");
  }
  
}
