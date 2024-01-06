package org.example.control;

public class SkyScannerProvider {
    private final String API_KEY;
    private final String QUERY;

    public SkyScannerProvider(String apiKey) {
        this.API_KEY = apiKey;
        this.QUERY = "https://partners.api.skyscanner.net/apiservices/v3/flights/live/search/create";
    }



}
