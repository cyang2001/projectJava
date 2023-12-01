package com.isep.eleve.javaproject.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import com.isep.eleve.javaproject.model.User;
import com.isep.eleve.javaproject.service.*;
public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    protected void handleLoginAction(ActionEvent event) {
        // get the username and password from the UI
        String username = usernameField.getText();
        String password = passwordField.getText();
        // call the authentication service
        AuthenticationService authenticationService = new AuthenticationService();
        User user = authenticationService.authenticate(username, password);
        System.out.println("Username: " + user.getUserName() + ", Password: " + user.getPasswordHash() + ", User ID: " + user.getUserId());

        
    }
}