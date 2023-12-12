package com.isep.eleve.javaproject.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.isep.eleve.javaproject.model.Portfolio;

@Repository
public class PortfolioRepository {
    private static final String EXTERNAL_FILE_PATH = System.getProperty("user.home") + File.separator + "App" + File.separator + "databasePortfolios.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Find all portfolios
     * @return List<Portfolio>
     * @throws IOException
     */
    public List<Portfolio> findAll() throws IOException {
        File file = new File(EXTERNAL_FILE_PATH);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                return objectMapper.readValue(fis, TypeFactory.defaultInstance().constructCollectionType(List.class, Portfolio.class));
            }
        }
        return new ArrayList<>();
    }
    /**
     * Save portfolio
     * @param portfolio
     * @throws IOException
     */
    public void save(Portfolio portfolio) throws IOException {
        List<Portfolio> portfolios = findAll();
        portfolios.add(portfolio);
        writePortfoliosToFile(portfolios);
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
    private void writePortfoliosToFile(List<Portfolio> portfolios) throws IOException {
        File file = new File(EXTERNAL_FILE_PATH);
        File parentDir = file.getParentFile();
        if (!parentDir.exists() && !parentDir.mkdirs()) {
            throw new IOException("Failed to create directory: " + parentDir);
        }
        try (FileOutputStream fos = new FileOutputStream(file)) {
            objectMapper.writeValue(fos, portfolios);
        }
    }
}
