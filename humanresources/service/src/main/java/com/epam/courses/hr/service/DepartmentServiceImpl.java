package com.epam.courses.hr.service;

import com.epam.courses.hr.dao.DepartmentDao;
import com.epam.courses.hr.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Transactional
public class DepartmentServiceImpl implements DepartmentService{

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private DepartmentDao dao;

    public DepartmentServiceImpl(DepartmentDao dao) {
        this.dao = dao;
    }

    @Override
    public Stream<Department> findAll() {
        LOGGER.debug("Find all departments");
        return dao.findAll();
    }

    @Override
    public void add(Department... departments) {
        for (Department department : departments) {
            dao.addDepartment(department);
        }
    }
}