package com.isep.eleve.javaproject.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
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
@Repository
public class UserRepository {
    // File path
    private static final String EXTERNAL_FILE_PATH = System.getProperty("user.home") + File.separator + "App" + File.separator + "databaseUser.json";
    private FileOperation fileOperation;
    @Autowired
    public UserRepository(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    /**
     * Find all users
     * @return List<User>
     * @throws IOException
     */
    public List<User> findAll() throws IOException {
        return fileOperation.readListFromFile(EXTERNAL_FILE_PATH, User.class);
    }
    /**
     * Save user
     * @param user
     * @throws IOException
     */
    public void save(User user) throws IOException {
        List<User> users = findAll();
        users.add(user);
        fileOperation.writeListToFile(EXTERNAL_FILE_PATH, users);
    }
    /**
     * Find user by user name
     * @param userName
     * @return User
     * @throws IOException
     */
    public User findByUserName(String userName) throws IOException {
        List<User> users = findAll();
        return users.stream().filter(u -> u.getUserName().equals(userName)).findFirst().orElse(null);
    }
}
