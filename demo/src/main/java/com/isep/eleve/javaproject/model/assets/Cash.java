
package com.isep.eleve.javaproject.model.assets;

import java.math.BigDecimal;

public class Cash extends LiquidAssets {
    public Cash(String assetName, int portfolioId, int quantity, BigDecimal price) {
        super(assetName, portfolioId, quantity, price);
    }

    @Override
    public void calculateValue() {
        // For cash, value might simply be the amount of cash available
        this.setValue(new BigDecimal(this.getQuantity()));
    }
}