package com.isep.eleve.javaproject.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import com.isep.eleve.javaproject.App;
import com.isep.eleve.javaproject.model.User;
import com.isep.eleve.javaproject.service.*;
/**
 * Login controller
 * @version V1.2
 * @Author Chen YANG
 */
@Controller
public class LoginController {
    @Autowired
    private AuthenticationService authenticationService;
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    protected void handleLoginAction(ActionEvent event) throws IOException {
        // get the username and password from the UI
        String username = usernameField.getText();
        String password = passwordField.getText();
        // call the authentication service
        User user = authenticationService.authenticate(username, password);
        // Todo: add more error handling
        // for example, password cannot be empty
        // or username cannot be empty
        if (user == null) {
            System.out.println("Username or password incorrect");
            return;
        }
        App.showAlert("Login Success", "You have successfully logged in", "Welcome!");
    }

    @FXML
    protected void handleChageRegistrationPageAction(ActionEvent event) throws Exception {
        App.switchToRegistration();
    }
}