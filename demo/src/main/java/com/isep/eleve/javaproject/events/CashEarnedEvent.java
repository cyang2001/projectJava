package com.isep.eleve.javaproject.events;

import java.math.BigDecimal;

import org.springframework.context.ApplicationEvent;

public class CashEarnedEvent extends ApplicationEvent {
  private final BigDecimal price;
  public CashEarnedEvent(Object source, BigDecimal price) {
    super(source);
    this.price = price;
  }
  public BigDecimal getPrice() {
    return price;
  }
}
