package com.isep.eleve.javaproject.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.isep.eleve.javaproject.factory.AssetFactory;
import com.isep.eleve.javaproject.factory.AssetFactoryProducer;

@Configuration
public class AssetFactoryConfig {
  @Autowired
  private AssetFactory cashFactory;
  @Autowired
  private AssetFactory fixedDepositFactory;

  @Bean
  public AssetFactoryProducer assetFactoryProducer() {
    Map<String, AssetFactory> factories = new HashMap<>();
        factories.put("LiquidAsset", cashFactory);
        factories.put("Crypto", fixedDepositFactory);
        // Add more factories here
        return new AssetFactoryProducer(factories);
  }
}
