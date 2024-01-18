package com.isep.eleve.javaproject.events;

import org.springframework.context.ApplicationEvent;

public class SellAssetEvent extends ApplicationEvent{
  private boolean isSuccessful;
  private int transactionId;
  public SellAssetEvent(Object source, boolean isSuccessful, int transactionId) {
    super(source);
    this.isSuccessful = isSuccessful;
    this.transactionId = transactionId;
  }
  public boolean getIsSuccessful() {
    return isSuccessful;
  }
  public int getTransactionId() {
    return transactionId;
  }

}
