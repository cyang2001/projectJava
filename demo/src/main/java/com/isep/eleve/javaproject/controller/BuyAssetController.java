package com.isep.eleve.javaproject.controller;

import com.isep.eleve.javaproject.App;
import com.isep.eleve.javaproject.Tools.Constants;
import com.isep.eleve.javaproject.events.AssetChangedEvent;
import com.isep.eleve.javaproject.events.AssetCreatedEvent;
import com.isep.eleve.javaproject.events.PortfolioChangedEvent;
import com.isep.eleve.javaproject.model.Asset;
import com.isep.eleve.javaproject.model.Portfolio;
import com.isep.eleve.javaproject.model.Transaction;
import com.isep.eleve.javaproject.repository.AssetsRepository;
import com.isep.eleve.javaproject.service.TransactionService;
import com.isep.eleve.javaproject.service.portfolioServices.AssetsService;
import com.isep.eleve.javaproject.session.CashSession;
import com.isep.eleve.javaproject.session.PortfolioSession;
import com.isep.eleve.javaproject.session.UserSession;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * remi
 */
@Controller
public class BuyAssetController {

    private final AssetsService assetsService;
    private final PortfolioSession portfolioSession;
    private final CashSession cashSession;
    private final AssetsRepository assetsRepository ;
    private final TransactionService transactionService ;
    private final ApplicationEventPublisher eventPublisher;
    @FXML
    private ChoiceBox<String> portfolioOfAssetToBuyChoiceBox;
    @FXML
    private ChoiceBox<String> assetToBuyChoiceBox;
    @FXML
    private TextField assetQuantity;
    @FXML
    private TextField assetPrice;
    @FXML
    private TextField interestRate;
    private final UserSession userSession;

    @Autowired
    BuyAssetController(TransactionService transactionService,AssetsService assetsService, UserSession userSession, PortfolioSession portfolioSession, CashSession cashSession, AssetsRepository assetsRepository, ApplicationEventPublisher eventPublisher) {
        this.assetsService = assetsService;
        this.userSession = userSession;
        this.portfolioSession = portfolioSession;
        this.cashSession = cashSession;
        this.assetsRepository = assetsRepository;
        this.eventPublisher = eventPublisher;
        this.transactionService = transactionService;
    }
    @FXML
    public void initialize() {
        List<String> portfolioNames = userSession.getCurrentUser().getPortfolios().stream().map(portfolio -> portfolio.getPortfolioName()).collect(java.util.stream.Collectors.toList());
        //System.err.println(portfolioNames);
        assetToBuyChoiceBox.setItems(FXCollections.observableArrayList("BTC", "AAPL", "FIXED_DEPOSIT"));
        portfolioOfAssetToBuyChoiceBox.setItems(FXCollections.observableArrayList(portfolioNames));
    }

    public void handleConfirmationAction(ActionEvent event) throws IOException {
        List<Portfolio> portfolios = userSession.getCurrentUser().getPortfolios();
        Portfolio portfolio = portfolios.stream().filter(p -> p.getPortfolioName().equals(portfolioOfAssetToBuyChoiceBox.getValue())).findFirst().get();
        eventPublisher.publishEvent(new PortfolioChangedEvent(this, portfolio));

        List<Asset> assets = userSession.getCurrentUser().getPortfolios().stream().filter(p -> p.getPortfolioName().equals(portfolioOfAssetToBuyChoiceBox.getValue())).findFirst().get().getAssets();
        Asset asset = assets.stream().filter(a -> a.getAssetName().equals(assetToBuyChoiceBox.getValue())).findFirst().orElse(null);

        int portfolioId= portfolio.getPortfolioId();
        int assetId = asset.getAssetId();
        int quantity = Integer.parseInt(assetQuantity.getText());
        BigDecimal price = new BigDecimal(Integer.parseInt(this.assetPrice.getText()));
        BigDecimal interestRate = this.interestRate.getText().equals("")? new BigDecimal(0) : new BigDecimal(Integer.parseInt(this.interestRate.getText()));
        if (asset==null){
            showAlert("asset not found", "asset not found in the portfolio");
            // ToDo create a new asset for user
            transactionService.executeTransaction(quantity,price,portfolioId, Constants.TRANSACTION_TYPE.BUY, interestRate, Constants.ASSET_TYPE_MAP.get(assetToBuyChoiceBox.getValue()), assetToBuyChoiceBox.getValue());
        }
        else {eventPublisher.publishEvent(new AssetChangedEvent(this, asset));
            transactionService.executeTransaction(quantity, price, portfolioId, assetId, Constants.TRANSACTION_TYPE.BUY);
        }
    }

    protected void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
