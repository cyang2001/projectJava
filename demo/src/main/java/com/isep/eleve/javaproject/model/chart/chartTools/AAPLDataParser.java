package com.isep.eleve.javaproject.model.chart.chartTools;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.isep.eleve.javaproject.Tools.*;



import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AAPLDataParser {
    private static Logger logger = LoggerFactory.getLogger(AAPLDataParser.class);

    public static List<Point> parseAAPLData() {
        String resourcePath = "com/isep/eleve/javaproject/reposity/AAPL.json"; 
        InputStream inputStream = null;

        try {
            inputStream = AAPLDataParser.class.getClassLoader().getResourceAsStream(resourcePath);
            if (inputStream == null) {
                throw new IllegalArgumentException("Resource not found: " + resourcePath);
            }

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
            logger.error("Error parsing AAPL data", e);
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("Error closing input stream", e);
                }
            }
        }
    }
}







