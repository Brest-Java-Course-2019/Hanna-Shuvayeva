package com.epam.courses.hr.service;

import com.epam.courses.hr.model.Crew;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml"})
class CrewServiceImplTest {

    private static final Integer SIZE = 7;


    @Autowired
    private CrewService crewService;

    @Test
    void findAll() {
        Collection<Crew> crews = crewService.findAll();
        assertNotNull(crews);
    }

    @Test
    void findById() {
        Crew firstCrew = crewService.findAll().iterator().next();
        assertNotNull(firstCrew);
        Integer id = firstCrew.getCrewId();

        Crew crew = crewService.findById(id);
        assertNotNull(crew);
        assertEquals(firstCrew.getCrewId(), crew.getCrewId());

    }

    @Test
    void deleteCrew() {
        Crew firstCrew = crewService.findById(1);
        crewService.deleteCrew(firstCrew.getCrewId());
        Integer size = crewService.findAll().size();
        assertEquals(size, SIZE);

    }


    @Test
    void addCrew() {

        long count = crewService.findAll().size();

        crewService.addCrew(crewService.findAll().iterator().next());

        long newCount = crewService.findAll().size();
        assertTrue(count != newCount);
    }



    @Test
    void updateCrew() {

        long count = crewService.findAll().size();

        crewService.updateCrew(crewService.findAll().iterator().next());

        long newCount = crewService.findAll().size();
        assertTrue(count == newCount);

    }


}