package com.isep.eleve.javaproject.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.isep.eleve.javaproject.service.portfolioServices.PortfolioService;
import com.isep.eleve.javaproject.session.PortfolioSession;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
@Controller
public class NewPortfolioPageController {
    public final PortfolioSession portfolioSession;
    public final PortfolioService portfolioService;

    @Autowired
    public NewPortfolioPageController(PortfolioSession portfolioSession, PortfolioService portfolioService) {
        this.portfolioSession = portfolioSession;
        this.portfolioService = portfolioService;
    }

    @FXML
    public TextField newPortfolioName;
    public void handleUserInformationAction(ActionEvent event) {
    }

    public void handleConfirmationAction(ActionEvent event) throws IOException {
      String portfolioName = this.newPortfolioName.getText();
      portfolioService.createPortfolio(portfolioName);
    }

    public void handleLogOutAction(ActionEvent event) {
    }
}
