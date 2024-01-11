package com.isep.eleve.javaproject.controller;

import com.isep.eleve.javaproject.App;
import javafx.event.ActionEvent;

public class SellCryptoController extends App {
    public void handleUserInformationAction(ActionEvent event) {
    }

    public void handleConfirmationAction(ActionEvent event) {
    }

    public void handleChangeBuyCryptoPageAction(ActionEvent event) throws Exception {
        switchToBuyCryptoPage();
    }

    public void handleChangeSellCryptoPageAction(ActionEvent event) throws Exception {
        switchToSellCryptoPage();
    }
}
