package com.isep.eleve.javaproject.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.isep.eleve.javaproject.Tools.FileOperation;
import com.isep.eleve.javaproject.model.Asset;
import com.isep.eleve.javaproject.model.MarketTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class MarketTransactionRepository {
  private static final String EXTERNAL_FILE_PATH = System.getProperty("user.home") + File.separator + "App" + File.separator + "databaseMarketTransaction.json";
  private Logger logger = LoggerFactory.getLogger(MarketTransactionRepository.class);
  private FileOperation fileOperation;
  /**
   * @param fileOperation the FileOperation used for file operations
   */
  @Autowired
  public MarketTransactionRepository(FileOperation fileOperation) {
    this.fileOperation = fileOperation;
  }
  /**
   * @return a list of all portfolios
   * @throws IOException if an I/O error occurs
   */
  public List<MarketTransaction> findAll() throws IOException {
    TypeReference<List<MarketTransaction>> typeReference = new TypeReference<List<MarketTransaction>>() {};
    return fileOperation.readListFromFile(EXTERNAL_FILE_PATH, typeReference);
  }

  /**
   * 
   * 
   * @param market the portfolio to be saved
   * @throws IOException if an I/O error occurs
   */
  public void save(MarketTransaction marketTransaction) throws IOException {
    List<MarketTransaction> marketTransactions = findAll();
    if (marketTransactions.size() == 0) {
      marketTransactions = new ArrayList<>();
      marketTransactions.add(marketTransaction);
      logger.info("new marketTransaction created in vide ficher: " + marketTransaction.getAssetName());
    } else {
      
    boolean flag = false;
    for (int i = 0; i < marketTransactions.size(); i++) {
        if (marketTransactions.get(i).getMarketTransactionId() == marketTransaction.getMarketTransactionId()) {
            marketTransactions.set(i, marketTransaction); 
            flag = true;
            logger.info("marketTransaction updated: " + marketTransaction.getAssetName());
            break;
        }
    }
    if (flag == false) {
        marketTransactions.add(marketTransaction);
        logger.info("new asset added: " + marketTransaction.getMarketTransactionName());
    }
    }
    fileOperation.writeListToFile(EXTERNAL_FILE_PATH, marketTransactions);
}

  /**
   * 
   * 
   * @param portfolioId the ID of the portfolio to find
   * @return the found portfolio, or null if not found
   * @throws IOException if an I/O error occurs
   */
  public MarketTransaction findByMarketTransactionId(int marketTransactionId) throws IOException {
    List<MarketTransaction> marketTransactions = findAll();
    return marketTransactions.stream().filter(m -> m.getMarketTransactionId() == marketTransactionId).findFirst().orElse(null);
  }


  public void delete(MarketTransaction marketTransaction) throws IOException {
    List<MarketTransaction> marketTransactions = findAll();
    marketTransactions.remove(marketTransaction);
    fileOperation.writeListToFile(EXTERNAL_FILE_PATH, marketTransactions);
  }
}
