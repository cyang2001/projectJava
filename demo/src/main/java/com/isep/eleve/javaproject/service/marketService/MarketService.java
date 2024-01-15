package com.isep.eleve.javaproject.service.marketService;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.eleve.javaproject.model.MarketTransaction;
import com.isep.eleve.javaproject.repository.MarketRepository;
import com.isep.eleve.javaproject.session.MarketSession;
import org.slf4j.*;
@Service
public class MarketService {
  private final MarketRepository marketRepository;
  private final MarketSession marketSession;
  private Logger logger = LoggerFactory.getLogger(MarketService.class);
  @Autowired
  public MarketService(MarketRepository marketRepository, MarketSession marketSession) {
    this.marketRepository = marketRepository;
    this.marketSession = marketSession;
  }

  public void recordMarket() throws IOException {
    marketRepository.save(marketSession.getMarket());
  }
  public void addNewMarketTransaction(MarketTransaction MarketTransaction) throws IOException {
    logger.info("Adding new MarketTransaction {} to Market {} " , MarketTransaction.getMarketTransactionId(), marketSession.getMarket().getMarketName());
    marketSession.getMarket().getMarketTransactionIds().add(MarketTransaction.getMarketTransactionId());
    this.recordMarket();
  }
}
