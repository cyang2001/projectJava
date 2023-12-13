package com.isep.eleve.javaproject.Tools;



public class Constants {
  public static final String LOGIN_VIEW_PATH = "/com/isep/eleve/javaproject/view/LoginView.fxml"; // path to the login view
  public static final String REGISTRATION_VIEW_PATH = "/com/isep/eleve/javaproject/view/RegistrationView.fxml"; // path to the registration view
  public static enum ENCRYPT_TYPE {
    MD5,
    SHA1,
    SHA256,
    SHA512,
  };
  public static enum REGISTRATION_FAILIURE_TYPE {
    PASSWORD_NOT_SAME,
    USERNAME_ALREADY_EXIST,
  };

}