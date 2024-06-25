package org.example.model;

import java.time.Instant;
import java.time.LocalDate;

public class Hotel {
    private String ID;
    private String name;
    private double rating;
    private double averagePrice;
    private final String ss;
    private Location location;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Hotel(String name, String ID, Location location, String ss) {
        this.name = name;
        this.ID = ID;
        this.location = location;
        this.ss = ss;
    }

    public Hotel(String id, String name, double rating, String ss) {
        this.ID = id;
        this.name = name;
        this.rating = rating;
        this.ss = ss;
    }
    public String getID() {
        return ID;
    }
    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public Location getLocation() {
        return location;
    }

    public String getSs() {
        return ss;
    }

    public double getAveragePrice() {
        return averagePrice;
    }
    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public String buildJson(Instant ts) {
        return "{" +
                "\"name\": \"" + name + "\"," +
                "\"rating\": \"" + rating + "\"," +
                "\"latitude\": \"" + location.getLatitude() + "\"," +
                "\"longitude\": \"" + location.getLongitude() + "\"," +
                "\"ss\": \"" + ss + "\"," +
                "\"System_ts\": \"" + ts.toString() + "\"," +
                "\"IslandName\": \"" + location.getName() + "\"," +
                "\"CheckInDate\": \"" + checkInDate.toString() + "\","  +
                "\"CheckOutDate\": \"" + checkOutDate.toString() + "\","  +
                "\"HotelKey\": \"" + ID + "\"" +
                "}";
    }
}
