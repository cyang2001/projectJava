package com.isep.eleve.javaproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.isep.eleve.javaproject.App;
import com.isep.eleve.javaproject.Tools.Constants;

@Controller
public class HomeController {
  @FXML
  private StackPane contentArea;

  private void loadContent(String fxml) {
    try {
      contentArea.getChildren().clear(); 
      Node content = FXMLLoader.load(getClass().getResource(fxml)); 
      contentArea.getChildren().add(content);
    } catch (IOException e) {
      e.printStackTrace(); 
    }
  }

  public void handleUserInformationAction(ActionEvent event) {

  }

  public void handleChangeHomePageAction(ActionEvent event) {
  }

  public void handleChangePortfolioPageAction(ActionEvent event) {
  }
  
  public void handleChangeAssetsPageAction(ActionEvent event) {
  }

  public void handleChangeBuySellAssetsPageAction(ActionEvent event) {
  }

  public void handleChangeCryptoPageAction(ActionEvent event) {
  }

  public void handleChangeBuySellCryptoPageAction(ActionEvent event) {
  }

  public void handleChangeAnalysisPageAction(ActionEvent event) {
  }

}