package org.ulpgc.dacd.control;

import java.io.FileWriter;
import java.io.IOException;

public class FileAdministrator {

    public void write(String path, String content) {
        try {
            FileWriter writer = new FileWriter(path, true);
            writer.write(content);
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
