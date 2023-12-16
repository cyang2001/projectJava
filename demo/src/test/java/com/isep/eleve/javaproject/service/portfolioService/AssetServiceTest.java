package com.isep.eleve.javaproject.service.portfolioService;

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

    Asset mockAsset = mock(Asset.class);
    when(mockAsset.getAssetName()).thenReturn(assetName);
    when(mockAsset.getPortfolioId()).thenReturn(portfolioId);
    when(mockAsset.getQuantity()).thenReturn(quantity);
    when(mockAsset.getPrice()).thenReturn(price);
    // when(mockAsset.getInterestRate()).thenReturn(interestRate);
    when(mockAsset.getOwnerId()).thenReturn(ownerId);
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
}
