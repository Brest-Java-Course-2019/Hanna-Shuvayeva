package com.epam.courses.hr.web_app;

import com.epam.courses.hr.model.Flight;
import com.epam.courses.hr.service.FlightService;
import com.epam.courses.hr.stub.FlightStub;
import com.epam.courses.hr.stub.Interval;
import com.epam.courses.hr.stub.IntervalValidator;
import com.epam.courses.hr.web_app.validators.FlightValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Date;
import java.text.ParseException;
import java.util.Collection;

import javax.validation.Valid;


@Controller
public class FlightController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrewController.class);

    @Autowired
    private FlightService flightService;

    @Autowired
    FlightValidator flightValidator;

    @GetMapping(value = "/flights")
    public final String flights(Model model) {

        LOGGER.debug("findAllStubs({})", model);
        model.addAttribute("crews", flightService.findAllStubs());
        return "flights";
    }

    @PostMapping(value = "/filtrFlights")
    public String filtrFlights(Model model, Interval datesInterval, BindingResult bindingResult) throws ParseException {

        LOGGER.debug("StudentController - filtrStudents - {}", datesInterval);
        Date dateFrom = datesInterval.getDateFrom();
        Date dateTo = datesInterval.getDateTo();
        IntervalValidator interval = new IntervalValidator();
        interval.validate(datesInterval, bindingResult);
        Collection<FlightStub> flightStubs;
        if (bindingResult.hasErrors()) {
            LOGGER.error("StudentController - filtrStudents - dateFrom less then dateTo {}", datesInterval);
            flightStubs = flightService.findAllStubs();
            model.addAttribute("isError", true);
        } else {
            flightStubs = flightService.filteredFlightStubs(dateFrom, dateTo);
        }
        model.addAttribute("flights", flightStubs);
        model.addAttribute("datesInterval", datesInterval);
        return "flights";

    }

    @GetMapping(value = "/fight/{id}")
    public final String gotoEditFlightPage(@PathVariable Integer id, Model model) {

        LOGGER.debug("gotoEditFlightPage({},{})", id, model);
        Flight flight = flightService.findById(id);
        model.addAttribute("crew", flight);
        return "flight";
    }

    @PostMapping(value = "/flight/{id}")
    public final String updateFlight(@Valid Flight flight,
                                   BindingResult result) {

        LOGGER.debug("updateFlight({}, {})", flight, result);
        flightValidator.validate(flight, result);
        if (result.hasErrors()) {
            return "flight";
        } else {
            this.flightService.updateFlight(flight);
            return "redirect:/flights";
        }
    }

    @GetMapping(value = "/flight")
    public final String gotoAddFlightPage(Model model) {

        LOGGER.debug("create({})", model);
        Flight flight = new Flight();
        model.addAttribute("isNew", true);
        model.addAttribute("flight", flight);
        return "flight";
    }

    @PostMapping(value = "/flight")
    public String addFlight(@Valid Flight flight,
                                BindingResult result) {

        LOGGER.debug("addFlight({}, {})", flight, result);
        flightValidator.validate(flight, result);
        if (result.hasErrors()) {
            return "flight";
        } else {
            this.flightService.addFlight(flight);
            return "redirect:/flights";
        }
    }

    @GetMapping(value = "/flight/{id}/delete")
    public final String deleteFlightById(@PathVariable Integer id, Model model) {
        LOGGER.debug("delete({},{})", id, model);
        flightService.deleteFlight(id);
        return "redirect:/flights";
    }
}
