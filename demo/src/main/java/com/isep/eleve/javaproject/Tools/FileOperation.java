package com.isep.eleve.javaproject.Tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
@Component
public class FileOperation {

    private final ObjectMapper objectMapper;

    public FileOperation(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> List<T> readListFromFile(String filePath, Class<T> clazz) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                return objectMapper.readValue(fis, TypeFactory.defaultInstance().constructCollectionType(List.class, clazz));
            }
        }
        return new ArrayList<>();
    }

    public <T> void writeListToFile(String filePath, List<T> list) throws IOException {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (!parentDir.exists() && !parentDir.mkdirs()) {
            throw new IOException("Failed to create directory: " + parentDir);
        }
        try (FileOutputStream fos = new FileOutputStream(file)) {
            objectMapper.writeValue(fos, list);
        }
    }
    public <T> void writeObjectToFile(String filePath, T object) throws IOException{
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (!parentDir.exists() && !parentDir.mkdirs()) {
            throw new IOException("Failed to create directory: " + parentDir);
        }
        try (FileOutputStream fos = new FileOutputStream(file)) {
            objectMapper.writeValue(fos, object);
        }
    }
}

