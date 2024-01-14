package com.isep.eleve.javaproject.events;

import org.springframework.context.ApplicationEvent;

import com.isep.eleve.javaproject.model.MarketTransaction;

public class MarketTransactionCreated extends ApplicationEvent{
  private MarketTransaction marketTransaction;
  public MarketTransactionCreated(Object source, MarketTransaction marketTransaction) {
    super(source);
    this.marketTransaction = marketTransaction;
  }
  public MarketTransaction getMarketTransaction() {
    return marketTransaction;
  }
}
