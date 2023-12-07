package org.ulpgc.dacd.control;

import java.io.File;
import java.io.IOException;

public class FileCreator {
    private static final String baseDirectory = "EventStoreBuilder/src/main/resources/";

    public static void createNewFile(String filePath) {
        File fichero = new File(filePath);

        try {
            if (fichero.createNewFile()) {
                System.out.println("El fichero se ha creado correctamente en: " + filePath);
                System.out.println(fichero.getAbsolutePath());
            } else {
                System.out.println("No ha podido ser creado el fichero en: " + filePath);
                System.out.println(fichero.getAbsolutePath());
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createNewFile(baseDirectory + new DateProvider().provide() + ".events");
    }
}
