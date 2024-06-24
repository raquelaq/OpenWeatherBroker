package org.ulpgc.dacd.control;

import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DirectoryCreator {
    public String createWeatherDirectory(Instant date) {
        String baseDirectory = "datalake/prediction.weather"; // Asegúrate de que los nombres de los directorios son correctos
        String subdirectory = "OpenWeatherMap/";

        return createSpecificDirectory(baseDirectory, subdirectory);
    }

    public String createHotelDirectory(Instant date) {
        String baseDirectory = "datalake/sensor.Hotel";
        String subdirectory = "TripAdvisor/";

        return createSpecificDirectory(baseDirectory, subdirectory);
    }

    private String createSpecificDirectory(String baseDirectory, String subdirectory) {
        File directory = new File(baseDirectory, subdirectory);
        if (!directory.exists()) {
            boolean isCreated = directory.mkdirs();
            System.out.println(isCreated ? "Directory created" : "Failed to create directory");
            return isCreated ? directory.getAbsolutePath() : null;
        } else {
            System.out.println("Directory already exists");
            return directory.getAbsolutePath();
        }
    }
}
