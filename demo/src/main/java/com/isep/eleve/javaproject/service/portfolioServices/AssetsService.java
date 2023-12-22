package com.isep.eleve.javaproject.service.portfolioServices;

import java.io.IOException;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.isep.eleve.javaproject.Tools.Constants;
import com.isep.eleve.javaproject.Tools.Constants.ASSET_TYPE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.eleve.javaproject.factory.AssetFactory;
import com.isep.eleve.javaproject.factory.AssetFactoryProducer;
import com.isep.eleve.javaproject.model.Asset;
import com.isep.eleve.javaproject.model.Portfolio;
import com.isep.eleve.javaproject.repository.AssetsRepository;
import com.isep.eleve.javaproject.repository.PortfolioRepository;

@Service
public class AssetsService {
  private static final Logger logger = LoggerFactory.getLogger(AssetsService.class);
  private final PortfolioRepository portfolioRepository;
  private final AssetsRepository assetsRepository;
  private final AssetFactoryProducer factoryProducer;
  @Autowired
  public AssetsService(PortfolioRepository portfolioRepository, AssetsRepository assetsRepository, AssetFactoryProducer factoryProducer){
    this.portfolioRepository = portfolioRepository;
    this.assetsRepository = assetsRepository;
    this.factoryProducer = factoryProducer;
  }

  public Asset createAsset(String assetName,int portfolioId, int quantity, BigDecimal price, ASSET_TYPE assetType, BigDecimal interestRate) throws IOException, IllegalArgumentException{
    if (assetName == null || assetName.isEmpty()) {
      throw new IllegalArgumentException("Asset name cannot be null or empty");
    }
    if (quantity <= 0) {
      throw new IllegalArgumentException("Quantity cannot be less than or equal to 0");
    }
    if (price.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Price cannot be less than or equal to 0");
    }
    try{
      AssetFactory factory = factoryProducer.getFactory(assetType);
      Portfolio portfolio = portfolioRepository.findByPortfolioId(portfolioId);
      Asset newAsset = factory.createAsset(assetName, portfolioId, quantity, price, interestRate, portfolio.getOwnerId());
      portfolio.addAsset(newAsset);
      // Persist the new asset
      logger.info("Asset created: {}", assetName);
      assetsRepository.save(newAsset);
      return newAsset;
    }catch(Exception e){
      logger.error("Error when creating asset", e);
      throw e;
    }
    
  }

  public void changeAssetQuantity(int assetId, int quantity, Constants.changeType changeType, ASSET_TYPE assetType) throws IOException {
    Asset asset = assetsRepository.findByAssetId(assetId);
    if (asset == null) {
        // 处理找不到资产的情况
        logger.error("Asset not found: assetId={}", assetId);
        throw new IllegalArgumentException("Asset not found with id: " + assetId);
    }

    int originalQuantity = asset.getQuantity();
    int newQuantity = originalQuantity;

    if (changeType == Constants.changeType.ADD) {
        newQuantity += quantity;
    } else if (changeType == Constants.changeType.SUBTRACT) {
        newQuantity -= quantity;
    } else if (changeType == Constants.changeType.SET) {
        newQuantity = quantity;
    }

    asset.setQuantity(newQuantity);
    asset.calculateValue();
    assetsRepository.save(asset);

    logger.info("Asset quantity changed: assetId={}, originalQuantity={}, newQuantity={}, changeType={}", assetId, originalQuantity, newQuantity, changeType);
}


  public void updateAssetPrice(int assetId, BigDecimal price, ASSET_TYPE assetType) throws IOException {
    // ToDo rewirte with API
    Asset asset = assetsRepository.findByAssetId(assetId);
    asset.setPrice(price);
    asset.calculateValue();
    assetsRepository.save(asset);
  }
  
}
