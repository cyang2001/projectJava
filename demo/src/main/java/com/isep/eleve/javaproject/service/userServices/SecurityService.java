package com.isep.eleve.javaproject.service.userServices;

import org.springframework.stereotype.Service;

import com.isep.eleve.javaproject.Tools.*;
/**
 * Security service
 * @version V1.2
 * @author YANG Chen
 */
@Service
public class SecurityService {
  // encryption type
  //public String type; 
  // if need more encryption type, add here
  public String encryptData(String plainData, Constants.ENCRYPT_TYPE type) {
      // encrypt data logic
      return EncryptUtils.encrypt(plainData, type);
  }

  public String decryptData(String encryptedData) {
      // decrypt data logic
      // I dont know if we need this, but it is here if we do
      return encryptedData;
  }


}