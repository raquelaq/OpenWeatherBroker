package org.ulpgc.dacd.control;

import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DirectoryCreator {
    public String createDirectory(Instant date) {
        LocalDate localDate = date.atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = formatter.format(localDate);

        String baseDirectory = "datalake/prediction.Weather";
        String subdirectory = "OpenWeatherMap/";

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
