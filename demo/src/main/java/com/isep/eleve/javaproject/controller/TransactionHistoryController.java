package com.isep.eleve.javaproject.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.isep.eleve.javaproject.model.Transaction;
import com.isep.eleve.javaproject.repository.TransactionRepository;
import com.isep.eleve.javaproject.service.TransactionService;
import com.isep.eleve.javaproject.session.UserSession;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

@Controller
public class TransactionHistoryController {
  @FXML
  private ChoiceBox<String> portfolioChoiceBox;
  @FXML
  private TextArea transactionHistoryTextArea;
  private final UserSession userSession;
  private final TransactionService transactionService;
  @Autowired
  public TransactionHistoryController(UserSession userSession, TransactionService transactionService) {
    this.userSession = userSession;
    this.transactionService = transactionService;
  }
  @FXML
  public void initialize() {
    List<String> portfolioNames = userSession.getCurrentUser().getPortfolios().stream().map(portfolio -> portfolio.getPortfolioName()).collect(java.util.stream.Collectors.toList());
    portfolioChoiceBox.setItems(FXCollections.observableArrayList(portfolioNames));
  }
  public void handleConfirmationAction(ActionEvent event) throws IOException {
    String portfolioName = portfolioChoiceBox.getValue();
    int portfolioId = userSession.getCurrentUser().getPortfolios().stream().filter(portfolio -> portfolio.getPortfolioName().equals(portfolioName)).findFirst().get().getPortfolioId();
    List<Transaction> transactions = transactionService.getTransactionByOwnerId(userSession.getCurrentUser().getUserId());
    List<Transaction> transactions2 = transactions.stream().filter(transaction -> transaction.getPortfolioId() == portfolioId).collect(java.util.stream.Collectors.toList());
    StringBuffer sb = new StringBuffer();
    sb.append("Transaction History for " + portfolioName);
    sb.append("\n");
    for (Transaction transaction : transactions2) {
      sb.append("transactionId: " + transaction.getTransactionId() + "\n");
      sb.append("assetId: " + transaction.getAssetId() + "\n");
      sb.append("quantity: " + transaction.getQuantity() + "\n");
      sb.append("price: " + transaction.getPrice() + "\n");
      sb.append("transactionType: " + transaction.getTransactionType() + "\n");
      sb.append("timeStamp: " + transaction.getTimeStamp() + "\n");
    }
    
    transactionHistoryTextArea.setText(sb.toString());
}
}
