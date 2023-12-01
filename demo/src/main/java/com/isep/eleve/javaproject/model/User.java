package com.isep.eleve.javaproject.model;

import java.util.List;

import com.isep.eleve.javaproject.Tools.*;


/**
 * UserModel
 * @author: Chen YANG
 * @version: 1.0
 */
public class User {
  // user ID
  private Integer userId;
  public Integer getUserId() {
    return this.userId;
  }
  // user name
  private String userName;
  public String getUserName() {
    return this.userName;
  }
  // user password hashed
  private String passwordHash;
  public String getPasswordHash() {
    return this.passwordHash;
  }
  // all portfolios of the user
  private List<Portfolio> portfolios;

  public User(String userName, String passwordHash) {
      this.userName = userName;
      this.passwordHash = passwordHash;
      this.portfolios = null;
      this.userId = UUIDUtils.getUUIDInOrderId();

  }
  
  /**
   * Login
   * @param userName user name
   * @param password password without hash
   * @return boolean
   */
  public boolean login(String userName, String password) {
      // login logic (simulated for now, in real life it would interact with a database or something)
      // usually here we would call the AuthenticationService's authenticate method
      return true;
  }
  /**
   * Logout
   * @return void
   */
  public void logout() {
      // logout logic
  }
  /**
   * updateProfile
   * @return boolean
   * @Description: update profile
   */
  public boolean updateProfile() {
      // update profile logic
      return true;
  }

  // pls add getters and setters 
}
