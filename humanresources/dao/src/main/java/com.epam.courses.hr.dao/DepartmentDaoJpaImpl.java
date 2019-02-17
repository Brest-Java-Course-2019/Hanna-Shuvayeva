package com.epam.courses.hr.dao;

import com.epam.courses.hr.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class DepartmentDaoJpaImpl implements DepartmentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDaoJpaImpl.class);
    private static final String SELECT_ALL="select departmentId, departmentName, departmentDescription from department";
    private static final String FIND_BY_ID = "select departmentId, departmentName, departmentDescription from department where departmentId = :departmentId";
    private static final String DEPARTMENT_ID = "departmentId";
    private static final String DEPARTMENT_NAME = "departmentName";
    private static final String DEPARTMENT_DESCRIPTION = "departmentDescription";

    final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DepartmentDaoJpaImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Stream<Department> findAll() {
        LOGGER.debug("findAll()");
        List<Department> departmentList = namedParameterJdbcTemplate.query(SELECT_ALL, new DepartmentRowMapper());
        return departmentList.stream();
    }

    @Override
    public Optional<Department> findById(Integer departmentId) {
        LOGGER.debug("findById({})", departmentId);
        SqlParameterSource namedParameters = new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
        Department department = namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(Department.class));
        return Optional.ofNullable(department);
    }

    private class DepartmentRowMapper implements RowMapper<Department> {

        @Override
        public Department mapRow(ResultSet resultSet, int i) throws SQLException {
            Department department = new Department();
            department.setDepartmentId(resultSet.getInt(DEPARTMENT_ID));
            department.setDepartmentName(resultSet.getString(DEPARTMENT_NAME));
            department.setDepartmentDescription(resultSet.getString(DEPARTMENT_DESCRIPTION));
            return department;
        }
    }
}
