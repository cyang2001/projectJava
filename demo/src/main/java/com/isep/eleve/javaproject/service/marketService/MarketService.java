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

/**
 * Service class for handling market operations.
 * This class provides functionalities related to market transactions and their management.
 */
@Service
public class MarketService {
    private final MarketRepository marketRepository;
    private final MarketSession marketSession;
    private Logger logger = LoggerFactory.getLogger(MarketService.class);

    /**
     * Constructs a MarketService with specified market repository and session.
     *
     * @param marketRepository the market repository used for data persistence
     * @param marketSession the market session containing current market state
     */
    @Autowired
    public MarketService(MarketRepository marketRepository, MarketSession marketSession) {
        this.marketRepository = marketRepository;
        this.marketSession = marketSession;
    }

    /**
     * Records the current state of the market.
     * This method saves the current market state to the repository.
     *
     * @throws IOException if there is an issue with saving the market state
     */
    public void recordMarket() throws IOException {
        marketRepository.save(marketSession.getMarket());
    }

    /**
     * Adds a new market transaction and updates the market state.
     * This method logs the addition of a new transaction and adds its ID to the current market session.
     *
     * @param MarketTransaction the market transaction to be added
     * @throws IOException if there is an issue with recording the updated market state
     */
    public void addNewMarketTransaction(MarketTransaction MarketTransaction) throws IOException {
        logger.info("Adding new MarketTransaction {} to Market {} ", MarketTransaction.getMarketTransactionId(), marketSession.getMarket().getMarketName());
        marketSession.getMarket().getMarketTransactionIds().add(MarketTransaction.getMarketTransactionId());
        this.recordMarket();
    }
}
