package com.isep.eleve.javaproject.events;

import org.springframework.context.ApplicationEvent;

import com.isep.eleve.javaproject.Tools.Constants;
import com.isep.eleve.javaproject.model.Asset;

public class AssetQuantityChangedEvent extends ApplicationEvent{
  private int quantity;
  private Constants.CHANGE_TYPE changeType;
  private Asset asset;
  public AssetQuantityChangedEvent(Object source, Asset asset, int quantity, Constants.CHANGE_TYPE changeType) {
    super(source);
    this.quantity = quantity;
    this.changeType = changeType;
    this.asset = asset;
  }
  public int getQuantity() {
    return quantity;
  }
  public Constants.CHANGE_TYPE getChangeType() {
    return changeType;
  }
  public Asset getAsset() {
    return asset;
  }
}
