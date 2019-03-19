package com.epam.courses.hr.service;

import com.epam.courses.hr.model.Flight;
import com.epam.courses.hr.stub.FlightStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.text.ParseException;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml"})
class FlightServiceImplTest {

    private static final Integer SIZE = 2;
    private static final Date DATE_TO = Date.valueOf("2019-01-01");
    private static final Date DATE_FROM = Date.valueOf("2019-01-01");

    @Autowired
    private FlightService flightService;

    @Test
    void findAll() {
        Collection<Flight> flights = flightService.findAll();
        assertNotNull(flights);
    }

    @Test
    void findAllStubs() {
        Collection<FlightStub> flights = flightService.findAllStubs();
        assertNotNull(flights);
    }

    @Test
    void findById() {
        Flight firstFlight = flightService.findAll().iterator().next();
        assertNotNull(firstFlight);
        Integer id = firstFlight.getFlightId();

        Flight flight = flightService.findById(id);
        assertNotNull(flight);
        assertEquals(firstFlight.getFlightName(), flight.getFlightName());

    }

    @Test
    void filteredFlightStubs() throws ParseException {
        Collection<FlightStub> flights = flightService.filteredFlightStubs(DATE_TO, DATE_FROM);
        assertNotNull(flights);

    }

    @Test
    void addFlight() {

        long count = flightService.findAll().size();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            flightService.addFlight(flightService.findAll().iterator().next());
        });

        long newCount = flightService.findAll().size();
        assertTrue(count == newCount);
    }



    @Test
    void updateFlight() {

        long count = flightService.findAll().size();

        flightService.updateFlight(flightService.findAll().iterator().next());

        long newCount = flightService.findAll().size();
        assertTrue(count == newCount);


    }

    @Test
    void deleteFlight() {
        Flight firstFlight = flightService.findById(1);
        flightService.deleteFlight(firstFlight.getFlightId());
        Integer size = flightService.findAll().size();
        assertEquals(size, SIZE);

    }
}