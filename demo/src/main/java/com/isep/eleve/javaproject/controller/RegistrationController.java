package com.isep.eleve.javaproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.isep.eleve.javaproject.*;
//import com.isep.eleve.javaproject.model.User;
import com.isep.eleve.javaproject.service.*;
import com.isep.eleve.javaproject.service.userServices.RegistrationService;
import com.isep.eleve.javaproject.Tools.*;
/**
 * Registration controller
 * @version V1.2
 * @Author Chen YANG
 */
@Controller
public class RegistrationController {
  @Autowired
  private RegistrationService registrationService;
  @FXML
  private TextField usernameField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private PasswordField passwordField2;

  @FXML
  protected void handleRegistrationAction(ActionEvent event) throws IOException {
    // get the username and password from the UI
    String username = usernameField.getText();
    String password = passwordField.getText();
    String passwordEnsurance = passwordField2.getText();
    // call the authentication service
    RegistrationService.RegistrationResult result = registrationService.register(username, password, passwordEnsurance);
    //System.out.println("sign up pressed"); // only for debug
    
    if (result == null)
      return;

    // Todo: add more error handling
    // for example, password cannot be empty
    // or username cannot be empty
    
    if (!result.isSuccess()) {
      if (result.getFailureType() == Constants.REGISTRATION_FAILIURE_TYPE.PASSWORD_NOT_SAME) {
        App.showAlert("Password Not Same", "The passwords do not match", "Please try again.");
      } else if (result.getFailureType() == Constants.REGISTRATION_FAILIURE_TYPE.USERNAME_ALREADY_EXIST) {
        App.showAlert("Username Exists", "This username is already taken", "Please try a different username.");
      }
    } else {
      App.showAlert("Registration Success", "You have successfully registered", "Welcome!");
      //User user = result.getUser();
    }
  }

  @FXML
  protected void handleChageLoginPageAction(ActionEvent event) throws Exception {
    App.switchToLogin();
  }

}