package com.isep.eleve.javaproject.repository.dataRepository;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.isep.eleve.javaproject.Tools.FileOperation;
@Repository
public class HistoryDataRepository {
    private static final String EXTERNAL_FILE_PATH = System.getProperty("user.home") + File.separator + "App" + File.separator;
    private FileOperation fileOperation;
    @Autowired
    public HistoryDataRepository(FileOperation fileOperation) {
      this.fileOperation = fileOperation;
    }
    public void save(String symbol) throws Exception {
        String EXTERNAL_FILE_PATH_ = EXTERNAL_FILE_PATH + symbol + ".json";
        String url = "https://query1.finance.yahoo.com/v7/finance/download/" + symbol +
        "?period1=0&period2=9999999999&interval=1mo&events=history";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            String csvResponse = EntityUtils.toString(httpClient.execute(request).getEntity());
            List<String> stockData = Arrays.asList(csvResponse.split("\\r?\\n"));
            fileOperation.writeListToFile(EXTERNAL_FILE_PATH_, stockData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    }



