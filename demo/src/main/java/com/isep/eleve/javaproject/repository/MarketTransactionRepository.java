package com.isep.eleve.javaproject.repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.isep.eleve.javaproject.Tools.FileOperation;
import com.isep.eleve.javaproject.model.MarketTransaction;

@Repository
public class MarketTransactionRepository {
  private static final String EXTERNAL_FILE_PATH = System.getProperty("user.home") + File.separator + "App" + File.separator + "databaseMarketTransaction.json";

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
    return fileOperation.readListFromFile(EXTERNAL_FILE_PATH, MarketTransaction.class);
  }

  /**
   * 
   * 
   * @param market the portfolio to be saved
   * @throws IOException if an I/O error occurs
   */
  public void save(MarketTransaction marketTransaction) throws IOException {
    List<MarketTransaction> marketTransactions = findAll();
    marketTransactions.add(marketTransaction);
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
