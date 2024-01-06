package org.example;

import com.google.gson.JsonObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteDataMart {

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS weather_data (" +
                "System_ts TEXT, " +
                "DateTime TEXT, " +
                "Source TEXT, " +
                "Temperature REAL, " +
                "Rain REAL, " +
                "Humidity REAL, " +
                "Clouds REAL, " +
                "WindSpeed REAL, " +
                "Latitude REAL, " +
                "Longitude REAL" +
                ");";

        try (Connection conn = DatabaseManager.getConnection("database.db");
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertWeatherData(JsonObject weatherData) {
        String sql = "INSERT INTO weather_data (System_ts, DateTime, Source, Temperature, Rain, " +
                "Humidity, Clouds, WindSpeed, Latitude, Longitude) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection("database.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, weatherData.get("System_ts").getAsString());
            pstmt.setString(2, weatherData.get("dateTime").getAsString());
            pstmt.setString(3, weatherData.get("ss").getAsString());
            pstmt.setDouble(4, weatherData.get("temperature").getAsDouble());
            pstmt.setDouble(5, weatherData.get("Rain").getAsDouble());
            pstmt.setDouble(6, weatherData.get("Humidity").getAsDouble());
            pstmt.setDouble(7, weatherData.get("Clouds").getAsDouble());
            pstmt.setDouble(8, weatherData.get("windSpeed").getAsDouble());
            pstmt.setDouble(9, weatherData.get("Latitude").getAsDouble());
            pstmt.setDouble(10, weatherData.get("Longitude").getAsDouble());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
