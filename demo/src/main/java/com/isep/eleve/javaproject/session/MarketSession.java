package com.isep.eleve.javaproject.session;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.isep.eleve.javaproject.model.Market;

@Component
@Scope("singleton")
public class MarketSession {
  private Market market;
  public void setMarket(Market market){
    this.market = market;
  }
  public Market getMarket(){
    return this.market;
  }
}
