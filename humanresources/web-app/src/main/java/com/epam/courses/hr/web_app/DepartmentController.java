package com.epam.courses.hr.web_app;

import com.epam.courses.hr.model.Department;
import com.epam.courses.hr.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.stream.Collectors;

@Controller
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    @GetMapping(value = "/departments")
    public final String departments(Model model) {

        LOGGER.debug("findAll({})", model);
        model.addAttribute("departmentStubs", departmentService.findAllStubs()
                .collect(Collectors.toList()));
        return "departments";
    }


    @GetMapping(value = "/department/{id}")
    public final String gotoEditDepartmentPage(@PathVariable Integer id, Model model) {

        LOGGER.debug("gotoEditDepartmentPage({},{})", id, model);
        Department department = departmentService.findById(id);
        model.addAttribute("department", department);
        return "department";
    }


    @PostMapping(value = "/department/{id}")
    public String updateDepartment(Department department) {

        LOGGER.debug("update({}, {})", department);
        this.departmentService.update(department);
        return "redirect:/departments";
    }


    @GetMapping(value = "/department")
    public final String gotoAddDepartmentPage(Model model) {

        LOGGER.debug("create({})", model);
        Department department = new Department();
        model.addAttribute("isNew", true);
        model.addAttribute("department", department);
        return "department";
    }


    @PostMapping(value = "/department")
    public String addDepartment(Department department) {

        LOGGER.debug("create({})", department);
        this.departmentService.add(department);
        return "redirect:/departments";
    }
}
