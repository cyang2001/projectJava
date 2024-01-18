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

    public void setTotalValueOfPortfolio(BigDecimal totalValue) {
        this.totalValueOfPortfolio = totalValue;
    }

    public String getAssetName() {
        return assetName;
    }

    public int getAssetQuantity() {
        return assetQuantity;
    }

    public BigDecimal getAssetValue() {
        return assetValue;
    }

    public BigDecimal getPercentageOfTotal() {
        return assetValue.divide(totalValueOfPortfolio, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
    }

}
