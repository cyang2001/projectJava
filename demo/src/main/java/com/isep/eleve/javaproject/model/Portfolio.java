package com.isep.eleve.javaproject.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.isep.eleve.javaproject.Tools.*;

public class Portfolio {
  private int portfolioId;

  @JsonProperty("portfolioId")
  public int getPortfolioId() {
    return this.portfolioId;
  }

  private String portfolioName;

  @JsonProperty("portfolioName")
  public String getPortfolioName() {
    return this.portfolioName;
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
  private List<Integer> assetIds;

  @JsonProperty("assetIds")
  public List<Integer> getAssetIds() {
    return this.assetIds;
  }

  @JsonProperty("assetIds")
  public void setAssetIds(List<Integer> assetIds) {
    this.assetIds = assetIds;
  }

  private List<Asset> assets;

  public List<Asset> getAssets() {
    return this.assets;
  }

  private BigDecimal value;

  @JsonProperty("value")
  public BigDecimal getValue() {
    return this.value;
  }

  @JsonProperty("value")
  public void setValue(BigDecimal value) {
    this.value = value;
  }
  /**
   * Constructs a new Portfolio with the specified portfolio name and owner ID.
   * @param portfolioName
   * @param onwerId
   * @param assets
   * @param assetIds
   */
  public Portfolio(String portfolioName, int onwerId, List<Asset> assets, List<Integer> assetIds) {
    this.portfolioName = portfolioName;
    this.ownerId = onwerId;
    this.assets = assets;
    this.portfolioId = UUIDUtils.getUUIDInOrderId();
    this.assetIds = assetIds;
  }

  public void calculatePortfolioValue() {
    BigDecimal totalValue = BigDecimal.ZERO;
    for (Asset asset : this.assets) {
      totalValue = totalValue.add(asset.getValue());
    }
    this.value = totalValue;
  }

  public void addAsset(Asset asset) {
    this.assets.add(asset);
    this.assetIds.add(asset.getAssetId());
    this.calculatePortfolioValue(); // Recalculate the total value after adding an asset
  }

  public void removeAsset(Asset asset) {
    this.assets.remove(asset);
    this.assetIds.remove(Integer.valueOf(asset.getAssetId()));
    this.calculatePortfolioValue(); // Recalculate the total value after removing an asset
  }
}
