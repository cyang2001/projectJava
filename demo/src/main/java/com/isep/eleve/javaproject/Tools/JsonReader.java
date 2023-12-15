package com.isep.eleve.javaproject.Tools;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.io.IOException;
/**
 * A utility class for reading JSON data 
 * from an input stream.
 * @version V1.0
 * @author Chen YANG
 */
public class JsonReader {

  /**
   * Reads JSON data from the specified 
   * input stream and converts it into an 
   * object of the specified class.
   *
   * @param inputStream the input stream containing the JSON data
   * @param clazz the class to which the JSON data should be converted
   * @param <T> the type of the object to be returned
   * @return an object of the specified 
   * class representing the JSON data, or 
   * null if an error occurs during reading
   */
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
