package com.isep.eleve.javaproject.controller;

import com.isep.eleve.javaproject.App;
import javafx.event.ActionEvent;

public class BuyAssetController extends App {

    public void handleUserInformationAction(ActionEvent event) {
    }

    public void handleConfirmationAction(ActionEvent event) {
    }

    public void handleChangeBuyAssetsPageAction(ActionEvent event) throws Exception {
        switchToBuyAssetPage();
    }

    public void handleChangeSellAssetsPageAction(ActionEvent event) throws Exception {
        switchToSellAssetPage();
    }
}
