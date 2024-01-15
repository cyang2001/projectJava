package com.isep.eleve.javaproject.controller;

import com.isep.eleve.javaproject.App;
import com.isep.eleve.javaproject.model.Market;
import com.isep.eleve.javaproject.repository.AssetsRepository;
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

@Controller
public class BuyCryptoController {
    @FXML
    private ChoiceBox<String> trasaction;
    @FXML
    private ChoiceBox<String> portfioToAddCrypto;
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
    BuyCryptoController(AssetsService assetsService, UserSession userSession, PortfolioSession portfolioSession, CashSession cashSession, AssetsRepository assetsRepository, ApplicationEventPublisher eventPublisher, MarketService marketService, MarketTransactionService marketTransactionService, MarketTransactionSession marketTransactionSession, MarketSession marketSession) {
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
    public void initialize() throws IOException {
        List<String> portfolioNames = userSession.getCurrentUser().getPortfolios().stream().map(portfolio -> portfolio.getPortfolioName()).collect(java.util.stream.Collectors.toList());
        //System.err.println(portfolioNames);

        List<Integer> marketTransactionIds =         marketSession.getMarket().getMarketTransactionIds();
        List<String> marketTransactionNames = marketTransactionIds.stream().map(marketTransactionId -> {
          try {
            return marketTransactionService.getMarketTransactionNameByTransactionId(marketTransactionId);
          } catch (IOException e) {
            e.printStackTrace();
          }
          return null;
        }).collect(java.util.stream.Collectors.toList());
        List<Integer> marketTransactionQuantitys = marketTransactionIds.stream().map(marketTransactionId -> {
          try {
            return marketTransactionService.getMarketTransactionQuantityByTransactionId(marketTransactionId);
          } catch (IOException e) {
            e.printStackTrace();
          }
          return null;
        }).collect(java.util.stream.Collectors.toList());
        List<BigDecimal> marketTransactionPrices = marketTransactionIds.stream().map(marketTransactionId -> {
          try {
            return marketTransactionService.getMarketTransactionPriceByTransactionId(marketTransactionId);
          } catch (IOException e) {
            e.printStackTrace();
          }
          return null;
        }).collect(java.util.stream.Collectors.toList());
        List<String> marketTransactionPublisherNames = marketTransactionIds.stream().map(marketTransactionId -> {
          try {
            return marketTransactionService.getMarketTransactionPublisherNameByTransactionId(marketTransactionId);
          } catch (IOException e) {
            e.printStackTrace();
          }
          return null;
        }).collect(java.util.stream.Collectors.toList());
        trasaction.setItems(FXCollections.observableArrayList(marketTransactionNames));

        portfioToAddCrypto.setItems(FXCollections.observableArrayList(portfolioNames));
        marketSession.setMarket(new Market("Market Cypto"));
    }
    public void handleUserInformationAction(ActionEvent event) {
    }

    public void handleConfirmationAction(ActionEvent event) {
    }

    public void handleLogOutAction(ActionEvent event) {
    }
}
