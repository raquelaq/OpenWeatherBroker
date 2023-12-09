package org.ulpgc.dacd.control;

import java.io.File;
import java.io.IOException;

public class DirectoryCreator {
    private String directoryPath;

    public String createDirectory() {

        String baseDirectory = "eventstore/prediction.Weather";
        String subdirectory1 = "ss";

        File baseDirectoryFile = new File(baseDirectory);

        if (!baseDirectoryFile.exists()) {
            if (baseDirectoryFile.mkdirs()) {
                System.out.println("Base directory created: " + baseDirectory);
            } else {
                System.out.println("Couldn't create base directory.");
                return null;
            }
        }

        File subdirectory2File = new File(baseDirectoryFile, subdirectory1);

        if (subdirectory2File.exists()) {
            System.out.println("The directory already exists: " + subdirectory2File.getAbsolutePath());
            directoryPath = subdirectory2File.getAbsolutePath();
            return directoryPath;
        }

        if (subdirectory2File.mkdirs()) {
            directoryPath = subdirectory2File.getAbsolutePath();
            System.out.println("Estructura de directorio creada exitosamente:");
            System.out.println(baseDirectory + "/" + subdirectory1);
            return directoryPath;
        } else {
            System.out.println("No se pudo crear la estructura de directorio.");
            return null;
        }
    }
}