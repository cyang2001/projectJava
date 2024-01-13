package com.isep.eleve.javaproject.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.isep.eleve.javaproject.Tools.Constants;
import com.isep.eleve.javaproject.model.Asset;
import com.isep.eleve.javaproject.model.Portfolio;
import com.isep.eleve.javaproject.model.assets.liquide.Cash;
import com.isep.eleve.javaproject.repository.AssetsRepository;
import com.isep.eleve.javaproject.service.portfolioServices.AssetsService;
import com.isep.eleve.javaproject.session.CashSession;
import com.isep.eleve.javaproject.session.PortfolioSession;
import com.isep.eleve.javaproject.session.UserSession;
import org.springframework.context.ApplicationEventPublisher;

import com.isep.eleve.javaproject.events.AssetChangedEvent;
import com.isep.eleve.javaproject.events.PortfolioChangedEvent;
import com.isep.eleve.javaproject.listener.DataUpdateListener;
@Controller
public class AddAssetController {
    @FXML
    private ChoiceBox<String> assetToAddChoiceBox;
    @FXML
    private TextField price;
    @FXML
    private TextField quantity;
    @FXML
    private TextField interestRate;
    @FXML
    private ChoiceBox<String> portfolioToAddChoiceBox;
    private final AssetsService assetsService;
    private final UserSession userSession;
    private final PortfolioSession portfolioSession;
    private final CashSession cashSession;
    private final AssetsRepository assetsRepository;
    private final ApplicationEventPublisher eventPublisher;
    private static final Logger logger = LoggerFactory.getLogger(AddAssetController.class);
    @Autowired
    AddAssetController(AssetsService assetsService, UserSession userSession, PortfolioSession portfolioSession, CashSession cashSession, AssetsRepository assetsRepository, ApplicationEventPublisher eventPublisher) {
        this.assetsService = assetsService;
        this.userSession = userSession;
        this.portfolioSession = portfolioSession;
        this.cashSession = cashSession;
        this.assetsRepository = assetsRepository;
        this.eventPublisher = eventPublisher;
    }
    @FXML 
    public void initialize() {
        List<String> portfolioNames = userSession.getCurrentUser().getPortfolios().stream().map(portfolio -> portfolio.getPortfolioName()).collect(java.util.stream.Collectors.toList());
        System.err.println(portfolioNames);
        assetToAddChoiceBox.setItems(FXCollections.observableArrayList("BTC", "AAPL", "CASH", "FIXED_DEPOSIT"));
        portfolioToAddChoiceBox.setItems(FXCollections.observableArrayList(portfolioNames));
    }
    public void handleUserInformationAction(ActionEvent event) {
    }

    public void handleConfirmationAction(ActionEvent event) throws NumberFormatException, IllegalArgumentException, IOException {
      Constants.ASSET_TYPE assetType= Constants.ASSET_TYPE_MAP.get(assetToAddChoiceBox.getValue());
      BigDecimal interestRate_ = null;
      List<Portfolio> portfolios = userSession.getCurrentUser().getPortfolios();
      Portfolio portfolio = portfolios.stream().filter(p -> p.getPortfolioName().equals(portfolioToAddChoiceBox.getValue())).findFirst().get();
      List<Asset> assets = userSession.getCurrentUser().getPortfolios().stream().filter(p -> p.getPortfolioName().equals(portfolioToAddChoiceBox.getValue())).findFirst().get().getAssets();
      Asset asset = assets.stream().filter(a -> a.getAssetName().equals(assetToAddChoiceBox.getValue())).findFirst().orElse(null);
      eventPublisher.publishEvent(new AssetChangedEvent(this, asset));
      if (assetType != Constants.ASSET_TYPE.FIXED_DEPOSIT) {
        interestRate_ = new BigDecimal(0);
      } else {
        interestRate_ = new BigDecimal(this.interestRate.getText());
      }
      
      eventPublisher.publishEvent(new PortfolioChangedEvent(this, portfolio));
      if (asset != null)  {
        logger.info("Asset already exists");
        assetsService.changeAssetQuantity(asset.getAssetId(), Integer.parseInt(quantity.getText()), Constants.CHANGE_TYPE.ADD);
      } else {
        // ToDo remaind user to fill in the interest rate if the asset is a fixed deposit
        logger.info("Asset does not exist");
        assetsService.createAsset(assetToAddChoiceBox.getValue(), Integer.parseInt(quantity.getText()), new BigDecimal( Integer.parseInt(price.getText())), assetType, interestRate_, portfolio.getPortfolioId(), true);
      }

      

    }

    public void handleLogOutAction(ActionEvent event) {
    }
}
