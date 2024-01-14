package org.example.model;

import org.example.model.Date;

import java.time.LocalTime;

public class Flight {
    private final Date departureDate;
    private final LocalTime departureTime;
    private final String origin;
    private final String destination;
    private final Integer adults;
    private final Cabin cabin;


    public Flight(Date departureDate, LocalTime departureTime, String origin, String destination, Integer adults, Cabin cabin) {
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.origin = origin;
        this.destination = destination;
        this.adults = adults;
        this.cabin = cabin;
    }


    public Date getDepartureDate() {
        return departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public Integer getAdults() {
        return adults;
    }

    public Cabin getCabin() {
        return cabin;
    }

    public String buildJson() {
        return "Flight{" +
                ", departureDate=" + departureDate +
                ", departureTime=" + departureTime +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", adults=" + adults +
                ", cabinClass=" + cabin +
                '}';
    }

}
