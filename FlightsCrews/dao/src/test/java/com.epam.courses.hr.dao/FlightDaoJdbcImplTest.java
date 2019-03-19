package com.epam.courses.hr.dao;

import com.epam.courses.hr.model.Flight;
import com.epam.courses.hr.stub.FlightStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Collection;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-dao.xml"})
@Transactional
@Rollback
class FlightDaoJdbcImplTest {

    private static final String FLIGHT_NAME = "Moscow-New York";
    private static final String NEW_FLIGHT_NAME = "Moscow-Riga";
    private static final Date NEW_DEPARTURE_DATE = Date.valueOf("2019-01-01");
    private static final Date NEW_DEPARTURE_DATE1 = Date.valueOf("2020-01-02");
    private static final Date DATE_TO = Date.valueOf("2019-01-01");
    private static final Date DATE_FROM = Date.valueOf("2019-01-01");

    private static final int CREW_NUM = 1;
    private static final int FIRST_FLIGHT_ID = 1;
    private static final int NEW_CREW_NUM = 2;
    private static final Integer NEW_CREW_NUM1 = 4;

    @Autowired
    private FlightDao flightDao;

    @Test
    void findAll() {
        Collection<Flight> flight = flightDao.findAll();
        assertNotNull(flight);
    }

    @Test
    void findAllStubs() {
        Collection<FlightStub>flight = flightDao.findAllStubs();
        assertNotNull(flight);
        assertTrue(flight.size() > 0);
    }


    @Test
    void findById() {
        Flight flight = flightDao.findById(1);
        assertNotNull(flight);
        assertEquals(FIRST_FLIGHT_ID, flight.getFlightId().intValue());
        assertEquals(FLIGHT_NAME, flight.getFlightName());
        assertEquals(CREW_NUM, flight.getCrewNum().intValue());
    }


    @Test
    void deleteFlight() {
        Flight flight=flightDao.findById(1);
        flightDao.deleteFlight(flight.getFlightId());

        Assertions.assertThrows(DataAccessException.class, () -> {
            flightDao.findById(flight.getFlightId());
        });
    }


    @Test
    void updateFlight() {
        Flight flight = new Flight();
        flight.setFlightName(NEW_FLIGHT_NAME);
        flight.setCrewNum(NEW_CREW_NUM);
        flight.setDepartureDate(NEW_DEPARTURE_DATE);
        Flight newFlight = flightDao.addFlight(flight);
        assertNotNull(newFlight.getFlightId());

        flight.setFlightName(NEW_FLIGHT_NAME + "_2");
        flight.setCrewNum(NEW_CREW_NUM1);
        flight.setDepartureDate(NEW_DEPARTURE_DATE1);
        flightDao.updateFlight(flight);

        Flight updatedFlight = flightDao.findById(flight.getFlightId());

        assertEquals(NEW_FLIGHT_NAME + "_2", updatedFlight.getFlightName());
        assertEquals(NEW_CREW_NUM1, updatedFlight.getCrewNum());
        assertEquals(NEW_DEPARTURE_DATE1, updatedFlight.getDepartureDate());
    }


    @Test
    void addFlight() {
        Collection<Flight> flightsBeforeInsert = flightDao.findAll();

        Flight flight = new Flight();
        flight.setFlightName(NEW_FLIGHT_NAME);
        flight.setCrewNum(NEW_CREW_NUM);
        flight.setDepartureDate(NEW_DEPARTURE_DATE);
        Flight newFlight = flightDao.addFlight(flight);
        assertNotNull(newFlight.getFlightId());

        Collection<Flight> flightsAfterInsert = flightDao.findAll();
        assertEquals(1, flightsAfterInsert.size() - flightsBeforeInsert.size());
    }


    @Test
    void filteredFlightStubs() throws ParseException {
        int size=flightDao.findAllStubs().size();
        int sizeNull=flightDao.filteredFlightStubs(null,null).size();
        assertFalse(size==sizeNull);
    }


    @Test
    void filteredFlightStubsTest2() throws ParseException {
        Date dateFromSql = DATE_TO;
        Date dateToSql =  DATE_FROM;
        Collection<FlightStub> flightStub = flightDao.filteredFlightStubs(dateFromSql, dateToSql);
        assertNotNull(flightStub);
    }






}