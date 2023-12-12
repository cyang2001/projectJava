package com.isep.eleve.javaproject.model.assets;

import java.math.BigDecimal;

public class FixedDeposit extends LiquidAssets {
    private BigDecimal interestRate;

    public FixedDeposit(int portfolioId, int quantity, BigDecimal price, BigDecimal interestRate) {
        super(portfolioId, quantity, price);
        this.interestRate = interestRate;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public void calculateValue() {
        // Calculate the value of the fixed deposit based on the interest rate
        BigDecimal baseValue = new BigDecimal(this.getQuantity()).multiply(this.getPrice());
        BigDecimal interest = baseValue.multiply(this.interestRate);
        this.setValue(baseValue.add(interest));
    }
}
