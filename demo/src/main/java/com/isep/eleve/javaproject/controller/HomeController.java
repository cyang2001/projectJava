package com.isep.eleve.javaproject.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import com.isep.eleve.javaproject.App;

@Controller
public class HomeController {
    //// ToDo : add needed service
    protected void handleUserInformationAction(ActionEvent event){
        //Switch to the UserInformation view
    }

    //Les bouttons seront sur toutes les pages donc je sais pas si on met les commandes reliées aux bouttons
    // sur tous les controller ou que sur le controller menu (si on fait comme ca, je sais pas comment faire)

    public void handleChangeHomePageAction(ActionEvent event) {
        // TODO: 29/12/2023 : add switch page 
    }

    public void handleChangePortfolioPageAction(ActionEvent event) {
        // TODO: 29/12/2023 : add switch page
    }

    public void handleChangeNewPortfolioPageAction(ActionEvent event) {
        // TODO: 29/12/2023 : add switch page
    }

    public void handleChangeAssetsPageAction(ActionEvent event) {
        // TODO: 29/12/2023 : add switch page
    }

    public void handleChangeBuySellAssetsPageAction(ActionEvent event) {
        // TODO: 29/12/2023 : add switch page
    }

    public void handleChangeCryptoPageAction(ActionEvent event) {
        // TODO: 29/12/2023 : add switch page
    }

    public void handleChangeBuySellCryptoPageAction(ActionEvent event) {
        // TODO: 29/12/2023 : add switch page
    }

    public void handleChangeAnalysisPageAction(ActionEvent event) {
        // TODO: 29/12/2023 : add switch page
    }
}