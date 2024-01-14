package com.isep.eleve.javaproject.controller;

import com.isep.eleve.javaproject.App;
import com.isep.eleve.javaproject.events.AssetChangedEvent;
import com.isep.eleve.javaproject.events.PortfolioChangedEvent;
import com.isep.eleve.javaproject.model.Asset;
import com.isep.eleve.javaproject.model.Portfolio;
import com.isep.eleve.javaproject.repository.AssetsRepository;
import com.isep.eleve.javaproject.service.portfolioServices.AssetsService;
import com.isep.eleve.javaproject.session.CashSession;
import com.isep.eleve.javaproject.session.PortfolioSession;
import com.isep.eleve.javaproject.session.UserSession;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
/**
 * remi
 */
@Controller
public class SellAssetController {
    @FXML
    private TextField price;
    @FXML
    private TextField quantity;
    @FXML
    private ChoiceBox<String> assetToSellChoiceBox;
    @FXML 
    private ChoiceBox<String> portfolioOfAssetToSellChoiceBox;
    private final AssetsService assetsService;
    private final UserSession userSession;
    private final PortfolioSession portfolioSession;
    private final CashSession cashSession;
    private final AssetsRepository assetsRepository;
    private final ApplicationEventPublisher eventPublisher;
    private static final Logger logger = LoggerFactory.getLogger(SellAssetController.class);
    @Autowired
    SellAssetController(AssetsService assetsService, UserSession userSession, PortfolioSession portfolioSession, CashSession cashSession, AssetsRepository assetsRepository, ApplicationEventPublisher eventPublisher) {
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
    assetToSellChoiceBox.setItems(FXCollections.observableArrayList("BTC", "AAPL", "CASH", "FIXED_DEPOSIT"));
    portfolioOfAssetToSellChoiceBox.setItems(FXCollections.observableArrayList(portfolioNames));
    }
    public void handleUserInformationAction(ActionEvent event) {
    }

    public void handleConfirmationAction(ActionEvent event) {

      // choise portfolio
      List<Portfolio> portfolios = userSession.getCurrentUser().getPortfolios();
      Portfolio portfolio = portfolios.stream().filter(p -> p.getPortfolioName().equals(portfolioOfAssetToSellChoiceBox.getValue())).findFirst().get();
      eventPublisher.publishEvent(new PortfolioChangedEvent(this, portfolio));

      // choise asset
      List<Asset> assets = userSession.getCurrentUser().getPortfolios().stream().filter(p -> p.getPortfolioName().equals(portfolioOfAssetToSellChoiceBox.getValue())).findFirst().get().getAssets();
      Asset asset = assets.stream().filter(a -> a.getAssetName().equals(assetToSellChoiceBox.getValue())).findFirst().orElse(null);
      if (asset == null) {
        logger.info("error : asset is null" );
      } else {
        eventPublisher.publishEvent(new AssetChangedEvent(this, asset));
      }
      // get price
      BigDecimal price_ = new BigDecimal(Integer.parseInt(this.price.getText()));
      // get quantity
      int quantity_ = Integer.parseInt(this.quantity.getText());

      // autre logique 
    }

    public void handleLogOutAction(ActionEvent event) {
    }
}
