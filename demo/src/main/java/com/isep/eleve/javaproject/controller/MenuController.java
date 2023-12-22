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
public class MenuController {
    // ToDo : add needed service
    protected void handleChangeHomePageAction(ActionEvent event) {
        //Switch to the home view
        

    }
    protected void handleChangePortfolioPageAction(ActionEvent event){
        //Switch to the portfolio view

    }
    protected void handleChangeNewPortfolioPageAction(ActionEvent event){
        //Switch to the Newportfolio view
    }

    protected void handleAssetsPageAction(ActionEvent event){
        //Switch to the Assets view
    }

    protected void handleChangeBuyAssetsPageAction(ActionEvent event){
        //Switch to the BuyAssets view
    }

    protected void handleChangeSellAssetsPageAction(ActionEvent event){
        //Switch to the SellAssets view
    }

    protected void handleChangeCryptoPageAction(ActionEvent event){
        //Switch to the Crypto view
    }

    protected void handleChangeBuyCryptoPageAction(ActionEvent event){
        //Switch to the BuyCrypto view
    }

    protected void handleChangeSellCryptoPageAction(ActionEvent event){
        //Switch to the SellCrypto view
    }

    protected void handleChangeAnalysisPageAction(ActionEvent event){
        //Switch to the Analysis view
    }

}