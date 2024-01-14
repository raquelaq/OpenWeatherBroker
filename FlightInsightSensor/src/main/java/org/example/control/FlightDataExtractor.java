package org.example.control;

import com.google.gson.JsonObject;
import org.example.model.Cabin;
import org.example.model.Date;

import java.time.LocalTime;

public class FlightDataExtractor {
    public String getOrigin(JsonObject jsonObject) {
        JsonObject query = jsonObject.get("query").getAsJsonObject();
        JsonObject queryLegs = query.get("query_legs").getAsJsonObject();
        JsonObject originPlaceId = queryLegs.get("origin_place_id").getAsJsonObject();
        return originPlaceId.get("iata").getAsString();

    }

    public String getDestination(JsonObject jsonObject) {
        JsonObject query = jsonObject.get("query").getAsJsonObject();
        JsonObject queryLegs = query.get("query_legs").getAsJsonObject();
        JsonObject destinationPlaceId = queryLegs.get("destination_place_id").getAsJsonObject();
        return destinationPlaceId.get("iata").getAsString();
    }

    public Date getDepartureDate(JsonObject jsonObject) {
        JsonObject query = jsonObject.get("query").getAsJsonObject();
        JsonObject queryLegs = query.get("query_legs").getAsJsonObject();
        JsonObject departureDateObj = queryLegs.get("date").getAsJsonObject();

        int year = departureDateObj.get("year").getAsInt();
        int month = departureDateObj.get("month").getAsInt();
        int day = departureDateObj.get("day").getAsInt();

        return new Date(year, month, day);
    }

    public LocalTime getDepartureTime(JsonObject jsonObject) {
        JsonObject query = jsonObject.get("query").getAsJsonObject();
        JsonObject queryLegs = query.get("query_legs").getAsJsonObject();
        JsonObject departureDateTime = queryLegs.get("date").getAsJsonObject();

        int hour = departureDateTime.get("hour").getAsInt();
        int minute = departureDateTime.get("minute").getAsInt();
        int second = departureDateTime.get("second").getAsInt();

        return LocalTime.of(hour, minute, second);
    }

    public int getAdults(JsonObject jsonObject) {
        JsonObject query = jsonObject.get("query").getAsJsonObject();
        return query.get("adults").getAsInt();
    }

    public Cabin getCabin(JsonObject jsonObject) {
        JsonObject query = jsonObject.get("query").getAsJsonObject();
        String cabinClassStr = query.get("cabin_class").getAsString();
        return Cabin.fromString(cabinClassStr);
    }

}
