package com.isep.eleve.javaproject.Tools;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.OutputStream;
import java.io.IOException;
/**
 * A utility class for writing objects into
 * JSON 
 * data to an output stream.
 * @version V1.0
 * @author Chen YANG
 */
public class JsonWriter {

  /**
   * Writes the specified object as JSON to
   *  the given output stream.
   *
   * @param outputStream the output stream
   *  to write the JSON data to
   * @param object the object to be written
   *  as JSON
   * @param <T> the type of the object
   */
  public static <T> void writeJsonToStream(OutputStream outputStream, T object) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      mapper.writeValue(outputStream, object);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
