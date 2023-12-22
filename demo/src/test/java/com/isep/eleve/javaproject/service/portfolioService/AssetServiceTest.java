package com.isep.eleve.javaproject.service.portfolioService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.isep.eleve.javaproject.Tools.Constants.ASSET_TYPE;
import com.isep.eleve.javaproject.factory.AssetFactory;
import com.isep.eleve.javaproject.factory.AssetFactoryProducer;
import com.isep.eleve.javaproject.model.Asset;
import com.isep.eleve.javaproject.model.Portfolio;
import com.isep.eleve.javaproject.repository.AssetsRepository;
import com.isep.eleve.javaproject.repository.PortfolioRepository;
import com.isep.eleve.javaproject.service.portfolioServices.AssetsService;
import com.isep.eleve.javaproject.Tools.Constants;
@RunWith(MockitoJUnitRunner.class)
public class AssetServiceTest {

  @InjectMocks
  private AssetsService assetsService;

  @Mock
  private AssetsRepository assetsRepository;

  @Mock
  private PortfolioRepository portfolioRepository;

  @Mock
  private AssetFactoryProducer factoryProducer;

  @Mock
  private AssetFactory assetFactory;

  @Test
  public void testCreateAsset() throws IOException {
    // Arrange
    String assetName = "Test Asset";
    int portfolioId = 1;
    int quantity = 10;
    BigDecimal price = new BigDecimal("100.00");
    ASSET_TYPE assetType = ASSET_TYPE.CASH; // or ASSET_TYPE.FIXED_DEPOSIT for different tests
    BigDecimal interestRate = new BigDecimal("0.05");
    int ownerId = 1;
    int assetId = 1;
    Asset mockAsset = mock(Asset.class);
    when(mockAsset.getAssetName()).thenReturn(assetName);
    when(mockAsset.getPortfolioId()).thenReturn(portfolioId);
    when(mockAsset.getQuantity()).thenReturn(quantity);
    when(mockAsset.getPrice()).thenReturn(price);
    // when(mockAsset.getInterestRate()).thenReturn(interestRate);
    when(mockAsset.getOwnerId()).thenReturn(ownerId);
    when(mockAsset.getAssetId()).thenReturn(assetId);
    Portfolio mockPortfolio = mock(Portfolio.class);
    when(mockPortfolio.getPortfolioId()).thenReturn(portfolioId);
    when(mockPortfolio.getOwnerId()).thenReturn(ownerId);


    when(factoryProducer.getFactory(assetType)).thenReturn(assetFactory);

    when(assetFactory.createAsset(assetName, portfolioId, quantity, price, interestRate, ownerId))
        .thenReturn(mockAsset);

    when(portfolioRepository.findByPortfolioId(portfolioId)).thenReturn(mockPortfolio);
    doNothing().when(mockPortfolio).addAsset(any(Asset.class));

    // Act
    Asset createdAsset = assetsService.createAsset(assetName, portfolioId, quantity, price, assetType, interestRate);
    // Assert
    verify(assetsRepository).save(mockAsset);
    assertEquals(mockAsset, createdAsset);
    verify(mockPortfolio).addAsset(mockAsset);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testCreateAssetWithInvalidName() throws IOException {
      assetsService.createAsset("", 1, 10, new BigDecimal("100.00"), ASSET_TYPE.CASH, new BigDecimal("0.05"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateAssetWithInvalidQuantity() throws IOException {
      assetsService.createAsset("Test Asset", 1, -10, new BigDecimal("100.00"), ASSET_TYPE.CASH, new BigDecimal("0.05"));
}
  @Test(expected = Exception.class)
  public void testCreateAssetWhenPortfolioNotFound() throws IOException {
      when(portfolioRepository.findByPortfolioId(anyInt())).thenReturn(null);
      assetsService.createAsset("Test Asset", 1, 10, new BigDecimal("100.00"), ASSET_TYPE.CASH, new BigDecimal("0.05"));
  }
  @Test
  public void testChangeAssetQuantity() throws IOException {
      Asset mockAsset = mock(Asset.class);
      when(assetsRepository.findByAssetId(anyInt())).thenReturn(mockAsset);
      doNothing().when(mockAsset).setQuantity(anyInt());

      assetsService.changeAssetQuantity(1, 5, Constants.changeType.ADD, ASSET_TYPE.CASH);

      verify(mockAsset).setQuantity(anyInt());
      verify(assetsRepository).save(mockAsset);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeAssetQuantityWithInvalidAsset() throws IOException {
      when(assetsRepository.findByAssetId(anyInt())).thenReturn(null);
      assetsService.changeAssetQuantity(1, 5, Constants.changeType.ADD, ASSET_TYPE.CASH);
  }
}
