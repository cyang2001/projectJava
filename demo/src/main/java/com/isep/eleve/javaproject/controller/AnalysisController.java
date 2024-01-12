package com.isep.eleve.javaproject.controller;

import com.isep.eleve.javaproject.App;
import javafx.event.ActionEvent;

public class AnalysisController extends App {

    public void handleUserInformationAction(ActionEvent event) {
    }

    public void handleDemonstrateValueAction(ActionEvent event) throws Exception {
        switchToDemonstrateValuePage();
    }

    public void handleAddEventsAction(ActionEvent event) throws Exception {
        switchToAddEventsPage();
    }

    public void handleDeleteEventsAction(ActionEvent event) throws Exception {
        switchToDeleteEventPage();
    }

    public void handleLogOutAction(ActionEvent event) {
    }
}
