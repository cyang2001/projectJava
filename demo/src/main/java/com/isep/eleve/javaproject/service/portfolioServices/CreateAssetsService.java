package com.isep.eleve.javaproject.service.portfolioServices;

import java.io.IOException;
import java.math.BigDecimal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.eleve.javaproject.factory.AssetFactory;
import com.isep.eleve.javaproject.factory.AssetFactoryProducer;
import com.isep.eleve.javaproject.model.Asset;
import com.isep.eleve.javaproject.model.Portfolio;
import com.isep.eleve.javaproject.repository.AssetsRepository;
import com.isep.eleve.javaproject.repository.PortfolioRepository;

@Service
public class CreateAssetsService {
  private final PortfolioRepository portfolioRepository;
  private final AssetsRepository assetsRepository;
  private final AssetFactoryProducer factoryProducer;
  @Autowired
  public CreateAssetsService(PortfolioRepository portfolioRepository, AssetsRepository assetsRepository, AssetFactoryProducer factoryProducer){
    this.portfolioRepository = portfolioRepository;
    this.assetsRepository = assetsRepository;
    this.factoryProducer = factoryProducer;
  }

  public Asset createAsset(int portfolioId, int quantity, BigDecimal price, String assetType, BigDecimal interestRate) throws IOException {
    AssetFactory factory = factoryProducer.getFactory(assetType);
    Asset newAsset = factory.createAsset(portfolioId, quantity, price, interestRate);
    Portfolio portfolio = portfolioRepository.findByPortfolioId(portfolioId);
      portfolio.addAsset(newAsset);
      // Persist the new asset
      assetsRepository.save(newAsset);
      return newAsset;
  }

}
