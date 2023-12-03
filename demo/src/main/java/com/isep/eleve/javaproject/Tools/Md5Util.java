package com.isep.eleve.javaproject.Tools;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * support class for encrypt data by MD5
 * @version V1.0
 * @see https://fr.wikipedia.org/wiki/MD5
 * @author Chen YANG
 */
public class Md5Util {
  public static String md5(String string) {
    if (string.isEmpty()) {
      return "";
    }
    MessageDigest md5 = null;
    try {
      md5 = MessageDigest.getInstance("MD5");
      byte[] bytes = md5.digest(string.getBytes("UTF-8"));
      String result = "";
      for (byte b : bytes) {
        String temp = Integer.toHexString(b & 0xff);
        if (temp.length() == 1) {
          temp = "0" + temp;
        }
        result += temp;
      }
      return result;
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return "";
  }
}
