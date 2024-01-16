package com.isep.eleve.javaproject.events;

import java.math.BigDecimal;

import org.springframework.context.ApplicationEvent;

import com.isep.eleve.javaproject.Tools.Constants;
import com.isep.eleve.javaproject.model.Asset;

public class AssetPriceChangedEvent extends ApplicationEvent{
  private BigDecimal price;
  private Constants.CHANGE_TYPE changeType;
  private Asset asset;
  private boolean isMarket;
  public AssetPriceChangedEvent(Object source, Asset asset, BigDecimal price, Constants.CHANGE_TYPE changeType, boolean isMarket) {
    super(source);
    this.price = price;
    this.changeType = changeType;
    this.asset = asset;
    this.isMarket = isMarket;
  }
  public BigDecimal getPrice() {
    return price;
  }
  public Constants.CHANGE_TYPE getChangeType() {
    return changeType;
  }
  public Asset getAsset() {
    return asset;
  }
  public boolean isMarket() {
    return isMarket;
  }
}
