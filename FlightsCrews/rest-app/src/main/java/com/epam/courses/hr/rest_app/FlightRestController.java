package com.epam.courses.hr.rest_app;

import com.epam.courses.hr.model.Flight;
import com.epam.courses.hr.service.FlightService;
import com.epam.courses.hr.stub.FlightStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.util.Collection;

@RestController
@RequestMapping(value = "/flights")
public class FlightRestController implements FlightService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightRestController.class);

    @Autowired
    private FlightService flightService;

    @Override
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Collection<Flight> findAll() {
        LOGGER.debug("get all flights");
        return flightService.findAll();
    }

    @Override
    @RequestMapping(value = "/stubs", method = RequestMethod.GET)
    public Collection<FlightStub> findAllStubs() {
        LOGGER.debug("get all flights from stub");
        return flightService.findAllStubs();
    }

    @Override
    @RequestMapping(value = "/flights/{dateFrom}/{dateTo}", method = RequestMethod.GET)
    public Collection<FlightStub> filteredFlightStubs(Date dateFrom, Date dateTo) throws ParseException {
        Date dateFromsql=null;
        Date dateTosql=null;
        if(dateFrom != Date.valueOf("00-00-00"))
            dateFromsql = dateFrom;
        if(dateFrom != Date.valueOf("00-00-00"))
            dateTosql = dateTo;
        LOGGER.debug("filteredFlightStubs(dateFrom - {},dateTo-{})", dateFromsql, dateTosql);
        return flightService.filteredFlightStubs(dateFromsql, dateTosql);
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Flight findById(@PathVariable Integer id) {
        LOGGER.debug("find flight by id({})", id);
        return flightService.findById(id);
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public void addFlight(@RequestBody Flight flight) {
        LOGGER.debug("add flight({})", flight);
        flightService.addFlight(flight);
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT)
    public void updateFlight(@RequestBody Flight flight) {
        LOGGER.debug("update flight ({})", flight);
        flightService.updateFlight(flight);
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteFlight(@PathVariable Integer id) {
        LOGGER.debug("delete flight ({})", id);
        flightService.deleteFlight(id);
    }
}
