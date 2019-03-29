package com.epam.courses.hr.rest_app;


import com.epam.courses.hr.model.Crew;
import com.epam.courses.hr.service.CrewService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class CrewRestControllerTest {

    @Autowired
    private CrewRestController controller;

    @Autowired
    private CrewService crewService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @AfterEach
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(crewService);
        Mockito.reset(crewService);
    }

    @Test
    public void crews() throws Exception {
        Mockito.when(crewService.findAll()).thenReturn(new ArrayList<Crew>() {{add(create(0)); add(create(1));}});

        mockMvc.perform(
                MockMvcRequestBuilders.get("/crews/all")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].functionName", Matchers.is("def0")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fullName", Matchers.is("desc0")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].crewId", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].crewNum", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].functionName", Matchers.is("def1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].fullName", Matchers.is("desc1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].crewId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].crewNum", Matchers.is(1)))
        ;

        Mockito.verify(crewService, Mockito.times(1)).findAll();
    }

    private Crew create(int index) {
        Crew crew = new Crew();
        crew.setFunctionName("def" + index);
        crew.setFullName("desc"+ index);
        crew.setCrewNum(index);
        crew.setCrewId(index);
        return crew;
    }
}
