package com.epam.courses.hr.web_app.validators;

import com.epam.courses.hr.model.Flight;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class FlightValidator implements Validator {

    public static final int DEPARTMENT_NAME_MAX_SIZE = 200;

    @Override
    public boolean supports(Class<?> clazz) {
        return Flight.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "flightName", "flightName.empty");
        Flight flight = (Flight) target;

        if (StringUtils.hasLength(flight.getFlightName())
                && flight.getFlightName().length() > DEPARTMENT_NAME_MAX_SIZE) {
            errors.rejectValue("flightName", "flightName.maxSize200");
        }
    }
}
