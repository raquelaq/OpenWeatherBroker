package org.example.control;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.example.model.Hotel;

import java.io.IOException;
import java.time.LocalDate;;
import java.util.*;

public class TripAdvisorProvider {
    private final String BASE_URL = "https://data.xotelo.com/api/rates";
    private ResponseBuilder responseBuilder = new ResponseBuilder();

    public List<Hotel> fetchHotelData(List<Hotel> hotels) throws IOException {
        LocalDate checkInDate = LocalDate.now().plusDays(1);
        LocalDate checkOutDate = checkInDate.plusDays(5);
        List<Hotel> updatedHotels = new ArrayList<>();

        for (Hotel hotel : hotels) {
            String url = String.format("%s?hotel_key=%s&chk_in=%s&chk_out=%s",
                    BASE_URL, hotel.getID(), checkInDate, checkOutDate);
            JsonObject json = fetchJsonFromUrl(url);
            //System.out.println("JSON Response: " + json);
            if (json != null && json.getAsJsonObject("result").has("rates")) {
                JsonArray rates = json.getAsJsonObject("result").getAsJsonArray("rates");
                //System.out.println("Rates: " + rates);
                processHotelRates(hotel, rates);
                hotel.setCheckInDate(checkInDate);
                hotel.setCheckOutDate(checkOutDate);
                updatedHotels.add(hotel);
            } else {
                //System.out.println("No rates found for hotel: " + hotel.getID());
            }
        }
        return updatedHotels;
    }

    private JsonObject fetchJsonFromUrl(String url) throws IOException {
        String response = responseBuilder.response(url);
        JsonParser parser = new JsonParser();
        return parser.parse(response).getAsJsonObject();
    }

    private void processHotelRates(Hotel hotel, JsonArray rates) {
        if (rates.size() == 0) {
            //System.out.println("No rates available for " + hotel.getName());
            return;
        }

        double totalRate = 0;
        for (JsonElement element : rates) {
            JsonObject rateObject = element.getAsJsonObject();
            double rate = rateObject.get("rate").getAsDouble();
            totalRate += rate;
        }
        double averageRate = totalRate / rates.size();
        hotel.setAveragePrice(averageRate);
    }
}
