package com.isep.eleve.javaproject.controller;

import com.isep.eleve.javaproject.App;
import com.isep.eleve.javaproject.Tools.Constants;
import com.isep.eleve.javaproject.events.AssetChangedEvent;
import com.isep.eleve.javaproject.events.PortfolioChangedEvent;
import com.isep.eleve.javaproject.model.Asset;
import com.isep.eleve.javaproject.model.Market;
import com.isep.eleve.javaproject.model.MarketTransaction;
import com.isep.eleve.javaproject.model.Portfolio;
import com.isep.eleve.javaproject.repository.AssetsRepository;
import com.isep.eleve.javaproject.repository.MarketRepository;
import com.isep.eleve.javaproject.service.marketService.MarketService;
import com.isep.eleve.javaproject.service.marketService.MarketTransactionService;
import com.isep.eleve.javaproject.service.portfolioServices.AssetsService;
import com.isep.eleve.javaproject.session.CashSession;
import com.isep.eleve.javaproject.session.MarketSession;
import com.isep.eleve.javaproject.session.MarketTransactionSession;
import com.isep.eleve.javaproject.session.PortfolioSession;
import com.isep.eleve.javaproject.session.UserSession;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
/**
 * chen 
 */
@Controller
public class SellCryptoController {
    @FXML
    private ChoiceBox<String> cryptoToSellChoiceBox;
    @FXML
    private TextField price;
    @FXML
    private TextField quantity;
    @FXML
    private TextField marketTransactionName;
    @FXML
    private ChoiceBox<String> portfolioToSellChoiceBox;
    private final AssetsService assetsService;
    private final UserSession userSession;
    private final PortfolioSession portfolioSession;
    private final CashSession cashSession;
    private final AssetsRepository assetsRepository;
    private final MarketService marketService;
    private final MarketTransactionService marketTransactionService;
    private final ApplicationEventPublisher eventPublisher;
    private final MarketTransactionSession marketTransactionSession;
    private final MarketSession marketSession;
    private static final Logger logger = LoggerFactory.getLogger(AddAssetController.class);
    @Autowired
    SellCryptoController(AssetsService assetsService, UserSession userSession, PortfolioSession portfolioSession, CashSession cashSession, AssetsRepository assetsRepository, ApplicationEventPublisher eventPublisher, MarketService marketService, MarketTransactionService marketTransactionService, MarketTransactionSession marketTransactionSession, MarketSession marketSession) {
        this.assetsService = assetsService;
        this.userSession = userSession;
        this.portfolioSession = portfolioSession;
        this.cashSession = cashSession;
        this.assetsRepository = assetsRepository;
        this.eventPublisher = eventPublisher;
        this.marketService = marketService;
        this.marketTransactionService = marketTransactionService;
        this.marketTransactionSession = marketTransactionSession;
        this.marketSession = marketSession;
    }
    @FXML 
    public void initialize() {
        List<String> portfolioNames = userSession.getCurrentUser().getPortfolios().stream().map(portfolio -> portfolio.getPortfolioName()).collect(java.util.stream.Collectors.toList());
        //System.err.println(portfolioNames);
        cryptoToSellChoiceBox.setItems(FXCollections.observableArrayList("BTC"));
        portfolioToSellChoiceBox.setItems(FXCollections.observableArrayList(portfolioNames));
        //marketSession.setMarket(new Market("Market Cypto"));
    }

    public void handleConfirmationAction(ActionEvent event) throws NumberFormatException, IllegalArgumentException, IOException{
      if (marketSession.getMarket() == null ){
        marketSession.setMarket(new Market("Market Crypto"));
      }
      List<Portfolio> portfolios = userSession.getCurrentUser().getPortfolios();
      Portfolio portfolio = portfolios.stream().filter(p -> p.getPortfolioName().equals(portfolioToSellChoiceBox.getValue())).findFirst().get();
      List<Asset> assets = userSession.getCurrentUser().getPortfolios().stream().filter(p -> p.getPortfolioName().equals(portfolioToSellChoiceBox.getValue())).findFirst().get().getAssets();
      Asset asset = assets.stream().filter(a -> a.getAssetName().equals(cryptoToSellChoiceBox.getValue())).findFirst().orElse(null);
      if (asset!=null){
        eventPublisher.publishEvent(new AssetChangedEvent(this, asset));
      }
      eventPublisher.publishEvent(new PortfolioChangedEvent(this, portfolio));
      if (asset!=null){
        marketTransactionService.addNewMarketTransaction(asset.getAssetName(), asset.getAssetId(), asset.getOwnerId(), new BigDecimal(Integer.parseInt(price.getText())), Integer.parseInt(quantity.getText()), marketTransactionName.getText(), portfolio.getPortfolioId());
        marketService.addNewMarketTransaction(marketTransactionSession.getMarketTransaction());
        marketTransactionService.executeTransaction(Constants.TRANSACTION_TYPE.SELL_MARKET);
      }
    }
}