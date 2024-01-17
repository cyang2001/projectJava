package com.isep.eleve.javaproject.controller;

import com.isep.eleve.javaproject.App;
import com.isep.eleve.javaproject.model.User;
import com.isep.eleve.javaproject.model.chart.LineChartUtils;
import com.isep.eleve.javaproject.model.chart.chartTools.AAPLDataParser;
import com.isep.eleve.javaproject.model.chart.chartTools.Point;
import com.isep.eleve.javaproject.session.UserSession;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ChoiceBox;

import java.util.List;

import org.springframework.stereotype.Controller;

@Controller
public class AnalysisController {
    @FXML
    private LineChart lineChart;
    @FXML
    private ChoiceBox<String> assetChoosen;
    @FXML 
    public void initialize() {
        assetChoosen.setItems(FXCollections.observableArrayList("AAPL"));
    }
    public void handleShowLineChart(ActionEvent actionEvent) {
        List<Point> parseAAPLData = AAPLDataParser.parseAAPLData();
        LineChartUtils lineChartUtils = new LineChartUtils(lineChart);
        lineChartUtils.operateLineChart();
        lineChartUtils.addDataSeries(parseAAPLData);
    }
    public void handleUserInformationAction(ActionEvent actionEvent) {

    }
    public void handleLogOutAction(ActionEvent actionEvent) {

    }
}
