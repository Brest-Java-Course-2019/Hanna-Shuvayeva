package com.epam.courses.hr.dao;

import com.epam.courses.hr.model.Flight;
import com.epam.courses.hr.stub.FlightStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;


public class FlightDaoJdbcImpl implements FlightDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightDaoJdbcImpl.class);
    private static final String SELECT_ALL = "select flightId, flightName, crewNum, departureDate from flight";
    private static final String FIND_BY_ID = "select flightId, flightName, crewNum, departureDate from flight where flightId = :flightId";
    private static final String FLIGHT_ID = "flightId";
    private static final String FLIGHT_NAME = "flightName";
    private static final String CREW_NUM = "crewNum";
    private static final String DEPARTURE_DATE = "departureDate";

    private static final String CHECK_COUNT_NAME = "select count(flightId) from flight where lower(flightName) = lower(:flightName)";
    private static final String INSERT = "insert into flight (flightName, crewNum, departureDate) values (:flightName, :crewNum, :departureDate)";
    private static final String UPDATE = "update flight set flightName = :flightName, crewNum = :crewNum, departureDate = :departureDate where flightId = :flightId";
    private static final String DELETE = "delete from flight where flightId = :flightId";

    public static final String SELECT_ALL_STUBS = "SELECT f.flightId, f.flightName, f.crewNum, f.departureDate, IFNULL (count(c.crewId),0) AS People" +
            " FROM flight f " +
            " LEFT JOIN crew as c ON (f.crewNum = c.crewNum)" +
            " GROUP BY f.flightId, f.flightName, f.crewNum, f.departureDate";
    public static final String SELECT_ALL_STUBS_BY_ID = "SELECT f.flightId, f.flightName, f.crewNum, f.departureDate, IFNULL (count(c.crewId),0) AS People" +
            " FROM flight f " +
            " LEFT JOIN crew as c ON (f.crewNum = c.crewNum)" +
            " WHERE f.departureDate BETWEEN @dateFrom AND @dateTo" +
            " GROUP BY f.flightId, f.flightName, f.crewNum, f.departureDate";

    public static final String PEOPLE = "People";
    private static final String DATEFROM = "dateFrom";
    private static final String DATETO = "dateTo";


    final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public FlightDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public Collection<Flight> findAll() {
        LOGGER.debug("findAll()");
        List<Flight> flightList = namedParameterJdbcTemplate.query(SELECT_ALL, new FlightRowMapper());
        return flightList;
    }


    @Override
    public Collection<FlightStub> findAllStubs() {
        LOGGER.debug("findAllStubs()");
        List<FlightStub> flightList =
                namedParameterJdbcTemplate
                        .query(SELECT_ALL_STUBS,
                                (resultSet, i) -> new FlightStub()
                                        .flightStubId(resultSet.getInt(FLIGHT_ID))
                                        .flightStubName(resultSet.getString(FLIGHT_NAME))
                                        .flightStubNum(resultSet.getInt(CREW_NUM))
                                        .flightStubDate(resultSet.getDate(DEPARTURE_DATE))
                                        .flightStubPeople(resultSet.getInt(PEOPLE)));
        return flightList;
    }


    @Override
    public Collection<FlightStub> filteredFlightStubs(Date dateFrom, Date dateTo) throws ParseException {
        LOGGER.debug("FlightStub filteredFlightStubs date from: {} to:{}", dateFrom, dateTo);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        if (dateFrom == null) {
            java.util.Date date = simpleDateFormat.parse("31.05.1890");
            dateFrom = new Date(date.getTime());
        }
        if (dateTo == null) {
            java.util.Date date = simpleDateFormat.parse("31.05.2500");
            dateTo = new Date(date.getTime());
        }
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(DATEFROM, dateFrom);
        mapSqlParameterSource.addValue(DATETO, dateTo);
        Collection<FlightStub> flightStub = namedParameterJdbcTemplate
                .query(SELECT_ALL_STUBS_BY_ID, mapSqlParameterSource, BeanPropertyRowMapper.newInstance(FlightStub.class));
        return flightStub;
    }


    @Override
    public Flight findById(Integer flightId) {
        LOGGER.debug("findById({})", flightId);
        SqlParameterSource namedParameters = new MapSqlParameterSource(FLIGHT_ID, flightId);
        Flight flight = namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(Flight.class));
        return flight;
    }


    @Override
    public Flight addFlight(Flight flight) {
        LOGGER.debug("add({})", flight);
        return Optional.of(flight)
                .filter(this::isNameUnique)
                .map(this::insertFlight)
                .orElseThrow(() -> new IllegalArgumentException("Flight with the same name already exsists in DB."));
    }

    private boolean isNameUnique(Flight flight) {
        return namedParameterJdbcTemplate.queryForObject(CHECK_COUNT_NAME,
                new MapSqlParameterSource(FLIGHT_NAME, flight.getFlightName()),
                Integer.class) == 0;
    }

    private Flight insertFlight(Flight flight) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(FLIGHT_NAME, flight.getFlightName());
        mapSqlParameterSource.addValue(CREW_NUM, flight.getCrewNum());
        mapSqlParameterSource.addValue(DEPARTURE_DATE, flight.getDepartureDate());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        int result = namedParameterJdbcTemplate.update(INSERT, mapSqlParameterSource, generatedKeyHolder);
        LOGGER.debug("add( result update = {}, keyholder = {})", result, generatedKeyHolder.getKey().intValue());

        flight.setFlightId(generatedKeyHolder.getKey().intValue());
        return flight;
    }


    private class FlightRowMapper implements RowMapper<Flight> {

        @Override
        public Flight mapRow(ResultSet resultSet, int i) throws SQLException {
            Flight flight = new Flight();
            flight.setFlightId(resultSet.getInt(FLIGHT_ID));
            flight.setFlightName(resultSet.getString(FLIGHT_NAME));
            flight.setCrewNum(resultSet.getInt(CREW_NUM));
            flight.setDepartureDate(resultSet.getDate(DEPARTURE_DATE));
            return flight;
        }
    }

    @Override
    public void updateFlight(Flight flight) {
        Optional.of(namedParameterJdbcTemplate.update(UPDATE, new BeanPropertySqlParameterSource(flight)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update flight in DB"));
    }


    @Override
    public void deleteFlight(Integer flightId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(FLIGHT_ID, flightId);
        Optional.of(namedParameterJdbcTemplate.update(DELETE, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete flight from DB"));
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

}
