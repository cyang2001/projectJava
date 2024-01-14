package com.isep.eleve.javaproject.controller;

import com.isep.eleve.javaproject.App;
import com.isep.eleve.javaproject.repository.AssetsRepository;
import com.isep.eleve.javaproject.service.portfolioServices.AssetsService;
import com.isep.eleve.javaproject.session.CashSession;
import com.isep.eleve.javaproject.session.PortfolioSession;
import com.isep.eleve.javaproject.session.UserSession;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
/**
 * chen 
 */
@Controller
public class SellCryptoController {
    @FXML
    private ChoiceBox<String> cryptoToSellChoiceBox;
    @FXML
    private TextField price;
    @FXML
    private TextField quantity;
    @FXML
    private TextField interestRate;
    @FXML
    private ChoiceBox<String> portfolioToSellChoiceBox;
    private final AssetsService assetsService;
    private final UserSession userSession;
    private final PortfolioSession portfolioSession;
    private final CashSession cashSession;
    private final AssetsRepository assetsRepository;
    private final ApplicationEventPublisher eventPublisher;
    private static final Logger logger = LoggerFactory.getLogger(AddAssetController.class);
    @Autowired
    SellCryptoController(AssetsService assetsService, UserSession userSession, PortfolioSession portfolioSession, CashSession cashSession, AssetsRepository assetsRepository, ApplicationEventPublisher eventPublisher) {
        this.assetsService = assetsService;
        this.userSession = userSession;
        this.portfolioSession = portfolioSession;
        this.cashSession = cashSession;
        this.assetsRepository = assetsRepository;
        this.eventPublisher = eventPublisher;
    }
    @FXML 
    public void initialize() {
        List<String> portfolioNames = userSession.getCurrentUser().getPortfolios().stream().map(portfolio -> portfolio.getPortfolioName()).collect(java.util.stream.Collectors.toList());
        //System.err.println(portfolioNames);
        cryptoToSellChoiceBox.setItems(FXCollections.observableArrayList("BTC"));
        portfolioToSellChoiceBox.setItems(FXCollections.observableArrayList(portfolioNames));
    }
    public void handleUserInformationAction(ActionEvent event) {
    }

    public void handleConfirmationAction(ActionEvent event) {
    }

    public void handleLogOutAction(ActionEvent event) {
    }
}
