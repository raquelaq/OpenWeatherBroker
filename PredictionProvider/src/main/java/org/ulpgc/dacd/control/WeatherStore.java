package org.ulpgc.dacd.control;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.ulpgc.dacd.model.Location;
import org.ulpgc.dacd.model.Weather;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class WeatherStore {
    private final OpenWeatherMapProvider openWeatherMapProvider;

    public WeatherStore() {
        this.openWeatherMapProvider = new OpenWeatherMapProvider();
    }

    public void storeWeatherData() {
        Map<String, Location> locationMap = openWeatherMapProvider.createMap();
        try {
            for (Map.Entry<String, Location> entry : locationMap.entrySet()) {
                String tablename = entry.getKey();
                Location location = entry.getValue();
                List<Weather> weatherList = openWeatherMapProvider.buildWeather(location);
                for (Weather weather : weatherList) {
                    Weather weatherEvent = new Weather(
                            weather.getTs(),
                            "OpenWeatherMap",
                            weather.getPredictionTime(),
                            weather.getTemperature(),
                            weather.getRain(),
                            weather.getHumidity(),
                            weather.getClouds(),
                            weather.getWindSpeed(),
                            location
                    );

                    String jsonWeatherEvent = convertToJson(weatherEvent);
                }
            }
            System.out.println("Weather data has been processed");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<Weather> getWeatherData() {
        List<Weather> weatherList = new ArrayList<>();
        Map<String, Location> locationMap = openWeatherMapProvider.createMap();
        try {
            for (Map.Entry<String, Location> entry : locationMap.entrySet()) {
                Location location = entry.getValue();
                List<Weather> currentWeatherList = openWeatherMapProvider.buildWeather(location);
                weatherList.addAll(currentWeatherList);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return weatherList;
    }

    private String convertToJson(Weather weatherEvent) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(weatherEvent);
    }
}