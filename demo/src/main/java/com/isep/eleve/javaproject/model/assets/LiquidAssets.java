package com.isep.eleve.javaproject.model.assets;
import java.math.BigDecimal;
import com.isep.eleve.javaproject.model.Asset;

public abstract class LiquidAssets extends Asset {
    // Constructor matching super
    public LiquidAssets(String assetName, int portfolioId, int quantity, BigDecimal price) {
        super(assetName, portfolioId, quantity, price);
    }

    // Additional properties or methods common to all liquid assets can be added here
}
