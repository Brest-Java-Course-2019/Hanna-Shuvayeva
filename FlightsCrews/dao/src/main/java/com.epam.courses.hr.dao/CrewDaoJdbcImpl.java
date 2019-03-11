package com.epam.courses.hr.dao;

import com.epam.courses.hr.model.Crew;
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
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CrewDaoJdbcImpl implements CrewDao{

    private static final Logger LOGGER = LoggerFactory.getLogger(CrewDaoJdbcImpl.class);
    private static final String SELECT_ALL="select crewId, functionName, fullName, crewNum from crew";
    private static final String FIND_BY_ID = "select crewId, functionName, fullName, crewNum from crew where crewId = :crewId";
    private static final String CREW_ID = "crewId";
    private static final String FUNCTION_NAME= "functionName";
    private static final String FULL_NAME= "fullName";
    private static final String CREW_NUM = "crewNum";

    private static final String UPDATE = "update crew set functionName = :functionName, fullName = :fullName, crewNum = :crewNum where crewId = :crewId";
    private static final String DELETE = "delete from crew where crewId = :crewId";
    private static final String INSERT = "insert into crew (functionName, fullName, crewNum) values (:functionName, :fullName, :crewNum)";

    final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CrewDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Collection<Crew> findAll() {
        LOGGER.debug("findAll()");
        List<Crew> crewList = namedParameterJdbcTemplate.query(SELECT_ALL, new CrewRowMapper());
        return crewList;
    }

    @Override
    public Crew findById(Integer crewId) {
        LOGGER.debug("findById({})", crewId);
        SqlParameterSource namedParameters = new MapSqlParameterSource(CREW_ID, crewId);
        Crew crew = namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(Crew.class));
        return crew;

    }

    @Override
    public Crew addCrew (Crew crew) {
        LOGGER.debug("add({})", crew);
        return Optional.of(crew)
                .map(this::insertCrew)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    private Crew insertCrew(Crew crew) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(FUNCTION_NAME, crew.getFunctionName());
        mapSqlParameterSource.addValue(FULL_NAME, crew.getFunctionName());
        mapSqlParameterSource.addValue(CREW_NUM, crew.getCrewNum());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        int result = namedParameterJdbcTemplate.update(INSERT, mapSqlParameterSource, generatedKeyHolder);
        LOGGER.debug("add( result update = {}, keyholder = {})", result, generatedKeyHolder.getKey().intValue());

        crew.setCrewId(generatedKeyHolder.getKey().intValue());
        return crew;
    }


    private class CrewRowMapper implements RowMapper<Crew> {

        @Override
        public Crew mapRow(ResultSet resultSet, int i) throws SQLException {
            Crew crew = new Crew();
            crew.setCrewId(resultSet.getInt(CREW_ID));
            crew.setFunctionName(resultSet.getString(FUNCTION_NAME));
            crew.setFunctionName(resultSet.getString(FULL_NAME));
            crew.setCrewNum(resultSet.getInt(CREW_NUM));
            return crew;
        }
    }

    @Override
    public void updateCrew(Crew crew) {
        Optional.of(namedParameterJdbcTemplate.update(UPDATE, new BeanPropertySqlParameterSource(crew)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update crew in DB"));
    }

    @Override
    public void deleteCrew(Integer crewId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(CREW_ID, crewId);
        Optional.of(namedParameterJdbcTemplate.update(DELETE, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete crew from DB"));
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }



}
