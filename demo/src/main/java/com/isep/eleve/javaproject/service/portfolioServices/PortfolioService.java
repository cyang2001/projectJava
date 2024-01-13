package com.isep.eleve.javaproject.service.portfolioServices;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.isep.eleve.javaproject.Tools.Constants;
import com.isep.eleve.javaproject.events.AssetAddedToPortfolioEvent;
import com.isep.eleve.javaproject.events.CashCreatedEvent;
import com.isep.eleve.javaproject.events.PortfolioCreatedEvent;
import com.isep.eleve.javaproject.factory.CashFactory;
import com.isep.eleve.javaproject.listener.DataUpdateListener;
import com.isep.eleve.javaproject.model.Asset;
import com.isep.eleve.javaproject.model.Portfolio;
import com.isep.eleve.javaproject.model.assets.liquide.Cash;
import com.isep.eleve.javaproject.repository.PortfolioRepository;
import com.isep.eleve.javaproject.session.CashSession;
import com.isep.eleve.javaproject.session.PortfolioSession;
import com.isep.eleve.javaproject.session.UserSession;

/**
 * This class represents a service for managing portfolios.
 */
@Service
public class PortfolioService {
    private static final Logger logger = LoggerFactory.getLogger(PortfolioService.class);
    private final PortfolioRepository portfolioRepository;
    private final UserSession userSession;
    private final PortfolioSession portfolioSession;
    private final ApplicationEventPublisher eventApplication;
    private final CashSession cashSession;
    private final AssetsService assetService;
    @Autowired
    public PortfolioService(PortfolioRepository portfolioRepository, ApplicationEventPublisher eventApplication, UserSession userSession, PortfolioSession portfolioSession, CashSession cashSession, AssetsService assetService){
        this.portfolioRepository = portfolioRepository;
        this.eventApplication = eventApplication;
        this.userSession = userSession;
        this.portfolioSession = portfolioSession;
        this.cashSession = cashSession;
        this.assetService = assetService;
    }

    /**
     * Creates a new portfolio with the given name and owner ID.
     *
     * @param portfolioName the name of the portfolio
     * @param ownerId the ID of the owner
     * @return the newly created portfolio
     * @throws IOException if an I/O error occurs
     */
    public Portfolio createPortfolio(String portfolioName) throws IOException {
        int ownerId = userSession.getCurrentUser().getUserId();
        // Create a new Portfolio object
        Portfolio newPortfolio = new Portfolio(portfolioName, ownerId, new ArrayList<>(), new ArrayList<>());
        portfolioSession.setCurrentPortfolio(newPortfolio);
        logger.info("New portfolio created: " + newPortfolio);
        Cash cash = (Cash)assetService.createAsset("CASH", 1000, new BigDecimal(1), Constants.ASSET_TYPE.CASH, new BigDecimal(0), newPortfolio.getPortfolioId(), true);
        logger.info("New cash created: " + cash + "type:" + cash.getAssetType());
        eventApplication.publishEvent(new CashCreatedEvent(this, cash));
        eventApplication.publishEvent(new PortfolioCreatedEvent(this, portfolioSession.getCurrentPortfolio()));
        // Return the newly created portfolio
        return newPortfolio;
    }
    
    /**
     * Updates the given portfolio.
     *
     * @param portfolio the portfolio to be updated
     * @throws IOException if an I/O error occurs while saving the portfolio
     */
    public void updatePortfolio(Portfolio portfolio) throws IOException {
        portfolioRepository.save(portfolio);
    }
    
    /**
     * Deletes the specified portfolio.
     *
     * @param portfolio the portfolio to be deleted
     * @throws IOException if an I/O error occurs
     */
    public void deletePortfolio(Portfolio portfolio) throws IOException {
        portfolioRepository.delete(portfolio);
        // ToDo what if delete this one 
    }

    /**
     * Adds an asset to the portfolio with the specified portfolio ID.
     *
     * @param portfolioId the ID of the portfolio
     * @param asset the asset to be added
     * @throws IOException if an I/O error occurs while saving the portfolio
     */
    public void addAssetToPortfolio(int portfolioId, Asset asset) throws IOException {
        Portfolio portfolio = portfolioRepository.findByPortfolioId(portfolioId);
        portfolio.addAsset(asset);
        portfolioRepository.save(portfolio);
        eventApplication.publishEvent(new AssetAddedToPortfolioEvent(portfolio, asset));
    }

    /**
     * Removes an asset from the portfolio.
     *
     * @param portfolioId the ID of the portfolio
     * @param asset the asset to be removed
     * @throws IOException if an I/O error occurs
     */
    public void removeAssetFromPortfolio(int portfolioId, Asset asset) throws IOException {
        Portfolio portfolio = portfolioRepository.findByPortfolioId(portfolioId);
        portfolio.removeAsset(asset);
        portfolioRepository.save(portfolio);
    }
}
