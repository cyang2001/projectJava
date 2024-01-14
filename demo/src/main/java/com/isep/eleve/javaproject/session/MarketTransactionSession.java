package com.isep.eleve.javaproject.session;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.isep.eleve.javaproject.model.MarketTransaction;

@Component
@Scope("singleton")
public class MarketTransactionSession {
  private MarketTransaction marketTransaction;
  public void setMarketTransaction(MarketTransaction marketTransaction){
    this.marketTransaction = marketTransaction;
  }
  public MarketTransaction getMarketTransaction(){
    return this.marketTransaction;
  }
}
