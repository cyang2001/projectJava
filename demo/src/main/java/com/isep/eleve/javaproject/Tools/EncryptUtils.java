package com.isep.eleve.javaproject.Tools;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * This class provides utility methods for 
 * generating hash values.
 * @version V1.0
 * @see https://fr.wikipedia.org/wiki/MD5
 * @author Chen YANG
 */
public class EncryptUtils {
  public static String encrypt(String str, Constants.ENCRYPT_TYPE type) throws IllegalArgumentException {
    switch (type) {
      case MD5:
        return md5(str);
      case SHA1:
        return sha1(str);
      case SHA256:
        return sha256(str);
      case SHA512:
        return sha512(str);
      default:
        throw new IllegalArgumentException("Unsupported encrypt type: " + type);
    }
  }
  /**
   * Generates the MD5 hash value for the 
   * given string.
   *
   * @param string the input string
   * @return the MD5 hash value as a string
   */
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
  /**
   * Calculates the SHA1 hash value of a 
   * given string.
   *
   * @param string the input string to be
   *  hashed
   * @return the SHA1 hash value of the 
   * input string
   */
  public static String sha1(String string) {
    if (string.isEmpty()) {
      return "";
    }
    MessageDigest sha1 = null;
    try {
      sha1 = MessageDigest.getInstance("SHA1");
      byte[] bytes = sha1.digest(string.getBytes("UTF-8"));
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
  /**
   * Calculates the SHA-256 hash value of
   *  the given string.
   *
   * @param string the input string to be 
   * hashed
   * @return the SHA-256 hash value of the
   *  input string
   */
  public static String sha256(String string) {
    if (string.isEmpty()) {
      return "";
    }
    MessageDigest sha256 = null;
    try {
      sha256 = MessageDigest.getInstance("SHA-256");
      byte[] bytes = sha256.digest(string.getBytes("UTF-8"));
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
  /**
   * Calculates the SHA-512 hash value of 
   * the given string.
   *
   * @param string the input string to be 
   * hashed
   * @return the SHA-512 hash value of the
   *  input string
   */
  public static String sha512(String string) {
    if (string.isEmpty()) {
      return "";
    }
    MessageDigest sha512 = null;
    try {
      sha512 = MessageDigest.getInstance("SHA-512");
      byte[] bytes = sha512.digest(string.getBytes("UTF-8"));
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
