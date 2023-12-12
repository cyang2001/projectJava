package com.isep.eleve.javaproject.service.portfolioServices;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.eleve.javaproject.Tools.Constants;
import com.isep.eleve.javaproject.model.Asset;
import com.isep.eleve.javaproject.model.Portfolio;
import com.isep.eleve.javaproject.repository.AssetsRepository;
import com.isep.eleve.javaproject.repository.PortfolioRepository;

@Service
public class CreateAssetsService {
  private final PortfolioRepository portfolioRepository;
  private final AssetsRepository assetsRepository;
  @Autowired
  public CreateAssetsService(PortfolioRepository portfolioRepository, AssetsRepository assetsRepository){
    this.portfolioRepository = portfolioRepository;
    this.assetsRepository = assetsRepository;
  }

  public Asset createAsset(int portfolioId, int quantity, BigDecimal price, String assetType) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
    // Create a new Asset object
    // Todo 
    Class<? extends Asset> assetClass = Constants.ASSET_TYPE.get(assetType);
    if (assetClass != null) {
      Asset newAsset = assetClass.getDeclaredConstructor(int.class, int.class, BigDecimal.class).newInstance(portfolioId, quantity, price);
      // Add the new asset to the portfolio
      Portfolio portfolio = portfolioRepository.findByPortfolioId(portfolioId);
      portfolio.addAsset(newAsset);
      // Persist the new asset
      assetsRepository.save(newAsset);
      // Return the newly created asset
      return newAsset;
    } else {
      return null;
    }
  }
}
