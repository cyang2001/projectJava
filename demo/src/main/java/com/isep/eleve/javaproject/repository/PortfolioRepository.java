package com.isep.eleve.javaproject.repository;

import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.isep.eleve.javaproject.Tools.FileOperation;
import com.isep.eleve.javaproject.model.Portfolio;
/**
 * Portfolio repository
 * @version V1.3
 * @author Chen YANG
 */
/**
 * This class represents a repository for managing portfolios.
 */
@Repository
public class PortfolioRepository {
  private static final String EXTERNAL_FILE_PATH = System.getProperty("user.home") + File.separator + "App" + File.separator + "databasePortfolios.json";

  private FileOperation fileOperation;
  
  /**
   * Constructs a new PortfolioRepository with the specified FileOperation.
   * 
   * @param fileOperation the FileOperation used for file operations
   */
  @Autowired
  public PortfolioRepository(FileOperation fileOperation) {
    this.fileOperation = fileOperation;
  }
  
  /**
   * Retrieves all portfolios from the file.
   * 
   * @return a list of all portfolios
   * @throws IOException if an I/O error occurs
   */
  public List<Portfolio> findAll() throws IOException {
    return fileOperation.readListFromFile(EXTERNAL_FILE_PATH, Portfolio.class);
  }

  /**
   * Saves a portfolio to the file.
   * 
   * @param portfolio the portfolio to be saved
   * @throws IOException if an I/O error occurs
   */
  public void save(Portfolio portfolio) throws IOException {
    List<Portfolio> portfolios = findAll();
    portfolios.add(portfolio);
    fileOperation.writeListToFile(EXTERNAL_FILE_PATH, portfolios);
  }

  /**
   * Finds a portfolio by its portfolio ID.
   * 
   * @param portfolioId the ID of the portfolio to find
   * @return the found portfolio, or null if not found
   * @throws IOException if an I/O error occurs
   */
  public Portfolio findByPortfolioId(int portfolioId) throws IOException {
    List<Portfolio> portfolios = findAll();
    return portfolios.stream().filter(p -> p.getPortfolioId() == portfolioId).findFirst().orElse(null);
  }

  /**
   * Finds all portfolios owned by the specified owner ID.
   * 
   * @param ownerId the ID of the owner
   * @return a list of portfolios owned by the specified owner
   * @throws IOException if an I/O error occurs
   */
  public List<Portfolio> findByOwnerId(int ownerId) throws IOException {
    List<Portfolio> portfolios = findAll();
    return portfolios.stream().filter(p -> p.getOwnerId() == ownerId).collect(Collectors.toList());
  }

  public void delete(Portfolio portfolio) throws IOException {
    List<Portfolio> portfolios = findAll();
    portfolios.remove(portfolio);
    fileOperation.writeListToFile(EXTERNAL_FILE_PATH, portfolios);
  }
}
