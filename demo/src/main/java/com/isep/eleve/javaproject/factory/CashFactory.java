package com.isep.eleve.javaproject.factory;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.isep.eleve.javaproject.model.Asset;
import com.isep.eleve.javaproject.model.assets.liquide.Cash;


@Component
@Qualifier("CashFactory")
public class CashFactory implements AssetFactory{
  /**
  * Creates a Cash asset with the given parameters.
  *
  * @param assetName the name of the asset
  * @param portfolioId the ID of the portfolio
  * @param quantity the quantity of the asset
  * @param price the price of the asset
  * @param interestRate the interest rate of the asset
  * @param ownerId the ID of the owner
  * @return the created Cash asset
  */
  @Override
  public Asset createAsset(String assetName,int portfolioId, int quantity, BigDecimal price, BigDecimal interestRate, int ownerId) {
    return new Cash(assetName, portfolioId, quantity, price, ownerId);
  }
  
}
