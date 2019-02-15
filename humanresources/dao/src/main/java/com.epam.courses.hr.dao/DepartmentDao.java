package com.epam.courses.hr.dao;

import com.epam.courses.hr.model.Department;

import java.util.Optional;
import java.util.stream.Stream;

public interface DepartmentDao {

    Stream<com.epam.courses.hr.model.Department> findAll();
    Optional<Department> findById(Integer departmentId);


}