package org.example.control;

import com.google.gson.Gson;
import org.example.model.Location;
import org.example.model.Weather;

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
                            "prediction-provider",
                            weather.getPredictionTime(),
                            weather.getTemperature(),
                            weather.getRain(),
                            weather.getHumidity(),
                            weather.getClouds(),
                            weather.getWindSpeed(),
                            location
                    );

                    // Convertir WeatherEvent a JSON
                    String jsonWeatherEvent = convertToJson(weatherEvent);

                    // Enviar el JSON al bróker o realizar otras operaciones necesarias
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
        Gson gson = new Gson();
        return gson.toJson(weatherEvent);
    }
}
