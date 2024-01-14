package com.isep.eleve.javaproject.service.marketService;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.isep.eleve.javaproject.events.MarketTransactionCreated;
import com.isep.eleve.javaproject.Tools.Constants;
import com.isep.eleve.javaproject.model.MarketTransaction;
import com.isep.eleve.javaproject.repository.MarketTransactionRepository;
import com.isep.eleve.javaproject.service.TransactionService;
import com.isep.eleve.javaproject.session.MarketTransactionSession;

@Service
public class MarketTransactionService {
  private final MarketTransactionRepository marketTransactionRepository;
  private final MarketTransactionSession marketTransactionSession;
  private final TransactionService transactionService;
  private final ApplicationEventPublisher eventApplication;
  public MarketTransactionService(MarketTransactionRepository marketTransactionRepository, MarketTransactionSession marketTransactionSession, TransactionService transactionService, ApplicationEventPublisher eventApplication) {
    this.marketTransactionRepository = marketTransactionRepository;
    this.marketTransactionSession = marketTransactionSession;
    this.transactionService = transactionService;
    this.eventApplication = eventApplication;
  }
  public void recordMarketTransaction() throws IOException {
    marketTransactionRepository.save(marketTransactionSession.getMarketTransaction());
  }
  public void addNewMarketTransaction(String assetName, int assetId, int publisherId, BigDecimal price, int quantity, String marketTransactionName, int portfolioId) throws IOException {
    MarketTransaction marketTransaction = new MarketTransaction(price, quantity, publisherId, assetName, marketTransactionName, portfolioId, assetId);
    eventApplication.publishEvent(new MarketTransactionCreated(this, marketTransaction));
    //marketTransactionSession.setMarketTransaction(marketTransaction);
  }
  public void executeTransaction(Constants.TRANSACTION_TYPE transactionType) throws IOException {
    transactionService.executeTransaction(marketTransactionSession.getMarketTransaction().getQuantity(), marketTransactionSession.getMarketTransaction().getPrice(), marketTransactionSession.getMarketTransaction().getPortfolioId(),marketTransactionSession.getMarketTransaction().getAssetName(), transactionType);
  }
}
