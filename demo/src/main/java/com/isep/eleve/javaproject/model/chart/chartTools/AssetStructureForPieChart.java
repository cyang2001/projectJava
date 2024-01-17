package com.isep.eleve.javaproject.model.chart.chartTools;

import java.math.BigDecimal;

public class AssetStructureForPieChart {
    private String assetName;
    private int assetQuantity;
    private BigDecimal assetValue;
    private BigDecimal totalValueOfPortfolio;

    public AssetStructureForPieChart(String assetName, int assetQuantity, BigDecimal assetValue) {
        this.assetName = assetName;
        this.assetQuantity = assetQuantity;
        this.assetValue = assetValue;
    }

    // 计算总资产价值的方法
    public void setTotalValueOfPortfolio(BigDecimal totalValue) {
        this.totalValueOfPortfolio = totalValue;
    }

    // 获取资产名称
    public String getAssetName() {
        return assetName;
    }

    // 获取资产数量
    public int getAssetQuantity() {
        return assetQuantity;
    }

    // 获取资产价值
    public BigDecimal getAssetValue() {
        return assetValue;
    }

    // 获取该资产占总资产的百分比
    public BigDecimal getPercentageOfTotal() {
        return assetValue.divide(totalValueOfPortfolio, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
    }

}
