package com.epam.courses.hr.dao;
import com.epam.courses.hr.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Optional;
import java.util.stream.Stream;

public class DepartmentDaoJpaImpl implements DepartmentDao{

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDaoJpaImpl.class);
    final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DepartmentDaoJpaImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Stream<com.epam.courses.hr.model.Department> findAll() {
        LOGGER.debug("findAll()");
        return null;
    }

    @Override
    public Optional<Department> findById(Integer departmentId) {
        return Optional.empty();
    }
}
