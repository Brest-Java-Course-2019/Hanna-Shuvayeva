package com.epam.courses.hr.service;

import com.epam.courses.hr.dao.FlightDao;
import com.epam.courses.hr.model.Flight;
import com.epam.courses.hr.stub.FlightStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.refEq;


public class FlightServiceMockTest {


    private FlightDao dao;

    private FlightService service;

    private static final Integer FLIGHTID = 1;

    @BeforeEach
    void setup() {
        dao = Mockito.mock(FlightDao.class);
        service = new FlightServiceImpl(dao);
    }

    private Flight fly() {
        Flight flight = new Flight();
        flight.setFlightId(4);
        flight.setFlightName("dfgd");
        flight.setCrewNum(1);
        flight.setDepartureDate(Date.valueOf("2020-10-12"));
        return flight;
    }


    @Test
    public void findAll() {

        Mockito.when(dao.findAll()).thenReturn(Arrays.asList(fly()));

        Collection<Flight> result = service.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());

        Mockito.verify(dao, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(dao);
    }


    @Test
    public void deleteFlight() {
        dao.deleteFlight(FLIGHTID);
        Mockito.verify(dao);
        service.deleteFlight(FLIGHTID);
    }

    @Test
    public void updateFlight() {
        dao.updateFlight(fly());
        Mockito.verify(dao).updateFlight(refEq(fly()));
        service.updateFlight(fly());
    }

    @Test
    public void addFlight() {
        dao.addFlight(fly());
        Mockito.verify(dao).addFlight(refEq(fly()));
        service.addFlight(fly());
    }

    @Test
    public void findById() {
        dao.findById(FLIGHTID);
        Mockito.verify(dao);
        service.findById(FLIGHTID);
    }

    @Test
    public void findAllStubs() {
        dao.findAllStubs();
        Mockito.verify(dao);
        service.findAllStubs();
    }











}
