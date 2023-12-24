package com.isep.eleve.javaproject.factory;

import java.math.BigDecimal;

import com.isep.eleve.javaproject.model.Asset;
import com.isep.eleve.javaproject.model.assets.stock.Stock;

public class StockFactory implements AssetFactory{

  @Override
  public Asset createAsset(String assetName, int portfolioId, int quantity, BigDecimal price, BigDecimal interestRate, int ownerId) {
    return new Stock(assetName, portfolioId, quantity, price, ownerId);
  }
  
}
