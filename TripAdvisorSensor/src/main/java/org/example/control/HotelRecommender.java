package org.example.control;

import org.example.model.Hotel;
import org.example.model.Location;

import java.util.ArrayList;
import java.util.List;

public class HotelRecommender {
    public static List<Hotel> recommendedHotels() {
        List<Hotel> hotels = new ArrayList<>();

        // Gran Canaria
        hotels.add(new Hotel("Hotel Riu Palace Oasis", "g230095-d569308", new Location("Gran Canaria", 27.737014895977957, -15.59764706166172), "TripAdvisor"));
        hotels.add(new Hotel("Gloria Palace Amadores Thalasso & Hotel", "g635887-d530796", new Location("Gran Canaria", 27.78609897163779, -15.718837309599461), "TripAdvisor"));
        hotels.add(new Hotel("Servatur Puerto Azul", "g635887-d287523", new Location("Gran Canaria", 27.78680611665208, -15.717614328907693), "TripAdvisor"));
        hotels.add(new Hotel("Gloria Palace San Agustín Thalasso & Hotel", "g562818-d237094", new Location("Gran Canaria", 27.786041418434202, -15.718846162670909), "TripAdvisor"));
        hotels.add(new Hotel("Hotel Emblematico Agaldar", "g1064347-d19103435", new Location("Gran Canaria", 28.14515316991462, -15.655791386900585), "TripAdvisor"));

        //Tenerife
        hotels.add(new Hotel("Gran Melia Palacio De Isora", "g1773834-d945835", new Location("Tenerife", 28.20461589726771, -16.83169396088734), "TripAdvisor"));
        hotels.add(new Hotel("Iberostar Selection Anthelia", "g662606-d285140", new Location("Tenerife", 28.089523897172345, -16.737520965443977), "TripAdvisor"));
        hotels.add(new Hotel("Melia Jardines Del Teide- Adults only", "g662606-d248456", new Location("Tenerife", 28.096310590333644, -16.738587180370377), "TripAdvisor"));
        hotels.add(new Hotel("Hotel Las Águilas Tenerife, Affiliated by Meliá", "g187481-d566003", new Location("Tenerife", 28.399904408377537, -16.545838813569343), "TripAdvisor"));
        hotels.add(new Hotel("Iberostar Bouganville Playa", "g662606-d296925", new Location("Tenerife", 28.0740997135317, -16.73245475669283), "TripAdvisor"));

        // Fuerteventura
        hotels.add(new Hotel("Hotel Riu Oliva Beach Resort", "g580322-d237064", new Location("Fuerteventura", 28.708557875262862, -13.839987995411114), "TripAdvisor"));
        hotels.add(new Hotel("H10 Tindaya", "g659634-d579375", new Location("Fuerteventura", 28.152478773858096, -14.230474740894213), "TripAdvisor"));
        hotels.add(new Hotel("Iberostar Selection Fuerteventura Palace", "g659632-d288584", new Location("Fuerteventura", 28.056517301280696, -14.320438051570607), "TripAdvisor"));
        hotels.add(new Hotel("Sheraton Fuerteventura Beach, Golf & Spa Resort", "g658907-d601739", new Location("Fuerteventura", 28.390007068306755, -13.863540232769722), "TripAdvisor"));
        hotels.add(new Hotel("Barcelo Fuerteventura Mar", "g658907-d255145", new Location("Fuerteventura", 28.39744709172223, -13.85829639790993), "TripAdvisor"));

        // Lanzarote
        hotels.add(new Hotel("Secrets Lanzarote Resort & Spa", "g673239-d282759", new Location("Lanzarote", 28.91710721088738, -13.71108200761156), "TripAdvisor"));
        hotels.add(new Hotel("Barcelo Teguise Beach - Adults Only", "g659633-d287948", new Location("Lanzarote", 28.995729884877015, -13.489554791241275), "TripAdvisor"));
        hotels.add(new Hotel("Gran Castillo Tagoro Family & Fun", "g652121-d1173876", new Location("Lanzarote", 28.860524487332654, -13.80099931241944), "TripAdvisor"));
        hotels.add(new Hotel("Barcelo Lanzarote Active Resort", "g659633-d234400", new Location("Lanzarote", 28.988354261801085, -13.51101801574875), "TripAdvisor"));
        hotels.add(new Hotel("Hotel Sentido Aequora Lanzarote Suites", "g662290-d285669", new Location("Lanzarote", 28.932405820994518, -13.629571556093435), "TripAdvisor"));

        // La Palma
        hotels.add(new Hotel("La Palma Princess", "g1175543-d638034", new Location("La Palma",28.50342048443061, -17.874194501816856), "TripAdvisor"));
        hotels.add(new Hotel("Hotel San Telmo", "g187476-d1760436", new Location("La Palma",28.67527582703306, -17.76745239289178), "TripAdvisor"));
        hotels.add(new Hotel("Hotel Emblemático Holiday Time", "g187476-d17288155", new Location("La Palma",28.683280169612484, -17.76357196532542), "TripAdvisor"));
        hotels.add(new Hotel("Hotel Hacienda De Abajo", "g1177806-d3577949", new Location("La Palma",28.642650164442788, -17.93384598776484), "TripAdvisor"));
        hotels.add(new Hotel("H10 Taburiente Playa", "g659966-d289252", new Location("La Palma",28.6458196519528, -17.75854800919634), "TripAdvisor"));

        // El Hierro
        hotels.add(new Hotel("Puntagrande Hotel", "g2139290-d627753", new Location("El Hierro",27.797028839927375, -17.99217495747635), "TripAdvisor"));
        hotels.add(new Hotel("Hotel Lua Boutique", "g2467431-d21119345", new Location("El Hierro",27.700648521894976, -17.981547961510053), "TripAdvisor"));
        hotels.add(new Hotel("Parador de El Hierro", "g187474-d277394", new Location("El Hierro",27.717196909014273, -17.958246787577657), "TripAdvisor"));
        hotels.add(new Hotel("Hotel Villa El Mocanal", "g1190055-d630598", new Location("El Hierro",27.81849759771758, -17.943935240986033), "TripAdvisor"));
        hotels.add(new Hotel("Apartahotel Boomerang", "g187474-d1497072", new Location("El Hierro",27.80841881597507, -17.914683398711645), "TripAdvisor"));

        // La Gomera
        hotels.add(new Hotel("Hotel Gran Rey", "g674782-d616495", new Location("La Gomera",28.088963624354644, -17.33904824448477), "TripAdvisor"));
        hotels.add(new Hotel("Residencial El Llano", "g674782-d946718", new Location("La Gomera",28.08519166053818, -17.33488053155366), "TripAdvisor"));
        hotels.add(new Hotel("Parador de La Gomera", "g187470-d190895", new Location("La Gomera",28.090883850408403, -17.108348885092916), "TripAdvisor"));
        hotels.add(new Hotel("Charco Del Conde", "g674782-d670025", new Location("La Gomera",28.085362201169335, -17.337282987812312), "TripAdvisor"));
        hotels.add(new Hotel("Hotel La Colombina", "g187470-d2039274", new Location("La Gomera",28.093265957904897, -17.11320714722318), "TripAdvisor"));

        return hotels;
    }
}
