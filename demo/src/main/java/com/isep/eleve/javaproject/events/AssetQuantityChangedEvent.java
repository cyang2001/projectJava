package com.isep.eleve.javaproject.events;

import org.springframework.context.ApplicationEvent;

import com.isep.eleve.javaproject.Tools.Constants;

public class AssetQuantityChangedEvent extends ApplicationEvent{
  private int quantity;
  private Constants.CHANGE_TYPE changeType;
  public AssetQuantityChangedEvent(Object source, int quantity, Constants.CHANGE_TYPE changeType) {
    super(source);
    this.quantity = quantity;
    this.changeType = changeType;
  }
  public int getQuantity() {
    return quantity;
  }
  public Constants.CHANGE_TYPE getChangeType() {
    return changeType;
  }
  
}
