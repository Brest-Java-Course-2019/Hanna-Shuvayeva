package com.epam.courses.hr.dao;

import com.epam.courses.hr.Stub.DepartmentStub;
import com.epam.courses.hr.model.Department;

import java.util.Optional;
import java.util.stream.Stream;

public interface DepartmentDao {
    Stream<Department> findAll();

    Stream<DepartmentStub> findAllStubs();

    Optional<Department> findById(Integer departmentId);

    Optional<Department> add(Department department);

    Optional<Department> addDepartment(Department department);


    void update(Department department);

    void delete(int departmentId);

}