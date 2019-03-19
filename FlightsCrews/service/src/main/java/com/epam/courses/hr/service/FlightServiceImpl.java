package com.epam.courses.hr.service;

import com.epam.courses.hr.dao.FlightDao;
import com.epam.courses.hr.model.Flight;
import com.epam.courses.hr.stub.FlightStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.ParseException;
import java.util.Collection;

@Transactional
public class FlightServiceImpl implements FlightService{

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightServiceImpl.class);

    private FlightDao dao;

    public FlightServiceImpl(FlightDao dao) {
        this.dao = dao;
    }


    @Override
    public Collection<Flight> findAll() {
        LOGGER.debug("Find all flights");
        return dao.findAll();
    }

    @Override
    public Collection<FlightStub> findAllStubs() {
        LOGGER.debug("Find all flights in Stub");
        return dao.findAllStubs();
    }

    @Override
    public Flight findById(Integer flightId) {
        LOGGER.debug("findById({})", flightId);
        return dao.findById(flightId);
    }

    @Override
    public Collection<FlightStub> filteredFlightStubs(Date dateFrom, Date dateTo) throws ParseException {
        LOGGER.debug("filteredFlightStubs({})", dateFrom, dateTo);
        return dao.filteredFlightStubs(dateFrom, dateTo);
    }

    @Override
    public void addFlight(Flight flight) {
        LOGGER.debug("add({})", flight);
        dao.addFlight(flight);
    }

    @Override
    public void updateFlight(Flight flight) {
        LOGGER.debug("update({})", flight);
        dao.updateFlight(flight);
    }

    @Override
    public void deleteFlight(Integer flightId) {
        LOGGER.debug("delete({})", flightId);
        dao.deleteFlight(flightId);
    }


}
