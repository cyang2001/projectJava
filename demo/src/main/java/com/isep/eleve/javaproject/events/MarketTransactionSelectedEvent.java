package com.isep.eleve.javaproject.events;

import org.springframework.context.ApplicationEvent;

import com.isep.eleve.javaproject.model.MarketTransaction;

public class MarketTransactionSelectedEvent extends ApplicationEvent{
  private MarketTransaction marketTransaction;
  public MarketTransactionSelectedEvent(Object source, MarketTransaction marketTransaction) {
    super(source);
    this.marketTransaction = marketTransaction;
  }
  public MarketTransaction getMarketTransaction() {
    return marketTransaction;
  }
}
