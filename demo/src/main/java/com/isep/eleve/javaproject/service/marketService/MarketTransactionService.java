package com.isep.eleve.javaproject.service.marketService;

import java.io.IOException;
import java.math.BigDecimal;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import com.isep.eleve.javaproject.events.MarketTransactionCreatedEvent;
import com.isep.eleve.javaproject.events.SellCryptoEvent;
import com.isep.eleve.javaproject.Tools.Constants;
import com.isep.eleve.javaproject.model.MarketTransaction;
import com.isep.eleve.javaproject.repository.MarketTransactionRepository;
import com.isep.eleve.javaproject.repository.UserRepository;
import com.isep.eleve.javaproject.service.TransactionService;
import com.isep.eleve.javaproject.session.MarketTransactionSession;

/**
 * Service class for handling market transactions.
 * Provides functionalities for managing market transactions including recording, retrieving, and executing transactions.
 */
@Service
public class MarketTransactionService {
  private final MarketTransactionRepository marketTransactionRepository;
  private final MarketTransactionSession marketTransactionSession;
  private final TransactionService transactionService;
  private final ApplicationEventPublisher eventApplication;
  private final UserRepository userRepository;
  private final ApplicationEventPublisher eventPublisher;

  /**
   * Constructs a MarketTransactionService with necessary dependencies.
   *
   * @param marketTransactionRepository Repository for market transactions.
   * @param marketTransactionSession Session for current market transaction.
   * @param transactionService Service for handling transactions.
   * @param eventApplication Event publisher for application events.
   * @param userRepository Repository for user information.
   * @param eventPublisher General event publisher.
   */
  public MarketTransactionService(MarketTransactionRepository marketTransactionRepository, MarketTransactionSession marketTransactionSession, TransactionService transactionService, ApplicationEventPublisher eventApplication, UserRepository userRepository, ApplicationEventPublisher eventPublisher) {
    this.marketTransactionRepository = marketTransactionRepository;
    this.marketTransactionSession = marketTransactionSession;
    this.transactionService = transactionService;
    this.eventApplication = eventApplication;
    this.userRepository = userRepository;
    this.eventPublisher = eventPublisher;
  }

  /**
   * Records the current market transaction to the repository.
   * @throws IOException if there is an issue with saving the market transaction
   */
  public void recordMarketTransaction() throws IOException {
    marketTransactionRepository.save(marketTransactionSession.getMarketTransaction());
  }

  /**
   * Adds a new market transaction and publishes an event.
   * 
   * @param assetName Name of the asset involved in the transaction.
   * @param assetId Unique identifier of the asset.
   * @param publisherId Identifier of the publisher.
   * @param price Price of the asset in the transaction.
   * @param quantity Quantity of the asset in the transaction.
   * @param marketTransactionName Name of the market transaction.
   * @param portfolioId Identifier of the portfolio involved.
   * @throws IOException if there is an issue with processing the transaction
   */
  public void addNewMarketTransaction(String assetName, int assetId, int publisherId, BigDecimal price, int quantity, String marketTransactionName, int portfolioId) throws IOException {
    MarketTransaction marketTransaction = new MarketTransaction(price, quantity, publisherId, assetName, marketTransactionName, portfolioId, assetId);
    eventApplication.publishEvent(new MarketTransactionCreatedEvent(this, marketTransaction));
  }

  /**
   * Retrieves the name of the market transaction by its ID.
   *
   * @param marketTransactionId The ID of the market transaction.
   * @return The name of the market transaction.
   * @throws IOException if there is an issue with retrieving the market transaction
   */
  public String getMarketTransactionNameByTransactionId(int marketTransactionId) throws IOException {
    return marketTransactionRepository.findByMarketTransactionId(marketTransactionId).getMarketTransactionName();
  }

  /**
   * Retrieves the quantity of the market transaction by its ID.
   *
   * @param marketTransactionId The ID of the market transaction.
   * @return The quantity of the market transaction.
   * @throws IOException if there is an issue with retrieving the market transaction
   */
  public int getMarketTransactionQuantityByTransactionId(int marketTransactionId) throws IOException {
    return marketTransactionRepository.findByMarketTransactionId(marketTransactionId).getQuantity();
  }

  /**
   * Retrieves the price of the market transaction by its ID.
   *
   * @param marketTransactionId The ID of the market transaction.
   * @return The price of the market transaction.
   * @throws IOException if there is an issue with retrieving the market transaction
   */
  public BigDecimal getMarketTransactionPriceByTransactionId(int marketTransactionId) throws IOException {
    return marketTransactionRepository.findByMarketTransactionId(marketTransactionId).getPrice();
  }

  /**
   * Retrieves the publisher name of the market transaction by its ID.
   *
   * @param marketTransactionId The ID of the market transaction.
   * @return The publisher name of the market transaction.
   * @throws IOException if there is an issue with retrieving the market transaction or the publisher information
   */
  public String getMarketTransactionPublisherNameByTransactionId(int marketTransactionId) throws IOException {
    return userRepository.findByUserId(marketTransactionRepository.findByMarketTransactionId(marketTransactionId).getPublisherId()).getUserName(); 
  }

  /**
   * Executes a transaction based on the given transaction type.
   *
   * @param transactionType The type of the transaction.
   * @throws IOException if there is an issue with executing the transaction
   */
  public void executeTransaction(Constants.TRANSACTION_TYPE transactionType) throws IOException {
    transactionService.executeTransaction(marketTransactionSession.getMarketTransaction().getQuantity(), marketTransactionSession.getMarketTransaction().getPrice(), marketTransactionSession.getMarketTransaction().getPortfolioId(),marketTransactionSession.getMarketTransaction().getAssetName(), transactionType);
    eventPublisher.publishEvent(new SellCryptoEvent(this, marketTransactionSession.getMarketTransaction()));
  }

  /**
   * Retrieves the market transaction by its ID.
   *
   * @param marketTransactionId The ID of the market transaction.
   * @return The market transaction.
   * @throws IOException if there is an issue with retrieving the market transaction
   */
  public MarketTransaction getMarketTransactionByMarketTransactionId(int marketTransactionId) throws IOException {
    return marketTransactionRepository.findByMarketTransactionId(marketTransactionId);
  }

  /**
   * Retrieves whether the market transaction is a buy transaction or not.
   *
   * @param marketTransactionId The ID of the market transaction.
   * @return true if the market transaction is a buy transaction, false otherwise.
   * @throws IOException if there is an issue with retrieving the market transaction
   */
  public Boolean getMarketTransactionIsBuyByMakretId(int marketTransactionId) throws IOException {
    return marketTransactionRepository.findByMarketTransactionId(marketTransactionId).getIsSuccess();
  }
}
