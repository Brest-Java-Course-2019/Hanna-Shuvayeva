package com.epam.courses.hr.web_app.validators;

import com.epam.courses.hr.model.Crew;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CrewValidator implements Validator {

    public static final int DEPARTMENT_NAME_MAX_SIZE = 200;

    @Override
    public boolean supports(Class<?> clazz) {
        return Crew.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "fullName", "fullName.empty");
        Crew crew = (Crew) target;

        if (StringUtils.hasLength(crew.getFullName())
                && crew.getFullName().length() > DEPARTMENT_NAME_MAX_SIZE) {
            errors.rejectValue("fullName", "flightName.maxSize200");
        }
    }
}
