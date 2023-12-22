package com.isep.eleve.javaproject.service.portfolioServices;

import java.io.IOException;
import java.math.BigDecimal;

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
  private final PortfolioRepository portfolioRepository;
  private final AssetsRepository assetsRepository;
  private final AssetFactoryProducer factoryProducer;
  @Autowired
  public AssetsService(PortfolioRepository portfolioRepository, AssetsRepository assetsRepository, AssetFactoryProducer factoryProducer){
    this.portfolioRepository = portfolioRepository;
    this.assetsRepository = assetsRepository;
    this.factoryProducer = factoryProducer;
  }

  public Asset createAsset(String assetName,int portfolioId, int quantity, BigDecimal price, ASSET_TYPE assetType, BigDecimal interestRate) throws IOException {
    AssetFactory factory = factoryProducer.getFactory(assetType);
    Portfolio portfolio = portfolioRepository.findByPortfolioId(portfolioId);
    Asset newAsset = factory.createAsset(assetName, portfolioId, quantity, price, interestRate, portfolio.getOwnerId());

    portfolio.addAsset(newAsset);
    // Persist the new asset
    assetsRepository.save(newAsset);
    return newAsset;
  }

  public void changeAssetQuantity(int assetId, int quantity, Constants.changeType changType, ASSET_TYPE assetType) throws IOException {
    Asset asset = assetsRepository.findByAssetId(assetId);
    if (changType == Constants.changeType.ADD) {
      asset.setQuantity(asset.getQuantity() + quantity);
    } else if (changType == Constants.changeType.SUBTRACT) {
      asset.setQuantity(asset.getQuantity() - quantity);
    }
    asset.calculateValue();
    assetsRepository.save(asset);
  }
  public void changeAssetQuantity(int assetId, int quantity, ASSET_TYPE assetType) throws IOException {
    Asset asset = assetsRepository.findByAssetId(assetId);
    asset.setQuantity(quantity);
    asset.calculateValue();
    assetsRepository.save(asset);
    if (assetType == Constants.ASSET_TYPE.CRYPTO) {
      
    }
  }

  public void updateAssetPrice(int assetId, BigDecimal price, ASSET_TYPE assetType) throws IOException {
    // ToDo rewirte with API
    Asset asset = assetsRepository.findByAssetId(assetId);
    asset.setPrice(price);
    asset.calculateValue();
    assetsRepository.save(asset);
  }
}
