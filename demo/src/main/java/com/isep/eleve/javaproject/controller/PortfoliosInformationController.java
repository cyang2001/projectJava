package com.isep.eleve.javaproject.controller;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.isep.eleve.javaproject.model.chart.PieChartUtils;
import com.isep.eleve.javaproject.App;
import com.isep.eleve.javaproject.model.Asset;
import com.isep.eleve.javaproject.model.Portfolio;
import com.isep.eleve.javaproject.session.UserSession;

@Controller
public class PortfoliosInformationController {
    @FXML
    private TextArea userPortfolio;
    @FXML
    private ChoiceBox<String> portfolioChoiceBox;
    @FXML
    private PieChart pieChart;
    private UserSession userSession;
    @Autowired
    PortfoliosInformationController(UserSession userSession) {
        this.userSession = userSession;
    }
    @FXML 
    public void initialize() {
        List<String> portfolioNames = userSession.getCurrentUser().getPortfolios().stream().map(portfolio -> portfolio.getPortfolioName()).collect(java.util.stream.Collectors.toList());
        portfolioChoiceBox.setItems(FXCollections.observableArrayList(portfolioNames));
    }
    public void handleConfirmationAction(ActionEvent event) {
        StringBuffer sb = new StringBuffer();
        sb.append("User Name: " + userSession.getCurrentUser().getUserName() + "\n");
        List<Portfolio> portfolios = userSession.getCurrentUser().getPortfolios();
        PieChartUtils pieChartUtils = new PieChartUtils(pieChart);
        for (Portfolio portfolio : portfolios) {
            List<Asset> assets = portfolio.getAssets();
            sb.append("Portfolio Name: " + portfolio.getPortfolioName() + "\n");
            sb.append("Portfolio Value: " + portfolio.getValue() + "\n");
            sb.append("Portfolio Assets: " + "\n");
            for (Asset asset : assets) {
                sb.append("Asset Name: " + asset.getAssetName() + "\n");
                sb.append("Asset Quantity: " + asset.getQuantity() + "\n");
                sb.append("Asset Price: " + asset.getPrice() + "\n");
                sb.append("Asset Value: " + asset.getValue() + "\n");
                sb.append("Asset Type: " + asset.getAssetType() + "\n");
                pieChartUtils.addData(asset.getAssetName(), asset.getValue().doubleValue());
            }
        }
        pieChartUtils.showChart();
        userPortfolio.setText(sb.toString());

    }

}