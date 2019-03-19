package com.epam.courses.hr.service;

import com.epam.courses.hr.dao.CrewDao;
import com.epam.courses.hr.model.Crew;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional
public class CrewServiceImpl implements CrewService{

    private static final Logger LOGGER = LoggerFactory.getLogger(CrewServiceImpl.class);

    private CrewDao dao;

    public CrewServiceImpl(CrewDao dao) {
        this.dao = dao;
    }


    @Override
    public Collection<Crew> findAll() {
        LOGGER.debug("Find all crews");
        return dao.findAll();
    }

    @Override
    public Crew findById(Integer crewId) {
        LOGGER.debug("findById({})", crewId);
        return dao.findById(crewId);
    }

    @Override
    public void addCrew(Crew crew) {
        LOGGER.debug("add({})", crew);
        dao.addCrew(crew);
    }

    @Override
    public void updateCrew(Crew crew) {
        LOGGER.debug("update({})", crew);
        dao.updateCrew(crew);
    }

    @Override
    public void deleteCrew(Integer crewId) {
        LOGGER.debug("delete({})", crewId);
        dao.deleteCrew(crewId);
    }


}
