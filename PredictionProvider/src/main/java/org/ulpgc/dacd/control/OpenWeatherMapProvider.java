package org.ulpgc.dacd.control;

import org.ulpgc.dacd.model.Location;
import org.ulpgc.dacd.model.Weather;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.time.Instant;
import java.util.*;

public class OpenWeatherMapProvider {
    private final String API_KEY;
    private final String QUERY;
    public OpenWeatherMapProvider(String apiKey) {
        this.API_KEY = apiKey;
        this.QUERY = "https://api.openweathermap.org/data/2.5/forecast?lat=%s&lon=%s&appid=%s&units=metrics";
    }
    WeatherDataExtractor weatherDataExtractor = new WeatherDataExtractor();

    public List<Weather> buildWeather(Location coordinates, String islandName) throws IOException {
        Instant instant = Instant.now();
        List<Weather> weatherList = new ArrayList<>();
        JsonObject json = generate(coordinates);
        JsonArray jsonArray = (JsonArray) json.get("list");

        for (JsonElement jsonElement : jsonArray) {
            JsonObject jsonObject = (JsonObject) jsonElement;
            String datetime = jsonObject.get("dt_txt").toString().substring(12, 20);
            if (datetime.equals("12:00:00")) {
                buildWeatherList(instant, jsonObject, coordinates, weatherList, islandName);
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

    private void buildWeatherList(Instant instant, JsonObject jsonObject, Location location, List<Weather> weatherList, String islandName) {
        double precipitation = weatherDataExtractor.getPrecipitation(jsonObject);
        double temperature = weatherDataExtractor.getTemperature(jsonObject);
        double humidity = weatherDataExtractor.getHumidity(jsonObject);
        double clouds = weatherDataExtractor.getClouds(jsonObject);
        double windSpeed = weatherDataExtractor.getWindSpeed(jsonObject);
        Instant forecastTime = weatherDataExtractor.getForecastTime(jsonObject);
        String ss = "OpenWeatherMap";
        weatherList.add(new Weather(instant, ss, forecastTime, temperature, precipitation, humidity, clouds, windSpeed, location, islandName));
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


