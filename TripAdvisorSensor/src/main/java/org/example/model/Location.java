package org.example.model;

public class Location {
    private final String name;
    private final double latitude;
    private final double longitude;

    public Location(String name, double lat, double lon) {
        this.name = name;
        this.latitude = lat;
        this.longitude = lon;
    }
    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    public String getName() {
        return name;
    }
}

