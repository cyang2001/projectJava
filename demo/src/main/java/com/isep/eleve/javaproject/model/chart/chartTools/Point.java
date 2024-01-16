package com.isep.eleve.javaproject.model.chart.chartTools;

public class Point {

        private String date;
        private String closePrice;

        public Point(String date, String closePrice) {
            this.date = date;
            this.closePrice = closePrice;
        }

        public String getDate() {
            return date;
        }


        public String getClosePrice() {
            return closePrice;
        }

    public void setDate(String date) {
        this.date = date;
    }


    public void setClosePrice(String closePrice) {
        this.closePrice = closePrice;
    }
}
