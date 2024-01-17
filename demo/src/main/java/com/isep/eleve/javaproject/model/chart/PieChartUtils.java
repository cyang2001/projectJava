package com.isep.eleve.javaproject.model.chart;

import com.isep.eleve.javaproject.model.chart.chartTools.AssetStructureForPieChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PieChartUtils {
    private PieChart pieChart;
    private ObservableList<PieChart.Data> pieChartData;

    PieChartUtils(PieChart pieChart) {
        pieChartData = FXCollections.observableArrayList();
        this.pieChart = pieChart;
    }

    private void addData(String name, double value) {
        pieChartData.add(new PieChart.Data(name, value));
    }

    private void showChart() {
        pieChart.setData(pieChartData);
    }

    private void setDataColor(int index, String color) {
        pieChartData.get(index).getNode().setStyle("-fx-background-color: " + color);
    }

    private void setMarkVisible(boolean b) {
        pieChart.setLabelsVisible(b);
    }

    private void setLegendColor(HashMap<Integer, String> colors) {
        String setColor = "";
        for(Map.Entry<Integer, String> entry : colors.entrySet()) {
            int index = entry.getKey();
            String color = entry.getValue();
            setColor = setColor.concat("CHART_COLOR_" + (index + 1) + ":" + color + ";");
        }
        pieChart.setStyle(setColor);
    }

    private void setLegendSide(String side) {
        if(side.equalsIgnoreCase("top"))
            pieChart.setLegendSide(Side.TOP);
        else if(side.equalsIgnoreCase("bottom"))
            pieChart.setLegendSide(Side.BOTTOM);
        else if(side.equalsIgnoreCase("left"))
            pieChart.setLegendSide(Side.LEFT);
        else {
            pieChart.setLegendSide(Side.RIGHT);
        }
    }

    private void setTitle(String title) {
        pieChart.setTitle(title);
    }

    void operatePieChart(List<AssetStructureForPieChart> assetStructures) {
        // 清空旧数据
        pieChartData.clear();

        // 计算总资产价值
        BigDecimal totalValue = assetStructures.stream()
                .map(AssetStructureForPieChart::getAssetValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 为每个资产添加数据到饼图
        for (AssetStructureForPieChart assetStructure : assetStructures) {
            BigDecimal percentage = assetStructure.getAssetValue().divide(totalValue, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
            addData(assetStructure.getAssetName() + " (" + percentage.setScale(2, BigDecimal.ROUND_HALF_UP) + "%)", assetStructure.getAssetValue().doubleValue());
        }

        // 显示图表
        showChart();

        // 可以根据需要设置颜色和其他属性
       }
}
