package com.isep.eleve.javaproject.model.chart;
import com.isep.eleve.javaproject.model.chart.chartTools.AssetStructureForPieChart;
import javafx.scene.chart.PieChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.math.BigDecimal;
import java.util.List;
import java.util.HashMap;

public class PieChartUtils {
    private PieChart pieChart;
    private ObservableList<PieChart.Data> pieChartData;

    PieChartUtils(PieChart pieChart) {
        pieChartData = FXCollections.observableArrayList();
        this.pieChart = pieChart;
    }

    private void addData(String name, double value) {
        pieChartData.add(new PieChart.Data(name, value));
    }


    void operatePieChart(List<AssetStructureForPieChart> assetStructures) {
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

        // 根据需要设置颜色和其他属性

    }

    private void showChart() {
    }
}
