package org.example;

import com.google.gson.JsonObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteDataMart {

    public void createWeatherTable(String islandName) {
        String tableName = islandName.replaceAll("\\s+","_");
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "System_ts TEXT, " +
                "DateTime TEXT UNIQUE, " +
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
        String islandName = weatherData.get("IslandName").getAsString().replaceAll("\\s+", "_");
        createWeatherTable(islandName);

        String sql = String.format("INSERT INTO " + islandName + " (System_ts, DateTime, Source, Temperature, Rain, " +
                "Humidity, Clouds, WindSpeed, Latitude, Longitude) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        DatabaseManager databaseManager = new DatabaseManager();
        try (Connection conn = databaseManager.getConnection("database.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
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
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateWeatherData(JsonObject weatherData) {
        String islandName = weatherData.get("IslandName").getAsString().replaceAll("\\s+", "_");

        String sql = String.format("UPDATE " + islandName + " SET System_ts = ?, Temperature = ?, Rain = ?, WindSpeed = ?, " +
                "Humidity = ?, Clouds = ?, Latitude = ?, Longitude = ?" +
                "WHERE DateTime = ?");

        DatabaseManager databaseManager = new DatabaseManager();
        try (Connection conn = databaseManager.getConnection("database.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, weatherData.get("System_ts").getAsString());
            pstmt.setDouble(2, weatherData.get("temperature").getAsDouble());
            pstmt.setDouble(3, weatherData.get("Rain").getAsDouble());
            pstmt.setDouble(4, weatherData.get("windSpeed").getAsDouble());
            pstmt.setDouble(5, weatherData.get("Humidity").getAsDouble());
            pstmt.setDouble(6, weatherData.get("Clouds").getAsDouble());
            pstmt.setDouble(7, weatherData.get("Latitude").getAsDouble());
            pstmt.setDouble(8, weatherData.get("Longitude").getAsDouble());
            pstmt.setString(9, weatherData.get("dateTime").getAsString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createHotelTable(String islandName) {
        String tableName = islandName.replaceAll("\\s+","_");
        System.out.println(tableName);

        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(" +
                "HotelID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Name TEXT, " +
                "Rating REAL, " +
                "Latitude REAL, " +
                "Longitude REAL, " +
                "Source TEXT, " +
                "System_ts TEXT, " +
                "CheckInDate TEXT, " +
                "CheckOutDate TEXT," +
                "HotelKey TEXT UNIQUE" +
                ");";

        try (Connection conn = DatabaseManager.getConnection("database.db");
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertHotelData(JsonObject hotelData) {
        String islandName = hotelData.get("IslandName").getAsString().replaceAll("\\s+", "_") + "_hotels";
        createHotelTable(islandName);

        String sql = String.format("INSERT INTO " + islandName + " (Name, Rating, Latitude, Longitude, Source, System_ts, CheckInDate, CheckOutDate, HotelKey)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");


        DatabaseManager databaseManager = new DatabaseManager();
        try (Connection conn = databaseManager.getConnection("database.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            pstmt.setString(1, hotelData.get("name").getAsString());
            pstmt.setDouble(2, hotelData.get("rating").getAsDouble());
            pstmt.setDouble(3, hotelData.get("latitude").getAsDouble());
            pstmt.setDouble(4, hotelData.get("longitude").getAsDouble());
            pstmt.setString(5, hotelData.get("ss").getAsString());
            pstmt.setString(6, hotelData.get("System_ts").getAsString());
            pstmt.setString(7, hotelData.get("CheckInDate").getAsString());
            pstmt.setString(8, hotelData.get("CheckOutDate").getAsString());
            pstmt.setString(9, hotelData.get("HotelKey").getAsString());
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateHotelData(JsonObject hotelData) {
        String islandName = hotelData.get("IslandName").getAsString().replaceAll("\\s+", "_") + "_hotels";

        String sql = String.format("UPDATE " + islandName + " SET Name = ?, Rating = ?, Latitude = ?, Longitude = ?, " +
                "Source = ?, System_ts = ?, CheckInDate = ?, CheckOutDate = ?" +
                "WHERE HotelKey = ?");

        DatabaseManager databaseManager = new DatabaseManager();
        try (Connection conn = databaseManager.getConnection("database.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, hotelData.get("name").getAsString());
            pstmt.setDouble(2, hotelData.get("rating").getAsDouble());
            pstmt.setDouble(3, hotelData.get("latitude").getAsDouble());
            pstmt.setDouble(4, hotelData.get("longitude").getAsDouble());
            pstmt.setString(5, hotelData.get("ss").getAsString());
            pstmt.setString(6, hotelData.get("System_ts").getAsString());
            pstmt.setString(7, hotelData.get("CheckOutDate").getAsString());
            pstmt.setString(8, hotelData.get("CheckInDate").getAsString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
