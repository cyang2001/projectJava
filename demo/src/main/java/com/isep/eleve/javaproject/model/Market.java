package com.isep.eleve.javaproject.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.isep.eleve.javaproject.Tools.*;
public class Market {
  public Market(String marketName) {
    this.marketId = UUIDUtils.getUUIDInOrderId();
    this.marketName = marketName;
  }
  private int marketId;
  @JsonProperty("marketId")
  public int getMarketId() {
    return marketId;
  }
  @JsonProperty("marketId")
  public void setMarketId(int marketId) {
    this.marketId = marketId;
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
