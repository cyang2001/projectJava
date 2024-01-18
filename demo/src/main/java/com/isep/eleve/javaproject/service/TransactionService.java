package com.isep.eleve.javaproject.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.isep.eleve.javaproject.events.BuyAssetEvent;
import com.isep.eleve.javaproject.model.Asset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.isep.eleve.javaproject.Tools.Constants;
import com.isep.eleve.javaproject.Tools.Constants.ASSET_TYPE;
import com.isep.eleve.javaproject.Tools.Constants.TRANSACTION_TYPE;

import com.isep.eleve.javaproject.events.CashEarnedEvent;
import com.isep.eleve.javaproject.events.CashSpentEvent;
import com.isep.eleve.javaproject.events.SellAssetEvent;
import com.isep.eleve.javaproject.model.MarketTransaction;
import com.isep.eleve.javaproject.model.Portfolio;
import com.isep.eleve.javaproject.model.Transaction;
import com.isep.eleve.javaproject.model.assets.liquide.Cash;
import com.isep.eleve.javaproject.repository.MarketTransactionRepository;
import com.isep.eleve.javaproject.repository.TransactionRepository;
import com.isep.eleve.javaproject.repository.UserRepository;
import com.isep.eleve.javaproject.service.portfolioServices.AssetsService;
import com.isep.eleve.javaproject.session.CashSession;
import com.isep.eleve.javaproject.session.MarketTransactionSession;
import com.isep.eleve.javaproject.session.PortfolioSession;

@Service
public class TransactionService {
  private final TransactionRepository transactionRepository;
  private final AssetsService assetsService;
  private final ApplicationEventPublisher eventApplication;
  private final MarketTransactionRepository marketTransactionRepository;
  private final MarketTransactionSession marketTransactionSession;
  private final PortfolioSession portfolioSession;
  private final UserRepository userRepository;
  private final CashSession cashSession;
  private final Logger logger = LoggerFactory.getLogger(TransactionService.class);
  @Autowired
  public TransactionService(TransactionRepository transactionRepository, AssetsService assetsService, ApplicationEventPublisher eventApplication, MarketTransactionRepository marketTransactionRepository, PortfolioSession portfolioSession, CashSession cashSession, MarketTransactionSession marketTransactionSession, UserRepository userRepository) {
    this.transactionRepository = transactionRepository;
    this.assetsService = assetsService;
    this.eventApplication = eventApplication;
    this.marketTransactionRepository = marketTransactionRepository;
    this.portfolioSession = portfolioSession;
    this.cashSession = cashSession;
    this.marketTransactionSession = marketTransactionSession;
    this.userRepository = userRepository;
  }
  public void recordTransaction(Transaction transaction) throws IOException {
    transactionRepository.save(transaction);
  }
  public List<Transaction> getTransactions() throws IOException {
    return transactionRepository.findAll();
  }
  /**
   * for sell assets
   * @param quantity
   * @param price
   * @param portfolioId
   * @param assetId
   * @param transitionType
   * @throws IOException
   */
  public void executeTransaction(int quantity, BigDecimal price, int portfolioId, int assetId, TRANSACTION_TYPE transitionType, int ownerId) throws IOException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date(System.currentTimeMillis());
    Transaction transaction = new Transaction(portfolioId, assetId, quantity, price, transitionType, sdf.format(date), ownerId);
    if (transitionType == TRANSACTION_TYPE.BUY) {
      Constants.CHANGE_TYPE changeType = Constants.CHANGE_TYPE.ADD;
      if (cashSession.getCash().getValue().compareTo(price.multiply(new BigDecimal(quantity))) >= 0) {
        assetsService.changeAssetQuantity(assetId, quantity, changeType);
        BigDecimal total = price.multiply(new BigDecimal(quantity));
        eventApplication.publishEvent(new CashSpentEvent(this, total));
        eventApplication.publishEvent(new BuyAssetEvent(this, true, transaction.getTransactionId()));
      } else {
        logger.error("Not enough cash");
        eventApplication.publishEvent(new BuyAssetEvent(this, false, transaction.getTransactionId()));
      }
    } else if(transitionType == TRANSACTION_TYPE.SELL) {
      Constants.CHANGE_TYPE changeType = Constants.CHANGE_TYPE.SUBTRACT;
      if (assetsService.getAssetQuantity(assetId) >= quantity) {
        assetsService.changeAssetQuantity(assetId, quantity, changeType);
        BigDecimal total = price.multiply(new BigDecimal(quantity));
        eventApplication.publishEvent(new CashEarnedEvent(this, total));
        eventApplication.publishEvent(new SellAssetEvent(this, true, transaction.getTransactionId()));
      } else {
        logger.error("Not enough assets");
        eventApplication.publishEvent(new SellAssetEvent(this, false, transaction.getTransactionId()));
      }
    }
    transactionRepository.save(transaction);
  }
  /**
   * Executes a transaction with the given parameters.
   *
   * @param quantity       the quantity of the asset to transact
   * @param price          the price of the asset
   * @param portfolioId    the ID of the portfolio
   * @param assetName      the name of the asset
   * @param transitionType the type of the transaction
   * @throws IOException if an I/O error occurs
   */
  public void executeTransaction(int quantity, BigDecimal price, int portfolioId, String assetName, TRANSACTION_TYPE transitionType) throws IOException {
    int assetId = 0;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date(System.currentTimeMillis());
    Cash cash = cashSession.getCash();
    Portfolio portfolio = portfolioSession.getCurrentPortfolio();
    if (transitionType == TRANSACTION_TYPE.BUT_MARKET) {
      Constants.CHANGE_TYPE changeType = Constants.CHANGE_TYPE.ADD;
      boolean flag = false;
      if (cash.getValue().compareTo(price.multiply(new BigDecimal(quantity))) >= 0) {
        for (int i = 0; i < portfolio.getAssets().size(); i++) {
          if (portfolio.getAssets().get(i).getAssetName().equals(assetName)) {
            assetId = portfolio.getAssets().get(i).getAssetId();
            assetsService.changeAssetQuantity(assetId, quantity, changeType);
            BigDecimal total = price.multiply(new BigDecimal(quantity));
            eventApplication.publishEvent(new CashSpentEvent(this, total));
            flag = true;
            break;
          }
        }
        if (flag == false) {
          assetsService.createAsset(assetName, quantity, price, ASSET_TYPE.CRYPTO, price, portfolioId, true);
          BigDecimal total = price.multiply(new BigDecimal(quantity));
          eventApplication.publishEvent(new CashSpentEvent(this, total));
        }
        assetsService.changeAssetQuantity(userRepository.findByUserId(marketTransactionSession.getMarketTransaction().getPublisherId()).getPortfolios().stream().filter(p -> p.getPortfolioId() == marketTransactionSession.getMarketTransaction().getPortfolioId()).collect(java.util.stream.Collectors.toList()).get(0).getAssets().stream().filter(a -> a.getAssetName().equals("CASH")).collect(java.util.stream.Collectors.toList()).get(0).getAssetId(), price.multiply(new BigDecimal(quantity)), Constants.CHANGE_TYPE.ADD,true); 
        Transaction transaction = new Transaction(portfolioId, assetId, quantity, price, transitionType, sdf.format(date), marketTransactionSession.getMarketTransaction().getPublisherId());
        transactionRepository.save(transaction);
        marketTransactionSession.getMarketTransaction().setIsBuy(true);
      } else {
        logger.error("Not enough cash");
      }
      
    } else if(transitionType == TRANSACTION_TYPE.SELL_MARKET) {
      boolean flag = false;
      for (int i = 0; i <= portfolio.getAssets().size(); i++) {
        if (portfolio.getAssets().get(i).getAssetName().equals(assetName)) {
          assetId = portfolio.getAssets().get(i).getAssetId();
          Constants.CHANGE_TYPE changeType = Constants.CHANGE_TYPE.SUBTRACT;
          assetsService.changeAssetQuantity(assetId, quantity, changeType);
          flag = true;
          break;
        }
      }
      if (flag == false) {
        System.out.println("No such asset");
      }
      Transaction transaction = new Transaction(portfolioId, assetId, quantity, price, transitionType, sdf.format(date), marketTransactionSession.getMarketTransaction().getPublisherId());
      transactionRepository.save(transaction);
    }
  }
  /**
   * Executes a transaction with the given parameters.
   *
   * @param quantity      the quantity of the asset being transacted
   * @param price         the price of the asset being transacted
   * @param portfolioId   the ID of the portfolio associated with the transaction
   * @param transitionType   the type of transaction (e.g., BUY, SELL)
   * @param interestRate  the interest rate for fixed deposit assets (ignored for other asset types)
   * @param assetType     the type of asset being transacted
   * @param assetName     the name of the asset being transacted
   * @param ownerId       the ID of the owner associated with the transaction
   * @throws IOException  if an I/O error occurs while saving the transaction
   */
  public void executeTransaction(int quantity, BigDecimal price, int portfolioId, TRANSACTION_TYPE transitionType, BigDecimal interestRate, Constants.ASSET_TYPE assetType, String assetName, int ownerId) throws IOException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date(System.currentTimeMillis());
    Constants.CHANGE_TYPE changeType = Constants.CHANGE_TYPE.ADD;
    int transactionId = 0;
    if (cashSession.getCash().getValue().compareTo(price.multiply(new BigDecimal(quantity))) >= 0) {
      if (assetType.equals(Constants.ASSET_TYPE_MAP.get("FIXED_DEPOSIT"))) {
        Asset assetCreated =  assetsService.createAsset(assetName,quantity,price,assetType, interestRate,portfolioId,true );
        Transaction transaction = new Transaction(portfolioId, assetCreated.getAssetId() , quantity, price, transitionType, sdf.format(date), ownerId);
        transactionRepository.save(transaction);
        transactionId = transaction.getTransactionId();
      } else {
        Asset assetCreated = assetsService.createAsset(assetName,quantity,price,assetType,new BigDecimal(0), portfolioId, true);
        Transaction transaction = new Transaction(portfolioId, assetCreated.getAssetId(), quantity, price, transitionType, sdf.format(date), ownerId);
        transactionRepository.save(transaction);
        transactionId = transaction.getTransactionId();
      }
      BigDecimal total = price.multiply(new BigDecimal(quantity));
      eventApplication.publishEvent(new CashSpentEvent(this, total));
      eventApplication.publishEvent(new BuyAssetEvent(this, true, transactionId));
    } else {
      logger.error("Not enough cash");
      eventApplication.publishEvent(new BuyAssetEvent(this, false, transactionId));
    }



  }
  public List<Transaction>getTransactionByOwnerId(int transactionOwnerId) throws IOException {
    return transactionRepository.findByOwnerId(transactionOwnerId);
  }
}
