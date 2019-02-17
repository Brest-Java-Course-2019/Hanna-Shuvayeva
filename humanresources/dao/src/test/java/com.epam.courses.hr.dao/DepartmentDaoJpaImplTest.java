package com.epam.courses.hr.dao;

import com.epam.courses.hr.model.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:test-db.xml", "classpath:test-dao.xml"})
class DepartmentDaoJpaImplTest {

    private static final String DEVELOPMENT_DEPARTMENT = "Development Department";
    private static final String DEV = "DEV";
    private static final int FIRST_DEPARTMENT_ID = 1;
    public static final int FULL_DEPARTMENT_LIST = 4;
    @Autowired
    private DepartmentDao departmentDao;

    @Test
    void findAll() {
        Stream<Department> departments = departmentDao.findAll();
        assertNotNull(departments);
    }

    @Test
    void findAllCheckCount() {
        Stream<Department> departments = departmentDao.findAll();
        assertNotNull(departments);
        assertEquals(FULL_DEPARTMENT_LIST, departments.count());
    }

    @Test
    void findById() {
        Department department = departmentDao.findById(1).get();
        assertNotNull(department);
        assertEquals(FIRST_DEPARTMENT_ID, department.getDepartmentId().intValue());
        assertEquals(DEV, department.getDepartmentName());
        assertEquals(DEVELOPMENT_DEPARTMENT, department.getDepartmentDescription());
    }

}