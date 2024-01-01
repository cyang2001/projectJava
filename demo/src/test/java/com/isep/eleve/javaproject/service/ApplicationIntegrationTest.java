package com.isep.eleve.javaproject.service;

import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.isep.eleve.javaproject.Tools.Constants.ASSET_TYPE;
import com.isep.eleve.javaproject.model.Asset;
import com.isep.eleve.javaproject.model.Portfolio;
import com.isep.eleve.javaproject.service.portfolioServices.AssetsService;
import com.isep.eleve.javaproject.service.portfolioServices.PortfolioService;
import com.isep.eleve.javaproject.service.userServices.*;
import com.isep.eleve.javaproject.service.userServices.RegistrationService.RegistrationResult;
import com.isep.eleve.javaproject.session.PortfolioSession;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationIntegrationTest {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private AssetsService assetsService;

    @Test
    public void testUserAssetPortfolioIntegration() throws IOException{
      String userName = "chen14";
      String password = "ycc";
      String passwordEnsurance = "ycc";
      RegistrationResult registrationResult = registrationService.register(userName, password, passwordEnsurance);
      assertNotEquals(registrationResult.getUser(), null);
      Portfolio portfolioTest = portfolioService.createPortfolio("testPortfolioName");
      PortfolioSession portfolioSession = new PortfolioSession();
      portfolioSession.setCurrentPortfolio(portfolioTest);
      Asset assetTest = assetsService.createAsset("testAssetName",  100, new BigDecimal(1), ASSET_TYPE.CASH, null);
      assertNotEquals(portfolioTest, null);
      assertNotEquals(assetTest, null);
    }
  
}