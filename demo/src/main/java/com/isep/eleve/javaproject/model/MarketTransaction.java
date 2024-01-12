package com.isep.eleve.javaproject.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarketTransaction {
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
}
