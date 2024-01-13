package com.isep.eleve.javaproject.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import com.isep.eleve.javaproject.App;
import com.isep.eleve.javaproject.model.Portfolio;
import com.isep.eleve.javaproject.model.User;
import com.isep.eleve.javaproject.repository.PortfolioRepository;
import com.isep.eleve.javaproject.service.userServices.AuthenticationService;
import com.isep.eleve.javaproject.session.PortfolioSession;
import com.isep.eleve.javaproject.session.UserSession;
/**
 * Login controller
 * @version V1.2
 * @Author Chen YANG
 * fini
 */
@Controller
public class LoginController {
    private final AuthenticationService authenticationService;
    private final UserSession userSession;
    private final PortfolioSession portfolioSession;
    private final PortfolioRepository portfolioRepository;
    @Autowired
    public LoginController(AuthenticationService authenticationService, UserSession userSession, PortfolioSession portfolioSession, PortfolioRepository portfolioRepository) {
        this.authenticationService = authenticationService;
        this.userSession = userSession;
        this.portfolioSession = portfolioSession;
        this.portfolioRepository = portfolioRepository;
    }
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;
    @FXML
    protected void handleLoginAction(ActionEvent event) throws Exception {
        // get the username and password from the UI
        String username = usernameField.getText();
        String password = passwordField.getText();
        // call the authentication service
        User user = authenticationService.authenticate(username, password);
        // Todo: add more error handling
        // for example, password cannot be empty
        // or username cannot be empty
        if (user == null) {
            errorLabel.setText("Invalid username or password");
            return;
        } else {
            errorLabel.setText("");
        }
        userSession.setCurrentUser(user);
        List<Portfolio> portfolios = portfolioRepository.findAll();
        for (Portfolio portfolio : portfolios) {
            if (portfolio.getOwnerId() == user.getUserId()) {
              user.addPortfolio(portfolio);
            }
        }

        App.showAlert("Login Success", "You have successfully logged in", "Welcome!");

        App.switchToHome();
    }

    @FXML
    protected void handleChageRegistrationPageAction(ActionEvent event) throws Exception {
        App.switchToRegistration();
        
    }
}