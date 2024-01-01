package com.isep.eleve.javaproject.events;

import java.math.BigDecimal;

import org.springframework.context.ApplicationEvent;

public class CashSpentEvent extends ApplicationEvent{
  private final BigDecimal price;
  public CashSpentEvent(Object source, BigDecimal price) {
    super(source);
    this.price = price;
  }
  public BigDecimal getPrice() {
    return price;
  }
}
