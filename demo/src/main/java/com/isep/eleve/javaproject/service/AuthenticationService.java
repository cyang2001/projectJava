package com.isep.eleve.javaproject.service;
import com.isep.eleve.javaproject.model.User;
/**
 * AuthenticationService
 * @author Chen YANG
 * @version 1.0
 */ 
public class AuthenticationService {
  // use SecurityService to encrypt and decrypt passwords
  private SecurityService securityService; 
    /**
     * Authenticate
     * @param userName user name
     * @param password password without hash
     * @return User
     */
    public User authenticate(String userName, String password) {
        // authentication logic, if authentication succeeds return User object, otherwise return null
        // here should use securityService.encryptPassword to encrypt password, then compare with the hash in the database
        return new User(); // simulated for now
    }
    /**
     * encryptPassword
     * @param userName user name
     * @param password password without hash
     * @return encrypted password
     */
    public String encryptPassword(String password) {
        // encrypt password logic
        // should call securityService.encryptData
        return securityService.encryptData(password);
    }
    /**
     * checkPassword
     * @param passwordHash
     * @param password
     * @return if password matches
     */
    public boolean checkPassword(String passwordHash, String password) {
        // check if password matches logic
        // should call securityService.decryptData
        String decryptedPassword = securityService.decryptData(passwordHash);
        return decryptedPassword.equals(password);
    }
    
    // pls add getters and setters
}