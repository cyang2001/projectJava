package com.isep.eleve.javaproject.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Market {
  private int marketId;
  @JsonProperty("marketId")
  public int getMarketId() {
    return marketId;
  }
  @JsonProperty("marketId")
  public void setMarketId(int marketId) {
    this.marketId = marketId;
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
  private String marketName;
  @JsonProperty("marketName")
  public String getMarketName() {
    return marketName;
  }
  @JsonProperty("marketName")
  public void setMarketName(String marketName) {
    this.marketName = marketName;
  }
  private List<Integer> marketTransactionIds;
  @JsonProperty("marketTransactionIds")
  public List<Integer> getMarketTransactionIds() {
    return marketTransactionIds;
  }
  @JsonProperty("marketTransactionIds")
  public void setMarketTransactionIds(List<Integer> marketTransactionIds) {
    this.marketTransactionIds = marketTransactionIds;
  }

}
