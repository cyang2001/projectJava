package com.isep.eleve.javaproject.service.portfolioServices;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.eleve.javaproject.model.Asset;
import com.isep.eleve.javaproject.model.Portfolio;
import com.isep.eleve.javaproject.repository.PortfolioRepository;

@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    @Autowired
    public PortfolioService(PortfolioRepository portfolioRepository){
        this.portfolioRepository = portfolioRepository;
    }

    public Portfolio createPortfolio(String portfolioName, int ownerId) throws IOException {
      
        // Create a new Portfolio object
        Portfolio newPortfolio = new Portfolio(portfolioName, ownerId, new ArrayList<>(), new ArrayList<>());

        // Persist the new portfolio
        portfolioRepository.save(newPortfolio);

        // Return the newly created portfolio
        return newPortfolio;
    }
    
    public void updatePortfolio(Portfolio portfolio) throws IOException {
        portfolioRepository.save(portfolio);
    }
    
    public void deletePortfolio(Portfolio portfolio) throws IOException {
        portfolioRepository.delete(portfolio);
    }

    public void addAssetToPortfolio(int portfolioId, Asset asset) throws IOException {
        Portfolio portfolio = portfolioRepository.findByPortfolioId(portfolioId);
        portfolio.addAsset(asset);
        portfolioRepository.save(portfolio);
    }

    public void removeAssetFromPortfolio(int portfolioId, Asset asset) throws IOException {
        Portfolio portfolio = portfolioRepository.findByPortfolioId(portfolioId);
        portfolio.removeAsset(asset);
        portfolioRepository.save(portfolio);
    }
}
