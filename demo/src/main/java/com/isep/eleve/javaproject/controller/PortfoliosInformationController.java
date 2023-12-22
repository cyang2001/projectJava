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
public class PortfoliosInformationController {
    // ToDo : add needed service
    protected void handleUserInformationAction(Action event){
        //Switch to the UserInformation view
    }

    protected void handlePortfolio1InformationAction(Action event){
        //Switch to the Portfolio1Information view
    }

    protected void handlePortfolio2InformationAction(Action event){
        //Switch to the Portfolio2Information view
    }

    protected void handlePortfolioNInformationAction(Actione event){
        //Switch to the PortfolioNInformation view
    }
}