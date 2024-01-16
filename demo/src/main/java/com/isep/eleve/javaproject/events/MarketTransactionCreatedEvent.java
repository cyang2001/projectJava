package com.isep.eleve.javaproject.events;

import org.springframework.context.ApplicationEvent;

import com.isep.eleve.javaproject.model.MarketTransaction;

public class MarketTransactionCreatedEvent extends ApplicationEvent{
  private MarketTransaction marketTransaction;
  public MarketTransactionCreatedEvent(Object source, MarketTransaction marketTransaction) {
    super(source);
    this.marketTransaction = marketTransaction;
  }
  public MarketTransaction getMarketTransaction() {
    return marketTransaction;
  }
}
