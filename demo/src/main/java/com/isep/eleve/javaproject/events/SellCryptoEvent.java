package com.isep.eleve.javaproject.events;

import org.springframework.context.ApplicationEvent;

import com.isep.eleve.javaproject.model.MarketTransaction;
import com.isep.eleve.javaproject.model.Portfolio;

public class SellCryptoEvent extends ApplicationEvent{
  private MarketTransaction marketTransaction;
  public SellCryptoEvent(Object source, MarketTransaction marketTransaction) {
        super(source);
        this.marketTransaction = marketTransaction;
    }
  public MarketTransaction getMarketTransaction() {
    return marketTransaction;
  }
}
