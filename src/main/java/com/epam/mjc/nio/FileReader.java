package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Path path = file.toPath();

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            String name = null;
            String email = null;
            int age = 0;
            long phone = 0;

            while ((line = reader.readLine()) != null) {
                String[] keyvalue = line.split(": ");
                String key = keyvalue[0];
                String value = keyvalue[1];
                switch (key) {
                    case "Name":
                        name = value;
                        break;
                    case "Age":
                        age = Integer.parseInt(value);
                        break;
                    case "Phone":
                        phone = Long.parseLong(value);
                        break;
                    case "Email":
                        email = value;
                        break;
                    default:
                        break;
                }
            }
            return new Profile(name, age, email, phone);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Profile();
    }
}
