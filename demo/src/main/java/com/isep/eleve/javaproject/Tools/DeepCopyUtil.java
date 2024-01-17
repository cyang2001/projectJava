package com.isep.eleve.javaproject.Tools;

import java.io.*;

public class DeepCopyUtil {

    public static <T extends Serializable> T deepCopy(T object) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos)) {

            oos.writeObject(object);

            try (ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais)) {

                return (T) ois.readObject();
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e); // Or handle it as you see fit
        }
    }
}
