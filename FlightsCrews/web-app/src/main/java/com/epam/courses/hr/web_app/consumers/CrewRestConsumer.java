package com.epam.courses.hr.web_app.consumers;

import com.epam.courses.hr.model.Crew;
import com.epam.courses.hr.service.CrewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;


public class CrewRestConsumer implements CrewService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrewRestConsumer.class);

    private String url;

    private RestTemplate restTemplate;

    public CrewRestConsumer(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public Collection<Crew> findAll() {
        LOGGER.debug("findAllStubs()");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/all", Collection.class);
        return (Collection<Crew>) responseEntity.getBody();
    }


    @Override
    public Crew findById(Integer id) {
        LOGGER.debug("findById({})", id);
        ResponseEntity<Crew> responseEntity = restTemplate.getForEntity(url + "/" + id, Crew.class);
        return responseEntity.getBody();
    }

    @Override
    public void addCrew(Crew crew) {
        LOGGER.debug("add({})", crew);
        restTemplate.postForEntity(url, crew, Crew.class);
    }

    @Override
    public void updateCrew(Crew crew) {
        LOGGER.debug("update({})", crew);
        restTemplate.put(url, crew);
    }

    @Override
    public void deleteCrew(Integer CrewId) {
        LOGGER.debug("delete({})", CrewId);
        restTemplate.delete(url + "/" + CrewId);
    }
}
