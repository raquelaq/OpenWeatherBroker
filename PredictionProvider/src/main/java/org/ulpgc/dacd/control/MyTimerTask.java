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
        WeatherStore weatherStore = new WeatherStore();
        Publisher publisher = new Publisher();
        Thread thread = new Thread(publisher);
        try {
            weatherStore.storeWeatherData();
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}