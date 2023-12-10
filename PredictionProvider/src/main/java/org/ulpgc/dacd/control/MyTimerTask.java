package org.ulpgc.dacd.control;

import java.sql.SQLException;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask {
    public void run() {
        try {
            executeTask();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void executeTask() throws SQLException {
        System.out.println("Executing task...");
        WeatherManager weatherManager = new WeatherManager();
        Publisher publisher = new Publisher();
        Thread thread = new Thread(publisher);
        try {
            weatherManager.processAndStoreWeatherData();
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}