package com.isep.eleve.javaproject.service;

import static org.junit.Assert.assertNotEquals;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.isep.eleve.javaproject.model.Asset;
import com.isep.eleve.javaproject.model.Portfolio;
import com.isep.eleve.javaproject.model.User;
import com.isep.eleve.javaproject.service.portfolioServices.AssetsService;
import com.isep.eleve.javaproject.service.portfolioServices.PortfolioService;
import com.isep.eleve.javaproject.service.userServices.*;
import com.isep.eleve.javaproject.service.userServices.RegistrationService.RegistrationResult;;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationIntegrationTest {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private AssetsService assetsService;

    @Test
    public void testUserAssetPortfolioIntegration() throws IOException{
      String userName = "chen";
      String password = "yc";
      String passwordEnsurance = "yc";
      RegistrationResult registrationResult = registrationService.register(userName, password, passwordEnsurance);
      assertNotEquals(registrationResult.getUser(), null);
      User userTest = registrationResult.getUser();
      Portfolio portfolioTest = portfolioService.createPortfolio("testPortfolioName", userTest.getUserId());
      Asset assetTest = assetsService.createAsset("testAssetName", 0, 0, null, null, null);
    }
  
}