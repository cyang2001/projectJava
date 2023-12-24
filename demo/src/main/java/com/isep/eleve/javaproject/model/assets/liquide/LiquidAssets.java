package com.isep.eleve.javaproject.model.assets.liquide;
import java.math.BigDecimal;
import com.isep.eleve.javaproject.model.Asset;

/**
 * This class represents liquid assets, which are a type of asset that can be easily converted into cash.
 * It is an abstract class that extends the Asset class.
 * @version 1.0
 * @see Asset
 * @author Chen YANG
 */
public abstract class LiquidAssets extends Asset {
    // Constructor matching super
    public LiquidAssets(String assetName,int portfolioId, int quantity, BigDecimal price, int ownerId) {
        super(assetName,portfolioId, quantity, price, ownerId);
    }

}
