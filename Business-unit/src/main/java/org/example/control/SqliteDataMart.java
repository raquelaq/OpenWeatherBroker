package org.example.control;

import com.google.gson.JsonObject;
import org.example.model.Location;
import org.example.model.Weather;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
            String formattedtSystemTS = formatInstant(weatherData.get("System_ts").getAsString());
            String formattedDatetime = formatInstant(weatherData.get("dateTime").getAsString());
            pstmt.setString(1, formattedtSystemTS);
            pstmt.setString(2, formattedDatetime);
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

        String sql = String.format("UPDATE " + islandName + " SET System_ts = ?, DateTime = ?, Temperature = ?, Rain = ?, WindSpeed = ?, " +
                "Humidity = ?, Clouds = ?, Latitude = ?, Longitude = ? " +
                "WHERE DateTime = ?");

        DatabaseManager databaseManager = new DatabaseManager();
        try (Connection conn = databaseManager.getConnection("database.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String formattedtSystemTS = formatInstant(weatherData.get("System_ts").getAsString());
            String formattedDatetime = formatInstant(weatherData.get("dateTime").getAsString());
            pstmt.setString(1, formattedtSystemTS);
            pstmt.setDouble(2, weatherData.get("temperature").getAsDouble());
            pstmt.setDouble(3, weatherData.get("Rain").getAsDouble());
            pstmt.setDouble(4, weatherData.get("windSpeed").getAsDouble());
            pstmt.setDouble(5, weatherData.get("Humidity").getAsDouble());
            pstmt.setDouble(6, weatherData.get("Clouds").getAsDouble());
            pstmt.setDouble(7, weatherData.get("Latitude").getAsDouble());
            pstmt.setDouble(8, weatherData.get("Longitude").getAsDouble());
            pstmt.setString(9, formattedDatetime);
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
                "CheckInDate TEXT UNIQUE, " +
                "CheckOutDate TEXT," +
                "HotelKey TEXT" +
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
            String formattedtSystemTS = formatInstant(hotelData.get("System_ts").getAsString());
            pstmt.setString(1, hotelData.get("name").getAsString());
            pstmt.setDouble(2, hotelData.get("rating").getAsDouble());
            pstmt.setDouble(3, hotelData.get("latitude").getAsDouble());
            pstmt.setDouble(4, hotelData.get("longitude").getAsDouble());
            pstmt.setString(5, hotelData.get("ss").getAsString());
            pstmt.setString(6, formattedtSystemTS);
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

        String sql = String.format("UPDATE " + islandName + " SET System_ts = ?, Name = ?, Rating = ?, Latitude = ?, Longitude = ?, " +
                "Source = ?, CheckInDate = ?, CheckOutDate = ?, HotelKey = ? " +
                "WHERE CheckInDate = ?");

        DatabaseManager databaseManager = new DatabaseManager();
        try (Connection conn = databaseManager.getConnection("database.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String formattedtSystemTS = formatInstant(hotelData.get("System_ts").getAsString());
            pstmt.setString(1, hotelData.get("name").getAsString());
            pstmt.setDouble(2, hotelData.get("rating").getAsDouble());
            pstmt.setDouble(3, hotelData.get("latitude").getAsDouble());
            pstmt.setDouble(4, hotelData.get("longitude").getAsDouble());
            pstmt.setString(5, hotelData.get("ss").getAsString());
            pstmt.setString(6, formattedtSystemTS);
            pstmt.setString(7, hotelData.get("CheckOutDate").getAsString());
            pstmt.setString(8, hotelData.get("CheckInDate").getAsString());
            pstmt.setString(9, hotelData.get("HotelKey").getAsString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Weather> selectWeatherData(String tablename) {
        List<Weather> weatherList = new ArrayList<>();
        String selectSql = String.format("SELECT * FROM \"%s\"", tablename);
        DatabaseManager databaseManager = new DatabaseManager();
        try (Connection conn = databaseManager.getConnection("database.db");
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSql)) {
            while (resultSet.next()) {

                Instant ts = resultSet.getTimestamp("System_ts").toInstant();
                Instant predictionTime = resultSet.getTimestamp("dateTime").toInstant();
                double temperature = resultSet.getDouble("Temperature");
                double rain = resultSet.getDouble("Rain");
                double windSpeed = resultSet.getDouble("WindSpeed");
                double humidity = resultSet.getDouble("Humidity");
                double clouds = resultSet.getDouble("Clouds");
                double latitude = resultSet.getDouble("Latitude");
                double longitude = resultSet.getDouble("Longitude");
                Location location = new Location(latitude, longitude);
                Weather weather = new Weather(ts, predictionTime, temperature, rain, humidity, clouds, windSpeed, location);
                weatherList.add(weather);
            }
        } catch (SQLException e) {
            System.out.println("Error executing select query: " + e.getMessage());
        }
        return weatherList;
    }
    private String formatInstant(String isoDateTime) {
        Instant instant = Instant.parse(isoDateTime);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }

    private String formatInstantDate(String isoDateTime) {
        Instant instant = Instant.parse(isoDateTime);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDateTime.format(formatter);
    }
}
