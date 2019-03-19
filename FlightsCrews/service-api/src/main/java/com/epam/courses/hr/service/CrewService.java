package com.epam.courses.hr.service;

import com.epam.courses.hr.model.Crew;

import java.util.Collection;

public interface CrewService {


    Collection<Crew> findAll();

    Crew findById(Integer crewId);

    void addCrew(Crew crew);

    void updateCrew(Crew crew);

    void deleteCrew(Integer crewId);
}
