package org.ulpgc.dacd.control;

import com.google.gson.JsonObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;

public class WeatherDataExtractor {
    public double getPrecipitation(JsonObject jsonObject) {
        return jsonObject.get("pop").getAsDouble() * 100;
    }

    public double getTemperature(JsonObject jsonObject) {
        JsonObject main = jsonObject.get("main").getAsJsonObject();
        double temperature = main.get("temp").getAsDouble();
        return round(kelvinToCelcius(temperature), 2);
    }

    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bigDecimal = new BigDecimal(Double.toString(value));
        return bigDecimal.setScale(places, RoundingMode.HALF_UP).doubleValue();
    }

    private double kelvinToCelcius(double temperature) {
        return temperature - 273.15;
    }

    public double getHumidity(JsonObject jsonObject) {
        JsonObject main = jsonObject.get("main").getAsJsonObject();
        return main.get("humidity").getAsDouble();
    }

    public double getClouds(JsonObject jsonObject) {
        JsonObject clouds = jsonObject.get("clouds").getAsJsonObject();
        return clouds.get("all").getAsDouble();
    }

    public double getWindSpeed(JsonObject jsonObject) {
        JsonObject wind = jsonObject.get("wind").getAsJsonObject();
        return wind.get("speed").getAsDouble();
    }

    public Instant getForecastTime(JsonObject jsonObject) {
        Instant forecastTime = Instant.ofEpochSecond(jsonObject.getAsJsonObject().get("dt").getAsLong());
        return forecastTime;
    }
}
