package org.example.control;

import org.example.model.Hotel;
import org.example.model.Location;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelDataSelector {
    public List<Hotel> selectHotelData(String tableName) {
        List<Hotel> hotelList = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName;
        try (Connection conn = DatabaseManager.getConnection("database.db");
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {
            while (resultSet.next()) {
                String id = resultSet.getString("HotelID");
                String name = resultSet.getString("Name");
                double rating = resultSet.getDouble("Rating");
                double latitude = resultSet.getDouble("Latitude");
                double longitude = resultSet.getDouble("Longitude");
                String ss = resultSet.getString("Source");
                LocalDate checkInDate = resultSet.getDate("CheckInDate").toLocalDate();
                LocalDate checkOutDate = resultSet.getDate("CheckOutDate").toLocalDate();
                Timestamp timestamp = resultSet.getTimestamp("System_ts");
                Instant systemTs = timestamp.toInstant();


                Location location = new Location(latitude, longitude);

                Hotel hotel = new Hotel(id, name, rating, ss, systemTs);
                hotel.setCheckInDate(checkInDate);
                hotel.setCheckOutDate(checkOutDate);
                hotel.setLocation(location);

                hotelList.add(hotel);
            }
        } catch (SQLException e) {
            System.out.println("Error executing hotel query: " + e.getMessage());
        }
        return hotelList;
    }
}