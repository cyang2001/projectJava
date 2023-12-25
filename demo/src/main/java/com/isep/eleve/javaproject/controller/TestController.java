package com.isep.eleve.javaproject.controller;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.isep.eleve.javaproject.model.Portfolio;
import com.isep.eleve.javaproject.service.portfolioServices.AssetsService;
import com.isep.eleve.javaproject.service.portfolioServices.PortfolioService;
import com.isep.eleve.javaproject.session.PortfolioSession;
import com.isep.eleve.javaproject.session.UserSession;
import com.isep.eleve.javaproject.Tools.Constants.*;
@Controller
public class TestController {
  private final UserSession userSession;
  private final PortfolioSession portfolioSession;
  private final PortfolioService portfolioService;
  private final AssetsService assetsService;
  @Autowired
  public TestController(UserSession userSession, PortfolioSession portfolioSession ,PortfolioService portfolioService, AssetsService assetsService){
    this.userSession = userSession;
    this.portfolioService = portfolioService;
    this.assetsService = assetsService;
    this.portfolioSession = portfolioSession;
  }
  public void TestCreatePortfolio(){
    try {
      portfolioService.createPortfolio("testPortfolio", userSession.getCurrentUser().getUserId());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public void TestCreateAsset(){
    BigDecimal priceTest = new BigDecimal(10);
    int quantityTest = 10;
    
    try {
      assetsService.createAsset("testAsset", portfolioSession.getCurrentPortfolio().getPortfolioId(), quantityTest, priceTest, ASSET_TYPE.STOCK, null);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
