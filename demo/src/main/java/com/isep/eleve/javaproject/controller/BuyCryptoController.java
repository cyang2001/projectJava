package com.isep.eleve.javaproject.controller;

import com.isep.eleve.javaproject.App;
import com.isep.eleve.javaproject.events.MarketTransactionSelectedEvent;
import com.isep.eleve.javaproject.events.PortfolioChangedEvent;
import com.isep.eleve.javaproject.model.Market;
import com.isep.eleve.javaproject.model.MarketTransaction;
import com.isep.eleve.javaproject.model.Portfolio;
import com.isep.eleve.javaproject.model.Transaction;
import com.isep.eleve.javaproject.repository.AssetsRepository;
import com.isep.eleve.javaproject.service.TransactionService;
import com.isep.eleve.javaproject.service.marketService.MarketService;
import com.isep.eleve.javaproject.service.marketService.MarketTransactionService;
import com.isep.eleve.javaproject.service.portfolioServices.AssetsService;
import com.isep.eleve.javaproject.session.CashSession;
import com.isep.eleve.javaproject.session.MarketSession;
import com.isep.eleve.javaproject.session.MarketTransactionSession;
import com.isep.eleve.javaproject.session.PortfolioSession;
import com.isep.eleve.javaproject.session.UserSession;
import com.isep.eleve.javaproject.Tools.*;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;

@Controller
public class BuyCryptoController {
    @FXML
    private ChoiceBox<MarketTransactionChoiceBoxItem> transaction;
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
    private final TransactionService transactionService;
    private final MarketSession marketSession;
    private static final Logger logger = LoggerFactory.getLogger(AddAssetController.class);
    @Autowired
    BuyCryptoController(AssetsService assetsService, UserSession userSession, PortfolioSession portfolioSession, CashSession cashSession, AssetsRepository assetsRepository, ApplicationEventPublisher eventPublisher, MarketService marketService, MarketTransactionService marketTransactionService, MarketTransactionSession marketTransactionSession, MarketSession marketSession, TransactionService transactionService) {
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
        this.transactionService = transactionService;
    }
    @FXML 
    public void initialize() throws IOException {
        List<String> portfolioNames = userSession.getCurrentUser().getPortfolios().stream().map(portfolio -> portfolio.getPortfolioName()).collect(java.util.stream.Collectors.toList());
        //System.err.println(portfolioNames);
        List<Integer> marketTransactionIds = marketSession.getMarket().getMarketTransactionIds();
        List<Boolean> marketTransactionIsBuys = marketTransactionIds.stream().map(marketTransactionId -> {
          try {
            return marketTransactionService.getMarketTransactionIsBuyByMakretId(marketTransactionId);
          } catch (IOException e) {
            e.printStackTrace();
          }
          return null;
        }).collect(java.util.stream.Collectors.toList());
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
        List<MarketTransactionChoiceBoxItem> marketTransactionChoiceBoxItems = new ArrayList<>();
        for (int i = 0; i < marketTransactionNames.size(); i++) {
            if (marketTransactionIsBuys.get(i) == true) {
                continue;
            }
            String name = marketTransactionNames.get(i);
            Integer quantity = marketTransactionQuantitys.get(i);
            BigDecimal price = marketTransactionPrices.get(i);
            String publisherName = marketTransactionPublisherNames.get(i);

            MarketTransactionChoiceBoxItem transaction = new MarketTransactionChoiceBoxItem(name, quantity, price, publisherName, marketTransactionIds.get(i));
            marketTransactionChoiceBoxItems.add(transaction);
        }
        this.transaction.setItems(FXCollections.observableArrayList(marketTransactionChoiceBoxItems));
        
        portfioToAddCrypto.setItems(FXCollections.observableArrayList(portfolioNames));
        marketSession.setMarket(new Market("Market Cypto"));
    }

    public void handleConfirmationAction(ActionEvent event) throws IOException {
      List<Portfolio> portfolios = userSession.getCurrentUser().getPortfolios();
      Portfolio portfolio = portfolios.stream().filter(p -> p.getPortfolioName().equals(portfioToAddCrypto.getValue())).findFirst().get();
      eventPublisher.publishEvent(new PortfolioChangedEvent(this, portfolio));
      MarketTransactionChoiceBoxItem marketTransactionChoiceBoxItem = transaction.getValue();
      int marketTransactionId = marketTransactionChoiceBoxItem.getMarketTransactionId();
      MarketTransaction marketTransaction = marketTransactionService.getMarketTransactionByMarketTransactionId(marketTransactionId);
      eventPublisher.publishEvent(new MarketTransactionSelectedEvent(this, marketTransaction));
      transactionService.executeTransaction(marketTransactionSession.getMarketTransaction().getQuantity(), marketTransactionSession.getMarketTransaction().getPrice(), marketTransactionSession.getMarketTransaction().getPortfolioId(), marketTransactionSession.getMarketTransaction().getAssetName(), Constants.TRANSACTION_TYPE.BUT_MARKET);
    }

    public class MarketTransactionChoiceBoxItem {
      private String name;
      private Integer quantity;
      private BigDecimal price;
      private String publisherName;
      private int marketTransactionId;
      public MarketTransactionChoiceBoxItem(String name, Integer quantity, BigDecimal price, String publisherName, int marketTransactionId) {
          this.name = name;
          this.quantity = quantity;
          this.price = price;
          this.publisherName = publisherName;
          this.marketTransactionId = marketTransactionId;
      }
  
      @Override
      public String toString() {
          return name + " - " + quantity + " - " + price + " - " + publisherName;
      }
  
      public void setName(String name) {
          this.name = name;
      }
      public String getName() {
          return name;
      }
      public void setQuantity(Integer quantity) {
          this.quantity = quantity;
      }
      public Integer getQuantity() {
          return quantity;
      }
      public void setPrice(BigDecimal price) {
          this.price = price;
      }
      public BigDecimal getPrice() {
          return price;
      }
      public void setPublisherName(String publisherName) {
          this.publisherName = publisherName;
      }
      public String getPublisherName() {
          return publisherName;
      }
      public void setMarketTransactionId(int marketTransactionId) {
          this.marketTransactionId = marketTransactionId;
      }
      public int getMarketTransactionId() {
          return marketTransactionId;
      }
  }
  
}
