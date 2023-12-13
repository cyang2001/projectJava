package com.isep.eleve.javaproject.factory;

import java.math.BigDecimal;


import com.isep.eleve.javaproject.model.Asset;

public interface AssetFactory {
  Asset createAsset(int portfolioId, int quantity, BigDecimal price, BigDecimal interestRate);
}
