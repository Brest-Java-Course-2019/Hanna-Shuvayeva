package com.epam.courses.hr.web_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {


    @GetMapping(value = "/employees")
    public String employees (Model model) {
        return "employees";
    }

    @GetMapping(value = "/employee")
    public String employee (Model model) {
        return "employee";
    }


}
