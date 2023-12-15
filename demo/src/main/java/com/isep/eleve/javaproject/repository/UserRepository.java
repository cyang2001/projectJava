package com.isep.eleve.javaproject.repository;


import com.isep.eleve.javaproject.Tools.FileOperation;
import com.isep.eleve.javaproject.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
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
    return fileOperation.readListFromFile(EXTERNAL_FILE_PATH, User.class);
  }

  /**
   * Saves a user to the file.
   * 
   * @param user the User object to be saved
   * @throws IOException if an I/O error occurs
   */
  public void save(User user) throws IOException {
    List<User> users = findAll();
    users.add(user);
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
}
