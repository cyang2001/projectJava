package com.isep.eleve.javaproject.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.isep.eleve.javaproject.Tools.Constants;
import com.isep.eleve.javaproject.Tools.Constants.TRANSACTION_TYPE;
import com.isep.eleve.javaproject.events.AssetQuantityChangedEvent;
import com.isep.eleve.javaproject.events.CashEarnedEvent;
import com.isep.eleve.javaproject.events.CashSpentEvent;
import com.isep.eleve.javaproject.model.Transaction;
import com.isep.eleve.javaproject.repository.TransactionRepository;
import com.isep.eleve.javaproject.service.portfolioServices.AssetsService;

@Service
public class TransactionService {
  private final TransactionRepository transactionRepository;
  private final AssetsService assetsService;
  private final ApplicationEventPublisher eventApplication;
  @Autowired
  public TransactionService(TransactionRepository transactionRepository, AssetsService assetsService, ApplicationEventPublisher eventApplication) {
    this.transactionRepository = transactionRepository;
    this.assetsService = assetsService;
    this.eventApplication = eventApplication;
  }
  public void recordTransaction(Transaction transaction) throws IOException {
    transactionRepository.save(transaction);
  }
  public List<Transaction> getTransactions() throws IOException {
    return transactionRepository.findAll();
  }
  public void executetransaction(int quantity, BigDecimal price, int portfolioId, int assetId, TRANSACTION_TYPE transitionType) throws IOException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date(System.currentTimeMillis());
    Transaction transaction = new Transaction(portfolioId, assetId, assetId, price, transitionType, sdf.format(date));
    if (transitionType == TRANSACTION_TYPE.BUY) {
      Constants.CHANGE_TYPE changeType = Constants.CHANGE_TYPE.ADD;
      assetsService.changeAssetQuantity(assetId, quantity,changeType);
      BigDecimal total = price.multiply(new BigDecimal(quantity));
      eventApplication.publishEvent(new CashEarnedEvent(this, total));
    } else {
      Constants.CHANGE_TYPE changeType = Constants.CHANGE_TYPE.SUBTRACT;
      assetsService.changeAssetQuantity(assetId, quantity, changeType);
      BigDecimal total = price.multiply(new BigDecimal(quantity));
      eventApplication.publishEvent(new CashSpentEvent(this, total));
    }
    
    transactionRepository.save(transaction);


  }
}
