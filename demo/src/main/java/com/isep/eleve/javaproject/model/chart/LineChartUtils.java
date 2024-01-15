package com.isep.eleve.javaproject.model.chart;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import com.isep.eleve.javaproject.model.chart.chartTools.*;

import java.util.List;

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
        lineChart.setTitle("Stock price changes");
    }


        public void addDataSeries(List<Point> stockData) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("AAPL Stock Price");
            //将数据点添加到系列中
            for (Point dataPoint : stockData) {
                series.getData().add(new XYChart.Data<>(dataPoint.getDate(), Double.parseDouble(dataPoint.getClosePrice())));
            }

            // 将系列添加到图表中
            lineChart.getData().add(series);
        }
    }




