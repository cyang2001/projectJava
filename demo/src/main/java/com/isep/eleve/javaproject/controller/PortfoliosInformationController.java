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
    protected void handleUserInformationAction(ActionEvent event){
        //Switch to the UserInformation view
    }

    protected void handlePortfolio1InformationAction(ActionEvent event){
        //Switch to the Portfolio1Information view
    }

    protected void handlePortfolio2InformationAction(ActionEvent event){
        //Switch to the Portfolio2Information view
    }

    protected void handlePortfolioNInformationAction(ActioneEvent event){
        //Switch to the PortfolioNInformation view
    }
}