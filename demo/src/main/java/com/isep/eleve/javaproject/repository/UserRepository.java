package com.isep.eleve.javaproject.repository;


import com.fasterxml.jackson.core.type.TypeReference;
import com.isep.eleve.javaproject.Tools.FileOperation;
import com.isep.eleve.javaproject.model.Asset;
import com.isep.eleve.javaproject.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * User repository
 * @version V1.0
 * @author Chen YANG
 */
/**
 * This class represents a repository for managing user data.
 */
@Repository
public class UserRepository {
  // File path
  private static final String EXTERNAL_FILE_PATH = System.getProperty("user.home") + File.separator + "App" + File.separator + "databaseUser.json";
  private FileOperation fileOperation;
  private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);
  /**
   * Constructs a new UserRepository with the specified FileOperation.
   * 
   * @param fileOperation the FileOperation used for file operations
   */
  @Autowired
  public UserRepository(FileOperation fileOperation) {
    this.fileOperation = fileOperation;
  }

  /**
   * Retrieves a list of all users from the file.
   * 
   * @return a list of User objects
   * @throws IOException if an I/O error occurs
   */
  public List<User> findAll() throws IOException {
    TypeReference<List<User>> typeReference = new TypeReference<List<User>>() {};
    return fileOperation.readListFromFile(EXTERNAL_FILE_PATH, typeReference);
  }

  /**
   * Saves a user to the file.
   * 
   * @param user the User object to be saved
   * @throws IOException if an I/O error occurs
   */
  public void save(User updatedUser) throws IOException {
    List<User> users = findAll();
    if (users.size() == 0) {
      users = new ArrayList<>();
      users.add(updatedUser);
      logger.info("new user added in vide ficher: " + updatedUser.getUserName());
    } else {
      
    boolean flag = false;
    for (int i = 0; i < users.size(); i++) {
        logger.info("Deserialized user: " + users.get(i) +"with userId: " + users.get(i).getUserId());
        logger.info("updated user: " + updatedUser +"with userId: " + updatedUser.getUserId());
        if (users.get(i).getUserId().equals(updatedUser.getUserId())) {
            users.set(i, updatedUser); 
            flag = true;
            logger.info("user updated: " + updatedUser.getUserName());
            break;
        }
    }
    if (flag == false) {
        users.add(updatedUser);
        logger.info("new user added: " + updatedUser.getUserName());
    }
    }
    

    fileOperation.writeListToFile(EXTERNAL_FILE_PATH, users);
}





  /**
   * Finds a user by their username.
   * 
   * @param userName the username of the user to be found
   * @return the User object if found, null otherwise
   * @throws IOException if an I/O error occurs
   */
  public User findByUserName(String userName) throws IOException {
    List<User> users = findAll();
    return users.stream().filter(u -> u.getUserName().equals(userName)).findFirst().orElse(null);
  }
  public User findByUserId(int userId) throws IOException {
    List<User> users = findAll();
    return users.stream().filter(u -> u.getUserId() == userId).findFirst().orElse(null);
  }
}
