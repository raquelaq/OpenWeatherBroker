package org.example.control;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.model.Cabin;
import org.example.model.Date;
import org.example.model.Flight;
import org.example.model.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Prueba {
    private final SkyScannerProvider skyScannerProvider;

    public Prueba(String apiKey) {
        this.skyScannerProvider = new SkyScannerProvider(apiKey);
    }

    public void processAndStoreFlightData() {
        List<ResponseBody> searchCriteriaList = createSearchCriteriaList();
        try {
            for (ResponseBody searchCriteria : searchCriteriaList) {
                List<Flight> flightList = skyScannerProvider.searchFlights(searchCriteria);
                for (Flight flight : flightList) {
                    convertToJson(flight);
                }
            }
            System.out.println("Flight data has been processed");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<Flight> getFlightData() {
        List<Flight> allFlights = new ArrayList<>();
        List<ResponseBody> searchCriteriaList = createSearchCriteriaList();
        try {
            for (ResponseBody searchCriteria : searchCriteriaList) {
                List<Flight> flightList = skyScannerProvider.searchFlights(searchCriteria);
                allFlights.addAll(flightList);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return allFlights;
    }

    private void convertToJson(Flight flight) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String flightJson = gson.toJson(flight);
        System.out.println(flightJson);
    }

    private List<ResponseBody> createSearchCriteriaList() {
        List<ResponseBody> searchCriteriaList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter flight search criteria (or type 'exit' to finish):");
            System.out.print("Origin: ");
            String origin = scanner.nextLine();

            if (origin.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("Destination: ");
            String destination = scanner.nextLine();

            System.out.print("Year: ");
            int year = scanner.nextInt();

            System.out.print("Month: ");
            int month = scanner.nextInt();

            System.out.print("Day: ");
            int day = scanner.nextInt();

            System.out.print("Number of adults: ");
            int adults = scanner.nextInt();

            scanner.nextLine(); // Consumir la nueva línea pendiente

            Cabin cabin;
            do {
                System.out.print("Cabin class (Economy, PremiumEconomy, Business, First): ");
                String cabinStr = scanner.nextLine().toUpperCase();
                cabin = Cabin.fromString(cabinStr);
            } while (cabin == Cabin.CABIN_CLASS_UNESPECIFIED);

            Date date = new Date(year, month, day);
            ResponseBody searchCriteria = new ResponseBody(origin, destination, date, adults, cabin.name());
            searchCriteriaList.add(searchCriteria);
        }

        return searchCriteriaList;
    }
}

/*private final SkyScannerProvider skyScannerProvider;

    public FlightManager(String apiKey) {
        this.skyScannerProvider = new SkyScannerProvider(apiKey);
    }

    public void processAndStoreFlightData() throws IOException {
        List<ResponseBody> searchCriteriaList = createSearchCriteriaList();
        try {
            for (ResponseBody searchCriteria : searchCriteriaList) {
                List<Flight> flightList = skyScannerProvider.searchFlights(searchCriteria);
                for (Flight flight : flightList) {
                    convertToJson(flight);
                }
            }
            System.out.println("Flight data has been processed");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<Flight> getFlightData() throws IOException {
        List<Flight> allFlights = new ArrayList<>();
        List<ResponseBody> searchCriteriaList = createSearchCriteriaList();
        try {
            for (ResponseBody searchCriteria : searchCriteriaList) {
                List<Flight> flightList = skyScannerProvider.searchFlights(searchCriteria);
                allFlights.addAll(flightList);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return allFlights;
    }

    private void convertToJson(Flight flight) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String flightJson = gson.toJson(flight);
        System.out.println(flightJson);
    }

    private List<ResponseBody> createSearchCriteriaList() throws IOException {
        List<ResponseBody> searchCriteriaList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("Enter flight search criteria (or type 'exit' to finish):");
            System.out.print("Origin: ");
            String origin = reader.readLine();

            if (origin.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("Destination: ");
            String destination = reader.readLine();

            System.out.print("Year: ");
            int year = Integer.parseInt(reader.readLine());

            System.out.print("Month: ");
            int month = Integer.parseInt(reader.readLine());

            System.out.print("Day: ");
            int day = Integer.parseInt(reader.readLine());

            System.out.print("Number of adults: ");
            int adults = Integer.parseInt(reader.readLine());

            System.out.print("Cabin class (Economy, Premium Economy, Business, First): ");
            String cabinClass = reader.readLine();

            Date date = new Date(year, month, day);
            ResponseBody searchCriteria = new ResponseBody(origin, destination, date, adults, cabinClass);
            searchCriteriaList.add(searchCriteria);
        }

        return searchCriteriaList;
    }*/