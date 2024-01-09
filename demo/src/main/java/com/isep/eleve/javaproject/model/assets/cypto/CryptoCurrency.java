package com.isep.eleve.javaproject.model.assets.cypto;

import java.math.BigDecimal;

import com.isep.eleve.javaproject.model.Asset;

public class CryptoCurrency extends Asset{

  public CryptoCurrency(String assetName, int portfolioId, int quantity, BigDecimal price, int ownerId) {
    super(assetName, portfolioId, quantity, price, ownerId);
    
  }

  @Override
  public void calculateValue() {
    BigDecimal value = this.getPrice().multiply(BigDecimal.valueOf(this.getQuantity()));
    this.setValue(value);
  }
}
