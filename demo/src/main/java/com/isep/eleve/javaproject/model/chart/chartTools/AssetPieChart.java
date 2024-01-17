package com.isep.eleve.javaproject.model.chart.chartTools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.math.BigDecimal;
import java.util.List;

public class AssetPieChart {

    public PieChart createPieChart(List<AssetStructureForPieChart> assetStructures) {
        // 计算总资产价值
        BigDecimal totalValue = assetStructures.stream()
                .map(AssetStructureForPieChart::getAssetValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 为每个资产设置总资产价值，以便计算百分比
        for (AssetStructureForPieChart assetStructure : assetStructures) {
            assetStructure.setTotalValueOfPortfolio(totalValue);
        }

        // 创建饼图数据
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (AssetStructureForPieChart assetStructure : assetStructures) {
            pieChartData.add(new PieChart.Data(assetStructure.getAssetName() + " (" + assetStructure.getPercentageOfTotal().setScale(2, BigDecimal.ROUND_HALF_UP) + "%)", assetStructure.getAssetValue().doubleValue()));
        }

        // 创建饼图并设置标题
        PieChart chart = new PieChart(pieChartData);
        chart.setTitle("asset distribution");

        return chart;
    }
}
