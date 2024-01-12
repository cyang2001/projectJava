package com.isep.eleve.javaproject.repository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.isep.eleve.javaproject.Tools.FileOperation;
import com.isep.eleve.javaproject.model.Market;
import com.isep.eleve.javaproject.model.Portfolio;

@Repository
public class MarketRepository {
  private static final String EXTERNAL_FILE_PATH = System.getProperty("user.home") + File.separator + "App" + File.separator + "databaseMarket.json";

  private FileOperation fileOperation;
  /**
   * 
   * 
   * @param fileOperation the FileOperation used for file operations
   */
  @Autowired
  public MarketRepository(FileOperation fileOperation) {
    this.fileOperation = fileOperation;
  }
  /**
   * 
   * 
   * @return a list of all portfolios
   * @throws IOException if an I/O error occurs
   */
  public List<Market> findAll() throws IOException {
    return fileOperation.readListFromFile(EXTERNAL_FILE_PATH, Market.class);
  }

  /**
   * 
   * 
   * @param market the portfolio to be saved
   * @throws IOException if an I/O error occurs
   */
  public void save(Market market) throws IOException {
    List<Market> markets = findAll();
    markets.add(market);
    fileOperation.writeListToFile(EXTERNAL_FILE_PATH, markets);
  }

  /**
   * 
   * 
   * @param portfolioId the ID of the portfolio to find
   * @return the found portfolio, or null if not found
   * @throws IOException if an I/O error occurs
   */
  public Market findByMarketId(int marketId) throws IOException {
    List<Market> markets = findAll();
    return markets.stream().filter(m -> m.getMarketId() == marketId).findFirst().orElse(null);
  }


  public void delete(Market market) throws IOException {
    List<Market> markets = findAll();
    markets.remove(market);
    fileOperation.writeListToFile(EXTERNAL_FILE_PATH, markets);
  }
}
