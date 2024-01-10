package com.isep.eleve.javaproject.factory;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.isep.eleve.javaproject.model.Asset;
import com.isep.eleve.javaproject.model.assets.liquide.FixedDeposit;

@Component
@Qualifier("FixedDepositFactory")
public class FixedDepositFactory implements AssetFactory {
  /**
  * Creates a FixedDeposit object with the specified parameters.
  *
  * @param assetName     the name of the fixed deposit asset
  * @param portfolioId   the ID of the portfolio to which the fixed deposit belongs
  * @param quantity      the quantity of the fixed deposit
  * @param price         the price of the fixed deposit
  * @param interestRate  the interest rate of the fixed deposit
  * @param ownerId       the ID of the owner of the fixed deposit
  * @return              a new FixedDeposit object
  */
  @Override
  public Asset createAsset(String assetName, int portfolioId, int quantity, BigDecimal price, BigDecimal interestRate, int ownerId) {
    return new FixedDeposit(assetName, portfolioId, quantity, price, interestRate, ownerId);
  }
  
}
