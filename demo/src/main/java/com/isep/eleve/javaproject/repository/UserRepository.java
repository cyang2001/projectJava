package com.isep.eleve.javaproject.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.isep.eleve.javaproject.model.User;

import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * User repository
 * @version V1.0
 * @author Chen YANG
 */
@Repository
public class UserRepository {
    // File path
    private static final String EXTERNAL_FILE_PATH = System.getProperty("user.home") + File.separator + "yourApp" + File.separator + "databaseUser.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    /**
     * Find all users
     * @return List<User>
     * @throws IOException
     */
    public List<User> findAll() throws IOException {
        File file = new File(EXTERNAL_FILE_PATH);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                return objectMapper.readValue(fis, TypeFactory.defaultInstance().constructCollectionType(List.class, User.class));
            }
        }
        return new ArrayList<>();
    }
    /**
     * Save user
     * @param user
     * @throws IOException
     */
    public void save(User user) throws IOException {
        List<User> users = findAll();
        users.add(user);
        writeUsersToFile(users);
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

    private void writeUsersToFile(List<User> users) throws IOException {
        File file = new File(EXTERNAL_FILE_PATH);
        File parentDir = file.getParentFile();
        if (!parentDir.exists() && !parentDir.mkdirs()) {
            throw new IOException("Failed to create directory: " + parentDir);
        }
        try (FileOutputStream fos = new FileOutputStream(file)) {
            objectMapper.writeValue(fos, users);
        }
    }
}
