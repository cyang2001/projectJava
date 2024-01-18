package com.isep.eleve.javaproject.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.isep.eleve.javaproject.model.Portfolio;
import com.isep.eleve.javaproject.session.UserSession;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
@Controller
public class UserInformationController {
  @FXML
  private TextArea userInformation;
  private UserSession userSession;
  public UserInformationController(UserSession userSession) {
    this.userSession = userSession;
  }
    public void handleConfirmationAction(ActionEvent event) {
      StringBuffer sb = new StringBuffer();
      sb.append("User Name: " + userSession.getCurrentUser().getUserName() + "\n");
      List<Portfolio> portfolios = userSession.getCurrentUser().getPortfolios();
      for (Portfolio portfolio : portfolios) {
        sb.append("Portfolio Name: " + portfolio.getPortfolioName() + "\n");
        sb.append("Portfolio Value: " + portfolio.getValue() + "\n");
      }
      userInformation.setText(sb.toString());
    }
}
