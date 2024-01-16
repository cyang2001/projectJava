package com.isep.eleve.javaproject.model;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.isep.eleve.javaproject.Tools.*;
public class Market {
  private int marketId;
  private String marketName;
  private List<Integer> marketTransactionIds;
  public Market(String marketName) {
    this.marketId = UUIDUtils.getUUIDInOrderId();
    this.marketName = marketName;
    this.marketTransactionIds = new ArrayList<>();
  }
  public Market() {
  }
  @JsonProperty("marketId")
  public int getMarketId() {
    return marketId;
  }
  @JsonProperty("marketId")
  public void setMarketId(int marketId) {
    this.marketId = marketId;
  }
  @JsonProperty("marketName")
  public String getMarketName() {
    return marketName;
  }
  @JsonProperty("marketName")
  public void setMarketName(String marketName) {
    this.marketName = marketName;
  }
  @JsonProperty("marketTransactionIds")
  public List<Integer> getMarketTransactionIds() {
    return marketTransactionIds;
  }
  @JsonProperty("marketTransactionIds")
  public void setMarketTransactionIds(List<Integer> marketTransactionIds) {
    this.marketTransactionIds = marketTransactionIds;
  }
  
}
