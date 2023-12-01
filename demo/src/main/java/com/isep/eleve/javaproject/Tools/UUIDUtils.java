package com.isep.eleve.javaproject.Tools;

import java.util.UUID;

/**
 * support class for generating UUID
 */
public class UUIDUtils {
  public static String getUUID(){
      return UUID.randomUUID().toString().replace("-","");
  }

  public static Integer getUUIDInOrderId(){
      Integer orderId=UUID.randomUUID().toString().hashCode();
      orderId = orderId < 0 ? -orderId : orderId; 
      return orderId;
  }

  public static void main(String[] args){
      System.out.println(getUUIDInOrderId());
  }
}