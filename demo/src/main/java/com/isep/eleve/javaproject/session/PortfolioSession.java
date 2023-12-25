package com.isep.eleve.javaproject.session;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.isep.eleve.javaproject.model.Portfolio;

@Component
@Scope("singleton")
public class PortfolioSession {
  private Portfolio currentPortfolio;
  public void setCurrentPortfolio(Portfolio currentPortfolio){
    this.currentPortfolio = currentPortfolio;
  }
  public Portfolio getCurrentPortfolio(){
    return this.currentPortfolio;
  }
}
