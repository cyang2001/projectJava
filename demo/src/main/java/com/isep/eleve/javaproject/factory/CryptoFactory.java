package com.isep.eleve.javaproject.factory;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.isep.eleve.javaproject.model.Asset;
import com.isep.eleve.javaproject.model.assets.cypto.CryptoCurrency;

@Component
@Qualifier("CryptoFactory")
public class CryptoFactory implements AssetFactory{

  @Override
  public Asset createAsset(String assetName, int portfolioId, int quantity, BigDecimal price, BigDecimal interestRate,
      int ownerId) {
        return new CryptoCurrency(assetName, portfolioId, quantity, price, ownerId);
  }
  
}