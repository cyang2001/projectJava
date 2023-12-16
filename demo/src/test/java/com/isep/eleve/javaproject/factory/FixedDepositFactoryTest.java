package com.isep.eleve.javaproject.factory;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.isep.eleve.javaproject.model.Asset;

@RunWith(MockitoJUnitRunner.class)
public class FixedDepositFactoryTest {
  @InjectMocks
  private FixedDepositFactory fixedDepositFactory;

  @Test
  public void testCreateAsset() {
    // Arrange
    String assetName = "My Asset";
    int portfolioId = 1;
    int quantity = 1;
    int ownerId = 1;
    BigDecimal price = new BigDecimal(1);
    BigDecimal interestRate = new BigDecimal(1);
    
    // Act
    Asset asset = fixedDepositFactory.createAsset(assetName, portfolioId, quantity, price, interestRate, ownerId);
    // Assert
    Assert.assertEquals(assetName, asset.getAssetName());
    Assert.assertEquals(portfolioId, asset.getPortfolioId());
    Assert.assertEquals(quantity, asset.getQuantity());
    Assert.assertEquals(price, asset.getPrice());
    Assert.assertEquals(ownerId, asset.getOwnerId());
    // Assert
  }
}
