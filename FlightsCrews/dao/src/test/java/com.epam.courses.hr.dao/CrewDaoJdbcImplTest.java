package com.epam.courses.hr.dao;

import com.epam.courses.hr.model.Crew;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-dao.xml"})
@Transactional
@Rollback

class CrewDaoJdbcImplTest {


    private static final String FIRST_FUNCTION_NAME = "pilot";
    private static final String NEW_FUNCTION_NAME = "pilot11";
    private static final String NEW_FULL_NAME = "Sidorova D.D.";
    private static final String FIRST_FULL_NAME = "Ivanov S.V.";
    private static final int FIRST_CREW_ID = 1;
    private static final int CREW_NUM = 1;
    private static final int NEW_CREW_NUM = 2;
    private static final int NEW_CREW_NUM1 = 3;

    @Autowired
    private CrewDao crewDao;

    @Test
    void findAll() {
        Collection<Crew> crew = crewDao.findAll();
        assertNotNull(crew);
    }

    @Test
    void findById() {
        Crew crew = crewDao.findById(1);
        assertNotNull(crew);
        assertEquals(FIRST_CREW_ID, crew.getCrewId().intValue());
        assertEquals(FIRST_FUNCTION_NAME, crew.getFunctionName());
        assertEquals(FIRST_FULL_NAME, crew.getFullName());
        assertEquals(CREW_NUM, crew.getCrewNum().intValue());
    }

    @Test
    void addCrew() {
        Collection<Crew> crewsBeforeInsert = crewDao.findAll();

        Crew crew = new Crew();
        crew.setFunctionName(NEW_FUNCTION_NAME);
        crew.setFullName(NEW_FULL_NAME);
        crew.setCrewNum(NEW_CREW_NUM);
        Crew newCrew = crewDao.addCrew(crew);
        assertNotNull(newCrew.getCrewId());

        Collection<Crew> crewsAfterInsert = crewDao.findAll();
        assertEquals(1, crewsAfterInsert.size() - crewsBeforeInsert.size());
    }

    @Test
    void updateCrew() {
        Crew crew = new Crew();
        crew.setFunctionName(NEW_FUNCTION_NAME);
        crew.setFullName(NEW_FULL_NAME);
        crew.setCrewNum(NEW_CREW_NUM);
        Crew newCrew = crewDao.addCrew(crew);
        assertNotNull(newCrew.getCrewId());

        crew.setFunctionName(NEW_FUNCTION_NAME + "_2");
        crew.setFullName(NEW_FULL_NAME+ "_2");
        crew.setCrewNum(NEW_CREW_NUM1);
        crewDao.updateCrew(crew);

        Crew updatedCrew = crewDao.findById(crew.getCrewId());

        assertEquals(NEW_FUNCTION_NAME + "_2", updatedCrew.getFunctionName());
        assertEquals(NEW_FULL_NAME+ "_2", updatedCrew.getFullName());
        assertEquals(NEW_CREW_NUM1, updatedCrew.getCrewNum().intValue());

    }

    @Test
    void deleteCrew() {
        Crew crew=crewDao.findById(1);
        crewDao.deleteCrew(crew.getCrewId());

        Assertions.assertThrows(DataAccessException.class, () -> {
            crewDao.findById(crew.getCrewId());
        });
    }
}