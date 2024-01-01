package com.isep.eleve.javaproject.events;

import org.springframework.context.ApplicationEvent;

import com.isep.eleve.javaproject.model.assets.liquide.Cash;

public class CashCreatedEvent extends ApplicationEvent{
  private Cash cash;
  public CashCreatedEvent(Object source, Cash cash) {
    super(source);
    this.cash = cash;
  }
  public Cash getCash(){
    return this.cash;
  }
}
