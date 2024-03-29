package com.isep.eleve.javaproject.controller;

import java.io.IOException;
import java.util.List;

import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.isep.eleve.javaproject.model.Portfolio;
import com.isep.eleve.javaproject.service.portfolioServices.PortfolioService;
import com.isep.eleve.javaproject.session.CashSession;
import com.isep.eleve.javaproject.session.PortfolioSession;
import com.isep.eleve.javaproject.session.UserSession;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
@Controller
public class NewPortfolioPageController {
    public final PortfolioSession portfolioSession;
    public final PortfolioService portfolioService;
    public final UserSession userSession;
    public final CashSession cashSession;
    @Autowired
    public NewPortfolioPageController(PortfolioSession portfolioSession, PortfolioService portfolioService, UserSession userSession, CashSession cashSession) {
        this.portfolioSession = portfolioSession;
        this.portfolioService = portfolioService;
        this.userSession = userSession;
        this.cashSession = cashSession;
    }

    @FXML
    public TextField newPortfolioName;

    public void handleConfirmationAction(ActionEvent event) throws IOException {
      String portfolioName = this.newPortfolioName.getText();
      List<Portfolio> portfolios = userSession.getCurrentUser().getPortfolios();
      for (Portfolio portfolio : portfolios) {
        if (portfolio.getPortfolioName().equals(portfolioName)) {
          System.err.println("Portfolio name already exists");
          showAlert("Information", "Portfolio's name already exist");
          return;
        }
      }
      portfolioService.createPortfolio(portfolioName);
    }
    protected void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
