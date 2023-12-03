package com.isep.eleve.javaproject.Tools;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.OutputStream;
import java.io.IOException;
/**
 * support class for write un object in Json file
 * @version V1.0
 * @author Chen YANG
 */
public class JsonWriter {

    public static <T> void writeJsonToStream(OutputStream outputStream, T object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(outputStream, object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
