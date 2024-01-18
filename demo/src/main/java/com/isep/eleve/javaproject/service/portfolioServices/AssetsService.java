package com.isep.eleve.javaproject.service.portfolioServices;

import java.io.IOException;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.isep.eleve.javaproject.Tools.Constants;
import com.isep.eleve.javaproject.Tools.Constants.ASSET_TYPE;
import com.isep.eleve.javaproject.events.AssetClonedEvent;
import com.isep.eleve.javaproject.events.AssetCreatedEvent;
import com.isep.eleve.javaproject.events.AssetPriceChangedEvent;
import com.isep.eleve.javaproject.events.AssetQuantityChangedEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.isep.eleve.javaproject.factory.AssetFactory;
import com.isep.eleve.javaproject.factory.AssetFactoryProducer;
import com.isep.eleve.javaproject.model.Asset;
import com.isep.eleve.javaproject.model.Portfolio;
import com.isep.eleve.javaproject.repository.AssetsRepository;
import com.isep.eleve.javaproject.repository.PortfolioRepository;
import com.isep.eleve.javaproject.session.AssetSession;
import com.isep.eleve.javaproject.session.PortfolioSession;

/**
 * This class represents a service for managing assets in a portfolio.
 */
/**
 * This class represents a service for managing assets in a portfolio.
 * It provides methods for creating, cloning, and updating assets, as well as changing asset quantities and retrieving asset quantities.
 */
@Service
public class AssetsService {
  private static final Logger logger = LoggerFactory.getLogger(AssetsService.class);
  private final PortfolioRepository portfolioRepository;
  private final AssetsRepository assetsRepository;
  private final AssetFactoryProducer factoryProducer;
  private final ApplicationEventPublisher eventApplication;
  private final PortfolioSession portfolioSession;
  private final AssetSession assetSession;
  @Autowired
  public AssetsService(PortfolioRepository portfolioRepository, AssetsRepository assetsRepository, AssetFactoryProducer factoryProducer, ApplicationEventPublisher eventApplication, PortfolioSession portfolioSession, AssetSession assetSession){
    this.portfolioRepository = portfolioRepository;
    this.assetsRepository = assetsRepository;
    this.factoryProducer = factoryProducer;
    this.eventApplication = eventApplication;
    this.portfolioSession = portfolioSession;
    this.assetSession = assetSession;
  }

  /**
   * Creates a new Asset with the given parameters and adds it to the specified Portfolio.
   *
   * @param assetName    the name of the asset
   * @param portfolioId  the ID of the portfolio to which the asset will be added
   * @param quantity     the quantity of the asset
   * @param price        the price of the asset
   * @param assetType    the type of the asset
   * @param interestRate the interest rate of the asset
   * @return the newly created Asset
   * @throws IOException              if an I/O error occurs
   * @throws IllegalArgumentException if the asset name is null or empty, quantity is less than or equal to 0, or price is less than or equal to 0
   */
  public Asset createAsset(String assetName,int quantity, BigDecimal price, ASSET_TYPE assetType, BigDecimal interestRate, int portfolioId, boolean isFirst) throws IOException, IllegalArgumentException{
    if (assetName == null || assetName.isEmpty()) {
      throw new IllegalArgumentException("Asset name cannot be null or empty");
    }
    if (quantity <= 0) {
      throw new IllegalArgumentException("Quantity cannot be less than or equal to 0");
    }
    /*if (isFirst && assetType!=ASSET_TYPE.CASH || price.compareTo(BigDecimal.ZERO) <= 0  ) {
      throw new IllegalArgumentException("Price cannot be less than or equal to 0");
    }*/
    try{
      AssetFactory factory = factoryProducer.getFactory(assetType);
      Portfolio portfolio = portfolioSession.getCurrentPortfolio();
      
      Asset newAsset = factory.createAsset(assetName, portfolioId, quantity, price, interestRate, portfolio.getOwnerId(), assetType);
      eventApplication.publishEvent(new AssetCreatedEvent(this, newAsset));
      // Persist the new asset
      logger.info("Asset created: {}", assetName);
      logger.info("Asset created: {}", newAsset.getAssetType());
      return newAsset;
    }catch(Exception e){
      logger.error("Error when creating asset", e);
      throw e;
    }
    
  }
  public Asset cloneAsset(String assetName,int quantity, BigDecimal price, ASSET_TYPE assetType, BigDecimal interestRate, int portfolioId, boolean isFirst, Portfolio portfolioToClone) throws IOException { 
    AssetFactory factory = factoryProducer.getFactory(assetType);
    //Portfolio portfolio = portfolioSession.getCurrentPortfolio();
    
    Asset newAsset = factory.createAsset(assetName, portfolioId, quantity, price, interestRate, portfolioToClone.getOwnerId(), assetType);
    eventApplication.publishEvent(new AssetClonedEvent(this, newAsset));
    // Persist the new asset
    logger.info("Asset cloned: {}", assetName);
    logger.info("Asset cloned: {}", newAsset.getAssetType());
    return newAsset;
  }
  /**
   * Changes the quantity of an asset based on the specified change type.
   *
   * @param assetId    the ID of the asset
   * @param quantity   the quantity to be changed
   * @param changeType the type of change to be applied (ADD, SUBTRACT, SET)
   * @param assetType  the type of the asset
   * @throws IOException if an I/O error occurs while accessing the asset repository
   * @throws IllegalArgumentException if the asset with the specified ID is not found
   */
  public void changeAssetQuantity(int assetId, int quantity, Constants.CHANGE_TYPE changeType) throws IOException {
    Asset asset = assetsRepository.findByAssetId(assetId);
    if (asset == null) {
        logger.error("Asset not found: assetId={}", assetId);
        throw new IllegalArgumentException("Asset not found with id: " + assetId);
    }

    int originalQuantity = asset.getQuantity();
    int newQuantity = originalQuantity;

    if (changeType == Constants.CHANGE_TYPE.ADD) {
        newQuantity += quantity;
    } else if (changeType == Constants.CHANGE_TYPE.SUBTRACT) {
        newQuantity -= quantity;
    } else if (changeType == Constants.CHANGE_TYPE.SET) {
        newQuantity = quantity;
    }

    asset.setQuantity(newQuantity);
    asset.calculateValue();
    eventApplication.publishEvent(new AssetQuantityChangedEvent(this, asset, newQuantity, changeType));
    logger.info("Asset quantity changed: assetId={}, originalQuantity={}, newQuantity={}, changeType={}", assetId, originalQuantity, newQuantity, changeType);
}
public void changeAssetQuantity(int assetId, BigDecimal price, Constants.CHANGE_TYPE changeType, boolean isMarket) throws IOException {
  Asset asset = assetsRepository.findByAssetId(assetId);
    if (asset == null) {
        logger.error("Asset not found: assetId={}", assetId);
        throw new IllegalArgumentException("Asset not found with id: " + assetId);
    }

    BigDecimal originalPrice = asset.getPrice();
    BigDecimal newPrice = originalPrice;

    if (changeType == Constants.CHANGE_TYPE.ADD) {
        newPrice = newPrice.add(price);
    } else if (changeType == Constants.CHANGE_TYPE.SUBTRACT) {
        newPrice = newPrice.subtract(price);
    } 

    asset.setPrice(newPrice);
    asset.calculateValue();
    eventApplication.publishEvent(new AssetPriceChangedEvent(this, asset, newPrice, changeType, isMarket));
    logger.info("Asset price changed: assetId={}, originalPrice={}, newPrice={}, changeType={}", assetId, originalPrice.intValue(), newPrice.intValue(), changeType);
}

  /**
   * Updates the price of an asset and recalculates its value.
   *
   * @param assetId   the ID of the asset to update
   * @param price     the new price of the asset
   * @param assetType the type of the asset
   * @throws IOException if an I/O error occurs while updating the asset
   */
  public void updateAssetPrice(int assetId, BigDecimal price, ASSET_TYPE assetType) throws IOException {
    // ToDo rewirte with API
    Asset asset = assetsRepository.findByAssetId(assetId);
    asset.setPrice(price);
    asset.calculateValue();
    assetsRepository.save(asset);
  }
  public int getAssetQuantity(int assetId) throws IOException {
    Asset asset = assetsRepository.findByAssetId(assetId);
    return asset.getQuantity();
  }
}
