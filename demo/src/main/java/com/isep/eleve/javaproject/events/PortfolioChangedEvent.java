package com.isep.eleve.javaproject.events;

import org.springframework.context.ApplicationEvent;

import com.isep.eleve.javaproject.model.Portfolio;
public class PortfolioChangedEvent extends ApplicationEvent{
  private Portfolio portfolio;

    public PortfolioChangedEvent(Object source, Portfolio portfolio) {
        super(source);
        this.portfolio = portfolio;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }
  
}