package com.isep.eleve.javaproject.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.isep.eleve.javaproject.Tools.*;
/**
 * Represents a market transaction.
 */
public class MarketTransaction {
  /**
   * Constructs a new MarketTransaction object with the specified parameters.
   *
   * @param price                 the price of the transaction
   * @param quantity              the quantity of the transaction
   * @param publisherId           the ID of the publisher
   * @param assetName             the name of the asset
   * @param marketTransactionName the name of the market transaction
   * @param portfolioId           the ID of the portfolio
   * @param assetId               the ID of the asset
   */
  public MarketTransaction(BigDecimal price, int quantity, int publisherId, String assetName, String marketTransactionName, int portfolioId, int assetId) {
    this.price = price;
    this.quantity = quantity;
    this.publisherId = publisherId;
    this.isSuccess = false;
    this.assetName = assetName;
    this.marketTransactionName = marketTransactionName;
    this.marketTransactionId = UUIDUtils.getUUIDInOrderId();
    this.portfolioId = portfolioId;
    this.assetId = assetId;
  }
  private int assetId;
  @JsonProperty("assetId")
  public int getAssetId() {
    return assetId;
  }
  @JsonProperty("assetId")
  public void setAssetId(int assetId) {
    this.assetId = assetId;
  }
  private int portfolioId;
  @JsonProperty("portfolioId")
  public int getPortfolioId() {
    return portfolioId;
  }
  @JsonProperty("portfolioId")
  public void setPortfolioId(int portfolioId) {
    this.portfolioId = portfolioId;
  }
  private int marketTransactionId;
  @JsonProperty("marketTransactionId")
  public int getMarketTransactionId() {
    return marketTransactionId;
  }
  @JsonProperty("marketTransactionId")
  public void setMarketTransactionId(int marketTransactionId) {
    this.marketTransactionId = marketTransactionId;
  }
  private BigDecimal price;
  @JsonProperty("price")
  public BigDecimal getPrice() {
    return price;
  }
  @JsonProperty("price")
  public void setPrice(BigDecimal price) {
    this.price = price;
  }
  private int quantity;
  @JsonProperty("quantity")
  public int getQuantity() {
    return quantity;
  }
  @JsonProperty("quantity")
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  private int publisherId;
  @JsonProperty("publisherId")
  public int getPublisherId() {
    return publisherId;
  }
  @JsonProperty("publisherId")
  public void setPublisherId(int publisherId) {
    this.publisherId = publisherId;
  }
  private boolean isSuccess;
  @JsonProperty("isSuccess")
  public boolean getIsSuccess() {
    return isSuccess;
  }
  @JsonProperty("isSuccess")
  public void setIsBuy(boolean isSuccess) {
    this.isSuccess = isSuccess;
  }
  private String assetName;
  @JsonProperty("assetName")
  public String getAssetName() {
    return assetName;
  }
  @JsonProperty("assetName")
  public void setAssetName(String assetName) {
    this.assetName = assetName;
  }
  private String marketTransactionName;
  @JsonProperty("marketTransactionName")
  public String getMarketTransactionName() {
    return marketTransactionName;
  }
  @JsonProperty("marketTransactionName")
  public void setMarketTransactionName(String marketTransactionName) {
    this.marketTransactionName = marketTransactionName;
  }
  
}
