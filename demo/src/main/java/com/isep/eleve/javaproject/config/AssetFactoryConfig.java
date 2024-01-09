package com.isep.eleve.javaproject.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.isep.eleve.javaproject.Tools.Constants.ASSET_TYPE;
import com.isep.eleve.javaproject.factory.AssetFactory;
import com.isep.eleve.javaproject.factory.AssetFactoryProducer;

@Configuration
public class AssetFactoryConfig {
  @Autowired
  private AssetFactory cashFactory;
  @Autowired
  private AssetFactory fixedDepositFactory;
  @Autowired
  private AssetFactory stockFactory;
  @Autowired
  private AssetFactory cryptoFactory;
  @Bean
  public AssetFactoryProducer assetFactoryProducer() {
    Map<ASSET_TYPE, AssetFactory> factories = new HashMap<>();
        factories.put(ASSET_TYPE.CASH, cashFactory);
        factories.put(ASSET_TYPE.FIXED_DEPOSIT, fixedDepositFactory);
        factories.put(ASSET_TYPE.STOCK, stockFactory);
        factories.put(ASSET_TYPE.CRYPTO, cryptoFactory);

        // Add more factories here
        return new AssetFactoryProducer(factories);
  }
}
