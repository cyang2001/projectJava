package com.isep.eleve.javaproject.model.chart.chartTools;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import com.isep.eleve.javaproject.Tools.*;

public class AAPLDataParser {
    public static List<Point> parseAAPLData(String filePath) {
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            String[] data = JsonReader.readJsonFromStream(inputStream, String[].class);
            List<Point> stockData = new ArrayList<>();

            for (String entry : data) {
                String[] values = entry.split(",");
                String date = values[0];
                String closePrice = values[4];
                stockData.add(new Point(date, closePrice));
            }

            return stockData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}









