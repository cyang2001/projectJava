package com.isep.eleve.javaproject.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.isep.eleve.javaproject.Tools.UUIDUtils;
import com.isep.eleve.javaproject.Tools.Constants.ASSET_TYPE;


public abstract class Asset {
  private ASSET_TYPE assetType;
  private int assetId;
  private String assetName;
  private int portfolioId;
  private BigDecimal value;
  private int quantity;
  private BigDecimal price;
  private int ownerId;
  public Asset(String assetName, int portfolioId, int quantity, BigDecimal price, int ownerId) {
    this.portfolioId = portfolioId;
    this.quantity = quantity;
    this.price = price;
    this.assetId = UUIDUtils.getUUIDInOrderId();
    this.calculateValue(); // Initialize the value of the asset
    this.ownerId = ownerId;
    this.assetName = assetName;
  }

@JsonProperty("assetId")
  public int getAssetId() {
    return this.assetId;
  }
@JsonProperty("assetName")
  public String getAssetName() {
    return this.assetName;
  }
@JsonProperty("portfolioId")
  public int getPortfolioId() {
    return this.portfolioId;
  }
@JsonProperty("value")
  public BigDecimal getValue() {
    return this.value;
  }
@JsonProperty("quantity")
  public int getQuantity() {
    return this.quantity;
  }
@JsonProperty("price")
  public BigDecimal getPrice() {
    return this.price;
  }

@JsonProperty("quantity")
  public void setQuantity(int quantity) {
    this.quantity = quantity;
    this.calculateValue(); // Recalculate the value after quantity change
  }
@JsonProperty("price")
  public void setPrice(BigDecimal price) {
    this.price = price;
    this.calculateValue(); // Recalculate the value after price change
  }
@JsonProperty("value")
  protected void setValue(BigDecimal value) {
    this.value = value;
  }
@JsonProperty("ownerId")
  public int getOwnerId() {
    return this.ownerId;
  }
@JsonProperty("ownerId")
  public void setOwnerId(int ownerId) {
    this.ownerId = ownerId;
  }
@JsonProperty("assetType")
  public ASSET_TYPE getAssetType() {
    return this.assetType;
  }
@JsonProperty("assetType")
  public void setAssetType(ASSET_TYPE assetType) {
    this.assetType = assetType;
  }
  // Abstract method to be implemented by subclasses to calculate the asset's value
  public abstract void calculateValue();
}
