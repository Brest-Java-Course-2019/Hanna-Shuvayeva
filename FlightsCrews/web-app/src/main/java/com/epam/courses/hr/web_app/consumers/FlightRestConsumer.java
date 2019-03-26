package com.epam.courses.hr.web_app.consumers;

import com.epam.courses.hr.model.Flight;
import com.epam.courses.hr.service.FlightService;
import com.epam.courses.hr.stub.FlightStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.text.ParseException;
import java.util.Collection;


public class FlightRestConsumer implements FlightService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightRestConsumer.class);

    private String url;

    private RestTemplate restTemplate;

    public FlightRestConsumer(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public Collection<Flight> findAll() {
        LOGGER.debug("findAll()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/all", Collection.class);
        return (Collection<Flight>) responseEntity.getBody();
    }

    @Override
    public Collection<FlightStub> findAllStubs() {
        LOGGER.debug("findAllStubs()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/stubs", Collection.class);
        return  (Collection<FlightStub>) responseEntity.getBody();
    }

    @Override
    public Collection<FlightStub> filteredFlightStubs(Date dateFrom, Date dateTo) throws ParseException{
        Date dateFromsql=null;
        Date dateTosql=null;
        if(dateFrom != Date.valueOf("00-00-00"))
            dateFromsql = dateFrom;
        if(dateFrom != Date.valueOf("00-00-00"))
            dateTosql = dateTo;
        LOGGER.debug("filteredFlightStubs(dateFrom - {},dateTo-{})", dateFromsql, dateTosql);
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/stubs", Collection.class);
        return (Collection<FlightStub>) responseEntity.getBody();
    }

    @Override
    public Flight findById(Integer id) {
        LOGGER.debug("findById({})", id);
        ResponseEntity<Flight> responseEntity = restTemplate.getForEntity(url + "/" + id, Flight.class);
        return responseEntity.getBody();
    }

    @Override
    public void addFlight(Flight flight) {
        LOGGER.debug("add({})", flight);
        restTemplate.postForEntity(url, flight, Flight.class);
    }

    @Override
    public void updateFlight(Flight flight) {
        LOGGER.debug("update({})", flight);
        restTemplate.put(url, flight);
    }

    @Override
    public void deleteFlight(Integer FlightId) {
        LOGGER.debug("delete({})", FlightId);
        restTemplate.delete(url + "/" + FlightId);
    }
}
