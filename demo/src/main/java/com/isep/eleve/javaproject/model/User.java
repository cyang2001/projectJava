package com.isep.eleve.javaproject.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.isep.eleve.javaproject.Tools.*;


/**
 * UserModel
 * @author: Chen YANG
 * @version: 1.0
 */
public class User {
  // user ID
  private Integer userId;
  @JsonProperty("userId")
  public Integer getUserId() {
    return this.userId;
  }
  @JsonProperty("userId")
  public void setUserId(Integer userId) {
    this.userId = userId;
  }
  // user name
  private String userName;
  @JsonProperty("userName")
  public String getUserName() {
    return this.userName;
  }
  @JsonProperty("userName")
  public void setUserName(String userName) {
    this.userName = userName;
  }
  // user password hashed
  private String passwordHash;
  @JsonProperty("passwordHash")
  public String getPasswordHash() {
    return this.passwordHash;
  }
  @JsonProperty("passwordHash")
  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }
  private List<Integer> portfoliosId;
  @JsonProperty("portfoliosId")
  public List<Integer> getPortfoliosId(){
    return this.portfoliosId;
  }
  @JsonProperty("portfoliosId")
  public void setPortfoliosId(List<Integer> portfoliosId){
    this.portfoliosId = portfoliosId;
  }
  // all portfolios of the user
  private List<Portfolio> portfolios;
  @JsonProperty("portfolios")
  public List<Portfolio> getPortfolios() {
    return this.portfolios;
  }
  @JsonProperty("portfolios")
  public void setPortfolios(List<Portfolio> portfolios) {
    this.portfolios = portfolios;
  }
  // constructor
  public User(String userName, String passwordHash) {
      this.userName = userName;
      this.passwordHash = passwordHash;
      this.portfolios = null;
      this.userId = UUIDUtils.getUUIDInOrderId();
      this.portfoliosId = new ArrayList<>();
      this.portfolios = new ArrayList<>();
  }
 
  // default constructor for jackson
  public User() {
      this.userName = null;
      this.passwordHash = null;
      this.portfolios = null;
      this.userId = UUIDUtils.getUUIDInOrderId();
  }
  /**
   * addPortfolio
   * @param portfolio
   * @return boolean
   * @Description: add a portfolio to the user
   */
  public boolean addPortfolio(Portfolio portfolio) {
    // add portfolio logic
    if (!this.getPortfolios().contains(portfolio)) {
      this.getPortfolios().add(portfolio);
      this.getPortfoliosId().add(portfolio.getPortfolioId());
      return true;
    }
    return false;
  }
  public void updatePortfolio(Portfolio portfolio) {
    for (int i = 0; i < this.getPortfolios().size(); i++) {
      if (this.getPortfolios().get(i).getPortfolioId() == portfolio.getPortfolioId()) {
        this.getPortfolios().set(i, portfolio);
      }
    }
  }

}
