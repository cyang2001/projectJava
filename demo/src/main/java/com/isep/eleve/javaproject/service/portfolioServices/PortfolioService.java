package com.isep.eleve.javaproject.service.portfolioServices;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
