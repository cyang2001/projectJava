package com.isep.eleve.javaproject.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import com.isep.eleve.javaproject.App;

public class MenuController extends App{
    // ToDo : add needed service
    // TODO: 29/12/2023 faire les switch page 
    protected void handleChangeHomePageAction(ActionEvent event) throws Exception {
        switchToHome();
    }

    protected void handleChangePortfolioPageAction(ActionEvent event) throws Exception {
        switchToPortfolioPage();

    }
    protected void handleChangeNewPortfolioPageAction(ActionEvent event) throws Exception {
        switchToNewPortfolioPage();
    }

    protected void handleChangeAssetsPageAction(ActionEvent event) throws Exception {
        switchToAssetInformationPage();
    }

    protected void handleChangeBuySellAssetsPageAction(ActionEvent event) throws Exception {
        switchToBuyAssetPage();
    }

    protected void handleChangeCryptoPageAction(ActionEvent event) throws Exception {
        switchToCryptoInformationPage();
    }

    protected void handleChangeBuySellCryptoPageAction(ActionEvent event) throws Exception {
        switchToBuyCryptoPage();
    }

    protected void handleChangeAnalysisPageAction(ActionEvent event) throws Exception {
      switchToAnalysisPage();
    }

}