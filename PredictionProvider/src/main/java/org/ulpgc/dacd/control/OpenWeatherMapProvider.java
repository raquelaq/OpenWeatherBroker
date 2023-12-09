package org.ulpgc.dacd.control;

import org.ulpgc.dacd.model.Location;
import org.ulpgc.dacd.model.Weather;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.*;

public class OpenWeatherMapProvider {
    private final String API_KEY = "96401e11b3d4fbefb6ff23c1a69fde24";
    private final String QUERY = "https://api.openweathermap.org/data/2.5/forecast?lat=%s&lon=%s&appid=%s&units=metrics";

    public List<Weather> buildWeather(Location coordinates) throws IOException {
        Instant instant = Instant.now();
        List<Weather> weatherList = new ArrayList<>();

        JsonObject json = generate(coordinates);
        JsonArray jsonArray = (JsonArray) json.get("list");

        for (JsonElement jsonElement : jsonArray) {
            JsonObject jsonObject = (JsonObject) jsonElement;
            String datetime = jsonObject.get("dt_txt").toString().substring(12, 20);
            if (datetime.equals("12:00:00")) {
                buildWeatherList(instant, jsonObject, coordinates, weatherList);
            }
        }
        return weatherList;
    }

    private JsonObject generate(Location coordinates) throws IOException {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        String response = responseBuilder.response(coordinates, QUERY, API_KEY);
        JsonParser parser = new JsonParser();
        return (JsonObject) parser.parse(response);
    }

    private void buildWeatherList(Instant instant, JsonObject jsonObject, Location location, List<Weather> weatherList) {
        double precipitation = getPrecipitation(jsonObject);
        double temperature = getTemperature(jsonObject);
        double humidity = getHumidity(jsonObject);
        double clouds = getClouds(jsonObject);
        double windSpeed = getWindSpeed(jsonObject);
        Instant forecastTime = getForecastTime(jsonObject);
        String ss = "prediction-provider";
        weatherList.add(new Weather(instant, ss, forecastTime, temperature, precipitation, humidity, clouds, windSpeed, location));
    }

    private double getPrecipitation(JsonObject jsonObject) {
        return jsonObject.get("pop").getAsDouble() * 100;
    }

    private double getTemperature(JsonObject jsonObject) {
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

    private double getHumidity(JsonObject jsonObject) {
        JsonObject main = jsonObject.get("main").getAsJsonObject();
        return main.get("humidity").getAsDouble();
    }

    private double getClouds(JsonObject jsonObject) {
        JsonObject clouds = jsonObject.get("clouds").getAsJsonObject();
        return clouds.get("all").getAsDouble();
    }

    private double getWindSpeed(JsonObject jsonObject) {
        JsonObject wind = jsonObject.get("wind").getAsJsonObject();
        return wind.get("speed").getAsDouble();
    }

    private Instant getForecastTime(JsonObject jsonObject) {
        Instant forecastTime = Instant.ofEpochSecond(jsonObject.getAsJsonObject().get("dt").getAsLong());
        return forecastTime;
    }

    public Map<String, Location> createMap() {
        Map<String, Location> map = new HashMap<>();
        map.put("Gran Canaria", new Location("Galdar", 28.14701, -15.6502));
        map.put("Tenerife", new Location("Garachico", 28.373686, -16.7640491));
        map.put("Fuerteventura", new Location("Antigua", 28.4160163, -14.0118473));
        map.put("Lanzarote", new Location("Yaiza", 28.9567800, -13.7653500));
        map.put("La Palma", new Location("Barlovento", 28.816667, -17.766667));
        map.put("El Hierro", new Location("Valverde", 27.809685, -17.915147));
        map.put("La Gomera", new Location("Vallehermoso", 28.179868, -17.264683));
        map.put("La Graciosa", new Location("Caleta del Sebo", 29.231389, -13.501944));
        return map;
    }
}
