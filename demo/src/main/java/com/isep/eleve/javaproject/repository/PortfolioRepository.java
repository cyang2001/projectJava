package com.isep.eleve.javaproject.repository;

import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.isep.eleve.javaproject.Tools.FileOperation;
import com.isep.eleve.javaproject.model.Portfolio;

@Repository
public class PortfolioRepository {
    private static final String EXTERNAL_FILE_PATH = System.getProperty("user.home") + File.separator + "App" + File.separator + "databasePortfolios.json";

    private FileOperation fileOperation;
    @Autowired
    public PortfolioRepository(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }
    /**
     * Find all portfolios
     * @return List<Portfolio>
     * @throws IOException
     */
    public List<Portfolio> findAll() throws IOException {
        return fileOperation.readListFromFile(EXTERNAL_FILE_PATH, Portfolio.class);
    }
    /**
     * Save portfolio
     * @param portfolio
     * @throws IOException
     */
    public void save(Portfolio portfolio) throws IOException {
        List<Portfolio> portfolios = findAll();
        portfolios.add(portfolio);
        fileOperation.writeListToFile(EXTERNAL_FILE_PATH, portfolios);
    }
    /**
     * Find portfolio by portfolio id
     * @param portfolioId
     * @return Portfolio
     * @throws IOException
     */
    public Portfolio findByPortfolioId(int portfolioId) throws IOException {
        List<Portfolio> portfolios = findAll();
        return portfolios.stream().filter(p -> p.getPortfolioId() == portfolioId).findFirst().orElse(null);
    }
    /**
     * Find portfolio by owner id
     * @param ownerId
     * @return List<Portfolio>
     * @throws IOException
     */
    public List<Portfolio> findByOwnerId(int ownerId) throws IOException {
        List<Portfolio> portfolios = findAll();
        return portfolios.stream().filter(p -> p.getOwnerId() == ownerId).collect(Collectors.toList());
    }

}
