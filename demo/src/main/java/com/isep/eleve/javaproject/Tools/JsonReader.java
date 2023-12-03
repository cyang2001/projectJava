package com.isep.eleve.javaproject.Tools;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.io.IOException;
/**
 * support class for read an object from Json file
 * @version V1.0
 * @author Chen YANG
 */
public class JsonReader {

    public static <T> T readJsonFromStream(InputStream inputStream, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(inputStream, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
