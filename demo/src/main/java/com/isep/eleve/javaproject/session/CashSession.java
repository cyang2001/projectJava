package com.isep.eleve.javaproject.session;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.isep.eleve.javaproject.model.assets.liquide.Cash;
@Component
@Scope("singleton")
public class CashSession {
  private Cash allCash;
  public void setCash(Cash cash){
    this.allCash = cash;
  }
  public Cash getCash(){
    return this.allCash;
  }
}
