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
import org.springframework.context.ApplicationEventPublisher;

import com.isep.eleve.javaproject.Tools.Constants.ASSET_TYPE;
import com.isep.eleve.javaproject.events.AssetCreatedEvent;
import com.isep.eleve.javaproject.factory.AssetFactory;
import com.isep.eleve.javaproject.factory.AssetFactoryProducer;
import com.isep.eleve.javaproject.model.Asset;
import com.isep.eleve.javaproject.model.Portfolio;
import com.isep.eleve.javaproject.model.User;
import com.isep.eleve.javaproject.repository.AssetsRepository;
import com.isep.eleve.javaproject.repository.PortfolioRepository;
import com.isep.eleve.javaproject.service.portfolioServices.AssetsService;
import com.isep.eleve.javaproject.session.AssetSession;
import com.isep.eleve.javaproject.session.PortfolioSession;
import com.isep.eleve.javaproject.session.UserSession;
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

  @Mock
  private ApplicationEventPublisher eventApplication;
  
  @Mock
  private UserSession userSession;
  @Mock
  private PortfolioSession portfolioSession;
  @Mock
  private AssetSession assetSession;

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
    when(mockAsset.getAssetId()).thenReturn(assetId);
    Portfolio mockPortfolio = mock(Portfolio.class);
    when(mockPortfolio.getPortfolioId()).thenReturn(portfolioId);
    when(mockPortfolio.getOwnerId()).thenReturn(ownerId);
    when(mockPortfolio.getPortfolioName()).thenReturn("Test Portfolio");

    User mockUser = mock(User.class);
    when(mockUser.getUserId()).thenReturn(ownerId);
    when(userSession.getCurrentUser()).thenReturn(mockUser);
    when(assetSession.getCurrentAsset()).thenReturn(mockAsset);
    when(portfolioSession.getCurrentPortfolio()).thenReturn(mockPortfolio);
    when(assetSession.getCurrentAsset().getAssetId()).thenReturn(assetId);
    when(portfolioSession.getCurrentPortfolio().getPortfolioId()).thenReturn(portfolioId);

    when(factoryProducer.getFactory(assetType)).thenReturn(assetFactory);

    when(assetFactory.createAsset(assetName, portfolioId, quantity, price, interestRate, ownerId))
        .thenReturn(mockAsset);

    when(portfolioRepository.findByPortfolioId(portfolioId)).thenReturn(mockPortfolio);
    doNothing().when(mockPortfolio).addAsset(any(Asset.class));

    // Act
    Asset createdAsset = assetsService.createAsset(assetName, quantity, price, assetType, interestRate,portfolioId);
    // Assert
    verify(assetsRepository).save(mockAsset);
    assertEquals(mockAsset, createdAsset);
    verify(eventApplication).publishEvent(any(AssetCreatedEvent.class));
  }
  @Test(expected = IllegalArgumentException.class)
  public void testCreateAssetWithInvalidName() throws IOException {
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
    when(mockAsset.getAssetId()).thenReturn(assetId);
    Portfolio mockPortfolio = mock(Portfolio.class);
    when(mockPortfolio.getPortfolioId()).thenReturn(portfolioId);
    when(mockPortfolio.getOwnerId()).thenReturn(ownerId);
    when(mockPortfolio.getPortfolioName()).thenReturn("Test Portfolio");

    User mockUser = mock(User.class);
    when(mockUser.getUserId()).thenReturn(ownerId);
    when(userSession.getCurrentUser()).thenReturn(mockUser);
    when(assetSession.getCurrentAsset()).thenReturn(mockAsset);
    when(portfolioSession.getCurrentPortfolio()).thenReturn(mockPortfolio);
    when(assetSession.getCurrentAsset().getAssetId()).thenReturn(assetId);
    when(portfolioSession.getCurrentPortfolio().getPortfolioId()).thenReturn(portfolioId);

    when(factoryProducer.getFactory(assetType)).thenReturn(assetFactory);

    when(assetFactory.createAsset(assetName, portfolioId, quantity, price, interestRate, ownerId))
        .thenReturn(mockAsset);

    when(portfolioRepository.findByPortfolioId(portfolioId)).thenReturn(mockPortfolio);
    doNothing().when(mockPortfolio).addAsset(any(Asset.class));
      assetsService.createAsset("", 10, new BigDecimal("100.00" ), ASSET_TYPE.CASH, new BigDecimal("0.05"), portfolioId);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateAssetWithInvalidQuantity() throws IOException {
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
    when(mockAsset.getAssetId()).thenReturn(assetId);
    Portfolio mockPortfolio = mock(Portfolio.class);
    when(mockPortfolio.getPortfolioId()).thenReturn(portfolioId);
    when(mockPortfolio.getOwnerId()).thenReturn(ownerId);
    when(mockPortfolio.getPortfolioName()).thenReturn("Test Portfolio");

    User mockUser = mock(User.class);
    when(mockUser.getUserId()).thenReturn(ownerId);
    when(userSession.getCurrentUser()).thenReturn(mockUser);
    when(assetSession.getCurrentAsset()).thenReturn(mockAsset);
    when(portfolioSession.getCurrentPortfolio()).thenReturn(mockPortfolio);
    when(assetSession.getCurrentAsset().getAssetId()).thenReturn(assetId);
    when(portfolioSession.getCurrentPortfolio().getPortfolioId()).thenReturn(portfolioId);

    when(factoryProducer.getFactory(assetType)).thenReturn(assetFactory);

    when(assetFactory.createAsset(assetName, portfolioId, quantity, price, interestRate, ownerId))
        .thenReturn(mockAsset);

    when(portfolioRepository.findByPortfolioId(portfolioId)).thenReturn(mockPortfolio);
    doNothing().when(mockPortfolio).addAsset(any(Asset.class));
      assetsService.createAsset("Test Asset", -10, new BigDecimal("100.00"), ASSET_TYPE.CASH, new BigDecimal("0.05"), portfolioId);
}
  @Test(expected = Exception.class)
  public void testCreateAssetWhenPortfolioNotFound() throws IOException {
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
    when(mockAsset.getAssetId()).thenReturn(assetId);
    Portfolio mockPortfolio = mock(Portfolio.class);
    when(mockPortfolio.getPortfolioId()).thenReturn(portfolioId);
    when(mockPortfolio.getOwnerId()).thenReturn(ownerId);
    when(mockPortfolio.getPortfolioName()).thenReturn("Test Portfolio");

    User mockUser = mock(User.class);
    when(mockUser.getUserId()).thenReturn(ownerId);
    when(userSession.getCurrentUser()).thenReturn(mockUser);
    when(assetSession.getCurrentAsset()).thenReturn(mockAsset);
    when(portfolioSession.getCurrentPortfolio()).thenReturn(mockPortfolio);
    when(assetSession.getCurrentAsset().getAssetId()).thenReturn(assetId);
    when(portfolioSession.getCurrentPortfolio().getPortfolioId()).thenReturn(portfolioId);

    when(factoryProducer.getFactory(assetType)).thenReturn(assetFactory);

    when(assetFactory.createAsset(assetName, portfolioId, quantity, price, interestRate, ownerId))
        .thenReturn(mockAsset);

    when(portfolioRepository.findByPortfolioId(portfolioId)).thenReturn(mockPortfolio);
    doNothing().when(mockPortfolio).addAsset(any(Asset.class));
      when(portfolioRepository.findByPortfolioId(anyInt())).thenReturn(null);
      assetsService.createAsset("Test Asset", 10, new BigDecimal("100.00"), ASSET_TYPE.CASH, new BigDecimal("0.05"), portfolioId);
  }
  @Test
  public void testChangeAssetQuantity() throws IOException {
      Asset mockAsset = mock(Asset.class);
      when(assetsRepository.findByAssetId(anyInt())).thenReturn(mockAsset);
      doNothing().when(mockAsset).setQuantity(anyInt());

      assetsService.changeAssetQuantity(1, 5, Constants.CHANGE_TYPE.ADD);

      verify(mockAsset).setQuantity(anyInt());
      verify(assetsRepository).save(mockAsset);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeAssetQuantityWithInvalidAsset() throws IOException {
      when(assetsRepository.findByAssetId(anyInt())).thenReturn(null);
      assetsService.changeAssetQuantity(1, 5, Constants.CHANGE_TYPE.ADD);
  }
}
