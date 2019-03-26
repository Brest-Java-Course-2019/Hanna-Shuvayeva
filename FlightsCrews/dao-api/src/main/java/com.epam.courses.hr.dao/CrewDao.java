package com.epam.courses.hr.dao;

import com.epam.courses.hr.model.Crew;

import java.util.Collection;


public interface CrewDao {


    Collection<Crew> findAll();

    Crew findById(Integer crewId);

    Crew addCrew(Crew crew);

    void updateCrew(Crew crew);

    void deleteCrew(Integer crewId);


}
