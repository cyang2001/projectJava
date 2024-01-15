package com.isep.eleve.javaproject.model.chart;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class LineChartUtils {
    private LineChart lineChart;

    LineChartUtils(LineChart lineChart) {
        this.lineChart = lineChart;
    }

    void operateLineChart() {
        //Declare two axes
        final CategoryAxis xAxis = (CategoryAxis) lineChart.getXAxis();
        final NumberAxis yAxis = (NumberAxis) lineChart.getYAxis();
        //Define the name of the axis
        xAxis.setLabel("months");
        yAxis.setLabel("shares");
        yAxis.setAnimated(true);
        //Define the name of the form
        lineChart.setTitle("");
    }
}


