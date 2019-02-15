package com.epam.courses.hr.dao;

import com.epam.courses.hr.model.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:test-db.xml", "classpath:test-dao.xml"})
class DepartmentDaoJpaImplTest {

    @Autowired
    private DepartmentDao departmentDao;

    @Test
    void findAll() {
        Stream<Department> departments = departmentDao.findAll();
        assertNotNull(departments);
    }
}