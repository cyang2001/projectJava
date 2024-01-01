package com.isep.eleve.javaproject.model.assets.liquide;

import java.math.BigDecimal;

import com.isep.eleve.javaproject.model.Asset;

/**
 * Represents a fixed deposit asset.
 * Inherits from the LiquidAssets class.
 * @version 1.0
 * @see LiquidAssets
 * @author Chen YANG
 */
public class FixedDeposit extends Asset {
  private BigDecimal interestRate;

  /**
   * Constructs a FixedDeposit object with the specified parameters.
   *
   * @param assetName   the name of the asset
   * @param portfolioId the ID of the portfolio
   * @param quantity    the quantity of the asset
   * @param price       the price of the asset
   * @param interestRate the interest rate of the fixed deposit
   * @param ownerId     the ID of the owner
   */
  public FixedDeposit(String assetName, int portfolioId, int quantity, BigDecimal price, BigDecimal interestRate, int ownerId) {
    super(assetName, portfolioId, quantity, price, ownerId);
    this.interestRate = interestRate;
  }

  /**
   * Gets the interest rate of the fixed deposit.
   *
   * @return the interest rate
   */
  public BigDecimal getInterestRate() {
    return interestRate;
  }

  /**
   * Sets the interest rate of the fixed deposit.
   *
   * @param interestRate the interest rate to set
   */
  public void setInterestRate(BigDecimal interestRate) {
    this.interestRate = interestRate;
  }

  /**
   * Calculates the value of the fixed deposit based on the interest rate.
   * Overrides the calculateValue method in the LiquidAssets class.
   */
  @Override
  public void calculateValue() {
    this.setValue(this.getPrice());
    // ToDo 
    //BigDecimal baseValue = new BigDecimal(this.getQuantity()).multiply(this.getPrice());
    //BigDecimal interest = baseValue.multiply(this.interestRate);
    //this.setValue(baseValue.add(interest));
  }
}
