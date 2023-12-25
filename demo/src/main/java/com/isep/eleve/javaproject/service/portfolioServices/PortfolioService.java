package com.isep.eleve.javaproject.service.portfolioServices;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.isep.eleve.javaproject.model.Asset;
import com.isep.eleve.javaproject.model.Portfolio;
import com.isep.eleve.javaproject.repository.PortfolioRepository;

/**
 * This class represents a service for managing portfolios.
 */
@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    
    private final ApplicationEventPublisher eventApplication;
    @Autowired
    public PortfolioService(PortfolioRepository portfolioRepository, ApplicationEventPublisher eventApplication){
        this.portfolioRepository = portfolioRepository;
        this.eventApplication = eventApplication;
    }

    /**
     * Creates a new portfolio with the given name and owner ID.
     *
     * @param portfolioName the name of the portfolio
     * @param ownerId the ID of the owner
     * @return the newly created portfolio
     * @throws IOException if an I/O error occurs
     */
    public Portfolio createPortfolio(String portfolioName, int ownerId) throws IOException {
      
        // Create a new Portfolio object
        Portfolio newPortfolio = new Portfolio(portfolioName, ownerId, new ArrayList<>(), new ArrayList<>());

        // Persist the new portfolio
        portfolioRepository.save(newPortfolio);
        eventApplication.publishEvent(newPortfolio);
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
