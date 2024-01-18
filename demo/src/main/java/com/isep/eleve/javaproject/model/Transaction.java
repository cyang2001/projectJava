package com.isep.eleve.javaproject.model;

import java.math.BigDecimal;
import com.isep.eleve.javaproject.Tools.*;
import com.isep.eleve.javaproject.Tools.Constants.TRANSACTION_TYPE;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {
  public Transaction( int portfolioId, int assetId, int quantity, BigDecimal price, TRANSACTION_TYPE transactionType, String timeStamp, int ownerId) {
    this.setTransactioinId();
    this.portfolioId = portfolioId;
    this.assetId = assetId;
    this.quantity = quantity;
    this.price = price;
    this.transactionType = transactionType;
    this.timeStamp = timeStamp;
    this.ownerId = ownerId;
  }
  private int transactionId;
  @JsonProperty("transactionId")
  public int getTransactionId() {
    return this.transactionId;
  }
  @JsonProperty("transactionId") 
  public void setTransactioinId(){
    this.transactionId = UUIDUtils.getUUIDInOrderId();
  }

  private int portfolioId;
  @JsonProperty("portfolioId")
  public int getPortfolioId() {
    return this.portfolioId;
  }
  @JsonProperty("portfolioId")
  public void setPortfolioId(int portfoliosId) {
    this.portfolioId = portfoliosId;
  }
  private int ownerId;
  @JsonProperty("ownerId")
  public int getOwnerId() {
    return this.ownerId;
  }
  @JsonProperty("ownerId")
  public void setOwnerId(int ownerId) {
    this.ownerId = ownerId;
  }
  private int assetId;
  @JsonProperty("assetId")
  public int getAssetId() {
    return this.assetId;
  }
  @JsonProperty("assetId")
  public void setAssetId(int assetId) {
    this.assetId = assetId;
  }
  private int quantity;
  @JsonProperty("quantity")
  public int getQuantity() {
    return this.quantity;
  }
  @JsonProperty("quantity")
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  private BigDecimal price;
  @JsonProperty("price")
  public BigDecimal getPrice() {
    return this.price;
  }
  @JsonProperty("price")
  public void setPrice(BigDecimal price) {
    this.price = price;
  }
  private TRANSACTION_TYPE transactionType;
  @JsonProperty("transactionType")
  public TRANSACTION_TYPE getTransactionType() {
    return this.transactionType;
  }
  @JsonProperty("transactionType")
  public void setTransactionType(TRANSACTION_TYPE transactionType) {
    this.transactionType = transactionType;
  }
  private String timeStamp;
  @JsonProperty("timeStamp")
  public String getTimeStamp() {
    return this.timeStamp;
  }
  @JsonProperty("timeStamp")
  public void setTimeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
  }
}
