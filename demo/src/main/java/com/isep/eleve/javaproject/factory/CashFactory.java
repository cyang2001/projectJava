package com.isep.eleve.javaproject.factory;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.isep.eleve.javaproject.model.Asset;
import com.isep.eleve.javaproject.model.assets.Cash;

@Component
public class CashFactory implements AssetFactory{

  @Override
  public Asset createAsset(int portfolioId, int quantity, BigDecimal price, BigDecimal interestRate) {
    return new Cash(portfolioId, quantity, price);
  }
  
}
