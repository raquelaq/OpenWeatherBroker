package org.example.control;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.example.model.Date;
import org.example.model.Cabin;
import org.example.model.Flight;
import org.example.model.ResponseBody;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SkyScannerProvider {
    private String API_KEY;
    private final String QUERY;

    public SkyScannerProvider(String apiKey) {
        this.API_KEY = apiKey;
        this.QUERY = "https://partners.api.skyscanner.net/apiservices/v3/flights/live/search/create";
    }

    public List<Flight> searchFlights(ResponseBody searchCriteria) throws IOException {
        List<Flight> flightList = new ArrayList<>();
        ResponseBuilder responseBuilder = new ResponseBuilder();
        String jsonBody = responseBuilder.responseBody(searchCriteria);
        String response = responseBuilder.response(QUERY, API_KEY, jsonBody);
        JsonObject jsonResponse = JsonParser.parseString(response).getAsJsonObject();
        JsonArray flightsArray = jsonResponse.getAsJsonArray("itineraries");

        for (JsonElement flightElement : flightsArray) {
            JsonObject flightObject = flightElement.getAsJsonObject();
            Flight flight = extraxtFlight(flightObject);
            flightList.add(flight);
        }
        return flightList;
    }

    FlightDataExtractor flightDataExtractor = new FlightDataExtractor();
    private Flight extraxtFlight(JsonObject jsonObject) {
        String origin = flightDataExtractor.getOrigin(jsonObject);
        String destination = flightDataExtractor.getDestination(jsonObject);
        LocalTime departureTime = flightDataExtractor.getDepartureTime(jsonObject);
        Date departureDate = flightDataExtractor.getDepartureDate(jsonObject);
        int adults = flightDataExtractor.getAdults(jsonObject);
        Cabin cabin = flightDataExtractor.getCabin(jsonObject);

        return new Flight(departureDate, departureTime, origin, destination, adults, cabin);
    }
}
