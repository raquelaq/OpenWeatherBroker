package org.example.control;

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
        HotelManager hotelManager = new HotelManager();
        HotelDataPublisher publisher = new HotelDataPublisher();
        Thread thread = new Thread(publisher);
        try {
            hotelManager.processAndStoreHotelData();
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
