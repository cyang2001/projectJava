package com.isep.eleve.javaproject.Tools;

/**
 * This class contains constant values used 
 * in the application.
 */
public class Constants {
  /**
   * The path to the login view FXML file.
   */
  public static final String LOGIN_VIEW_PATH = "/com/isep/eleve/javaproject/view/LoginView.fxml";

  /**
   * The path to the registration view FXML file.
   */
  public static final String REGISTRATION_VIEW_PATH = "/com/isep/eleve/javaproject/view/RegistrationView.fxml";

  /**
   * The encryption types available.
   */
  public static enum ENCRYPT_TYPE {
    MD5,
    SHA1,
    SHA256,
    SHA512,
  };

  /**
   * The types of registration failures.
   */
  public static enum REGISTRATION_FAILIURE_TYPE {
    PASSWORD_NOT_SAME,
    USERNAME_ALREADY_EXIST,
  };
  public static enum ASSET_TYPE {
    CASH,
    FIXED_DEPOSIT,
  };
}