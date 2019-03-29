package com.epam.courses.hr.rest_app;

import com.epam.courses.hr.model.Crew;
import com.epam.courses.hr.service.CrewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping(value = "/crews")
public class CrewRestController implements CrewService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrewRestController.class);

    @Autowired
    private CrewService crewService;

    @Override
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Collection<Crew> findAll() {
        LOGGER.debug("get all crews");
        return crewService.findAll();
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Crew findById(@PathVariable Integer id) {
        LOGGER.debug("find crew by id({})", id);
        return crewService.findById(id);
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public void addCrew(@RequestBody Crew crew) {
        LOGGER.debug("add crew({})", crew);
        crewService.addCrew(crew);
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT)
    public void updateCrew(@RequestBody Crew crew) {
        LOGGER.debug("update crew ({})", crew);
        crewService.updateCrew(crew);
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCrew(@PathVariable Integer id) {
        LOGGER.debug("delete crew ({})", id);
        crewService.deleteCrew(id);
    }
}
