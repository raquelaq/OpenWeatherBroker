package org.ulpgc.dacd.model;

import java.time.Instant;

public class Weather {
    private Instant ts;
    private Instant predictionTime;
    private String ss;  // Mirar donde debo usarlo y añadirlo o no a los objetos tipo Weather
    private double temperature;
    private double rain;
    private double humidity;
    private double clouds;
    private double windSpeed;
    private Location location;

    public Weather(Instant ts, String ss, Instant predictionTime, double temperature, double rain, double humidity, double clouds, double windSpeed, Location location) {
        this.ts = ts;
        this.ss = ss;
        this.predictionTime = predictionTime;
        this.temperature = temperature;
        this.rain = rain;
        this.humidity = humidity;
        this.clouds = clouds;
        this.windSpeed = windSpeed;
        this.location = location;
    }


    public Instant getTs() {
        return ts;
    }

    public Instant getPredictionTime() {
        return predictionTime;
    }
    public String getSs() { return ss; }

    public double getTemperature() {
        return temperature;
    }

    public double getRain() {
        return rain;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getClouds() {
        return clouds;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return String.format("DateTime: %s, Temperature: %.2f °C, Rain: %.2f mm, Humidity: %.2f%%, Clouds: %.2f%%, WindSpeed: %.2f m/s",
                predictionTime, temperature, rain, humidity, clouds, windSpeed);
    }

    public String buildJson() {
        return "{" +
                "\"System_ts\": " + "\"" + ts.toString() + "\"," +
                "\"dateTime\": " + "\"" + predictionTime.toString() + "\"," +
                "\"temperature\": " + temperature + "," +
                "\"Rain\": " + rain  + "," +
                "\"Humidity\": " + humidity + "," +
                "\"Clouds\": " + clouds + "," +
                "\"windSpeed\": " + windSpeed + "," +
                "\"Latitude\": " + location.getLatitude() + "," +
                "\"Longitude\": " + location.getLongitude() +
                "}";
    }


}
