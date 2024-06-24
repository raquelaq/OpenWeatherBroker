package org.example.control;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.model.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelManager {
    private TripAdvisorProvider tripAdvisorProvider = new TripAdvisorProvider();
    public void processAndStoreHotelData() {
        List<Hotel> hotels = HotelRecommender.recommendedHotels();
        try {
            List<Hotel> updatedHotels = tripAdvisorProvider.fetchHotelData(hotels);
            for (Hotel hotel : updatedHotels) {
                Hotel hotelEvent = new Hotel(
                        hotel.getID(),
                        hotel.getName(),
                        hotel.getRating(),
                        "TripAdvisor"
                );
                convertToJson(hotelEvent);
            }
            System.out.println("Hotel data has been processed and stored");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<Hotel> getHotelData() {
        List<Hotel> hotels = HotelRecommender.recommendedHotels();
        try {
            List<Hotel> updatedHotels = tripAdvisorProvider.fetchHotelData(hotels);
            return updatedHotels; // Verifica que esta lista no esté vacía
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return new ArrayList<>(); // Este catch podría estar capturando errores y devolviendo una lista vacía
        }
    }

    private void convertToJson(Hotel hotel) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String hotelJson = gson.toJson(hotel);
        System.out.println(hotelJson);
    }
}
