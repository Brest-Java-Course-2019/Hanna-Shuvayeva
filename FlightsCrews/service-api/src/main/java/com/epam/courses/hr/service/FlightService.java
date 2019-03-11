package com.epam.courses.hr.service;

import com.epam.courses.hr.model.Flight;
import com.epam.courses.hr.stub.FlightStub;

import java.sql.Date;
import java.text.ParseException;
import java.util.Collection;

public interface FlightService {

    Collection<Flight> findAll();

    Collection<FlightStub> findAllStubs();

    Collection<FlightStub> filteredFlightStubs(Date dateFrom, Date dateTo) throws ParseException;

    Flight findById(Integer flightId);

    void addFlight(Flight flight);

    void updateFlight(Flight flight);

    void deleteFlight(Integer flightId);

}
