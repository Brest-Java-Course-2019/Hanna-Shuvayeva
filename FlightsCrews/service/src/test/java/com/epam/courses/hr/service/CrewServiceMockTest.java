package com.epam.courses.hr.service;

import com.epam.courses.hr.dao.CrewDao;
import com.epam.courses.hr.model.Crew;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.refEq;


public class CrewServiceMockTest {



    private CrewDao dao;

    private CrewService service;

    private static final Integer CREWID = 1;

    @BeforeEach
    void setup() {
        dao = Mockito.mock(CrewDao.class);
        service = new CrewServiceImpl(dao);
    }

    private Crew fly() {
        Crew crew = new Crew();
        crew.setCrewId(4);
        crew.setFunctionName("dfgd");
        crew.setFullName("dfgd");
        crew.setCrewNum(2);
        return crew;
    }


    @Test
    public void findAll() {

        Mockito.when(dao.findAll()).thenReturn(Arrays.asList(fly()));

        Collection<Crew> result = service.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());

        Mockito.verify(dao, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(dao);
    }

    @Test
    public void findById() {
        dao.findById(CREWID);
        Mockito.verify(dao);
        service.findById(CREWID);
    }


    @Test
    public void deleteCrew() {
        dao.deleteCrew(CREWID);
        Mockito.verify(dao);
        service.deleteCrew(CREWID);
    }

    @Test
    public void updateCrew() {
        dao.updateCrew(fly());
        Mockito.verify(dao).updateCrew(refEq(fly()));
        service.updateCrew(fly());
    }

    @Test
    public void addCrew() {
        dao.addCrew(fly());
        Mockito.verify(dao).addCrew(refEq(fly()));
        service.addCrew(fly());
    }











}
