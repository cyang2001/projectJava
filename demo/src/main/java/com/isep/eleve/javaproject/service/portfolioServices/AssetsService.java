package com.isep.eleve.javaproject.service.portfolioServices;

import java.io.IOException;
import java.math.BigDecimal;

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

}
