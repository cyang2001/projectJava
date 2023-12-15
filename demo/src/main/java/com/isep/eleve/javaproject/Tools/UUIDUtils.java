package com.isep.eleve.javaproject.Tools;

import java.util.UUID;

/**
 * The UUIDUtils class provides utility 
 * methods for generating UUIDs and 
 * converting them to different formats.
 * @version V1.0
 * @see https://fr.wikipedia.org/wiki/Universally_unique_identifier
 * @author Chen YANG
 */
public class UUIDUtils {
  /**
   * Generates a random UUID and returns it as a string without hyphens.
   *
   * @return The generated UUID as a string without hyphens.
   */
  public static String getUUID(){
    return UUID.randomUUID().toString().replace("-","");
  }

  /**
   * Generates a random UUID and converts it to an integer value suitable for use as an order ID.
   *
   * @return The generated UUID as an integer order ID.
   */
  public static Integer getUUIDInOrderId(){
    Integer orderId=UUID.randomUUID().toString().hashCode();
    orderId = orderId < 0 ? -orderId : orderId; 
    return orderId;
  }
}