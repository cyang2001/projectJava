package com.isep.eleve.javaproject.model.assets;
import java.math.BigDecimal;
import com.isep.eleve.javaproject.model.Asset;

public abstract class LiquidAssets extends Asset {
    // Constructor matching super
    public LiquidAssets(int portfolioId, int quantity, BigDecimal price) {
        super(portfolioId, quantity, price);
    }

}
