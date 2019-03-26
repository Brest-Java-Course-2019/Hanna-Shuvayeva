package com.epam.courses.hr.web_app;

import com.epam.courses.hr.model.Crew;
import com.epam.courses.hr.service.CrewService;
import com.epam.courses.hr.web_app.validators.CrewValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CrewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrewController.class);

    @Autowired
    private CrewService crewService;

    @Autowired
    CrewValidator crewValidator;

    @GetMapping(value = "/crews")
    public final String crews(Model model) {

        LOGGER.debug("findAll({})", model);
        model.addAttribute("crews", crewService.findAll());
        return "crews";
    }

    @GetMapping(value = "/crew/{id}")
    public final String gotoEditCrewPage(@PathVariable Integer id, Model model) {

        LOGGER.debug("gotoEditCrewPage({},{})", id, model);
        Crew crew = crewService.findById(id);
        model.addAttribute("crew", crew);
        return "crew";
    }

    @PostMapping(value = "/crew/{id}")
    public final String updateCrew(@Valid Crew crew,
                                   BindingResult result) {

        LOGGER.debug("updateCrew({}, {})", crew, result);
        crewValidator.validate(crew, result);
        if (result.hasErrors()) {
            return "crew";
        } else {
            this.crewService.updateCrew(crew);
            return "redirect:/crews";
        }
    }

    @GetMapping(value = "/crew")
    public final String gotoAddCrewPage(Model model) {

        LOGGER.debug("create({})", model);
        Crew crew = new Crew();
        model.addAttribute("isNew", true);
        model.addAttribute("crew", crew);
        return "crew";
    }

    @PostMapping(value = "/crew")
    public String addCrew(@Valid Crew crew,
                                BindingResult result) {

        LOGGER.debug("addCrew({}, {})", crew, result);
        crewValidator.validate(crew, result);
        if (result.hasErrors()) {
            return "crew";
        } else {
            this.crewService.addCrew(crew);
            return "redirect:/crews";
        }
    }

    @GetMapping(value = "/crew/{id}/delete")
    public final String deleteCrewById(@PathVariable Integer id, Model model) {
        LOGGER.debug("delete({},{})", id, model);
        crewService.deleteCrew(id);
        return "redirect:/crews";
    }
}
