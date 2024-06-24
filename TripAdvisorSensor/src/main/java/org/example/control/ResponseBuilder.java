package org.example.control;

import org.example.model.Location;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ResponseBuilder {
    public String response(String urlString) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
        connection.setRequestMethod("GET"); // Ensure method is GET
        connection.connect(); // Connect to the server

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP GET request failed with response code " + responseCode);
        }

        InputStream inputStream = connection.getInputStream();
        StringBuilder builder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            int c;
            while ((c = reader.read()) != -1) {
                builder.append((char) c);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        // Imprimir la respuesta
        //System.out.println("BUIELDER: " + builder);
        return builder.toString();
    }
}