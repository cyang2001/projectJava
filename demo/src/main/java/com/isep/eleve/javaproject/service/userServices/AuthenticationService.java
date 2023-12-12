package com.isep.eleve.javaproject.service.userServices;
import com.isep.eleve.javaproject.model.User;
import com.isep.eleve.javaproject.repository.*;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Authentication service
 * @version V1.2
 * @author Chen YANG
 */
@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final SecurityService securityService;
    @Autowired
    public AuthenticationService(SecurityService securityService, UserRepository userRepository){
      this.securityService = securityService;
      this.userRepository = userRepository;
    }
    /**
     * Authenticate user
     * @param userName
     * @param password
     * @return User
     * @throws IOException
     */
    // ToDo create a new class for authentication result just like RegistrationResult
    public User authenticate(String userName, String password) throws IOException {
      // ToDo: check if the password is empty
      // check if the user name already exists

        List<User> users = userRepository.findAll();
            for (User user : users) {
                if (user.getUserName().equals(userName) && checkPassword(user.getPasswordHash(), securityService.encryptData(password))) {
                    return user;
                }
            }
        return null; // Authentication failed
    }
    /**
     * Check password
     * @param passwordHash
     * @param password
     * @return boolean
     */
    private boolean checkPassword(String passwordHash, String password) {
        String decryptedPassword = securityService.decryptData(passwordHash);
        return decryptedPassword.equals(password);
    }

}