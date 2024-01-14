package com.isep.eleve.javaproject.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.sound.sampled.AudioFileFormat.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.isep.eleve.javaproject.Tools.FileOperation;
import com.isep.eleve.javaproject.model.Asset;
import com.isep.eleve.javaproject.model.Market;
import com.isep.eleve.javaproject.model.Portfolio;

@Repository
public class MarketRepository {
  private static final String EXTERNAL_FILE_PATH = System.getProperty("user.home") + File.separator + "App" + File.separator + "databaseMarket.json";
  private Logger logger = LoggerFactory.getLogger(MarketRepository.class);
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
    TypeReference<List<Market>> typeReference = new TypeReference<List<Market>>() {};
    return fileOperation.readListFromFile(EXTERNAL_FILE_PATH, typeReference);
  }

  /**
   * 
   * 
   * @param market the portfolio to be saved
   * @throws IOException if an I/O error occurs
   */
  public void save(Market market) throws IOException {
    List<Market> markets = findAll();
    if (markets.size() == 0) {
      markets = new ArrayList<>();
      markets.add(market);
      logger.info("new market created in vide ficher: " + market.getMarketName());
    } else {
      
    boolean flag = false;
    for (int i = 0; i < markets.size(); i++) {
        if (markets.get(i).getMarketId() == market.getMarketId()) {
            markets.set(i, market); 
            flag = true;
            logger.info("market updated: " + market.getMarketName());
            break;
        }
    }
    if (flag == false) {
        markets.add(market);
        logger.info("new market added: " + market.getMarketName());
    }
    }

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
