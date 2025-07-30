package org.prth.dao;

import org.prth.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM emp";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public Optional<Employee> getEmployeeById(int id) {
        String sql = "SELECT * FROM emp WHERE emp_id = ?";
        List<Employee> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class), id);
        return result.stream().findFirst();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        String sql = "INSERT INTO emp(name, email, city, salary, department, age) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, employee.getName(), employee.getEmail(), employee.getCity(),
                employee.getSalary(), employee.getDepartment(), employee.getAge());
        return employee;
    }

    @Override
    public Employee updateEmployee(int id, Employee employee) {
        String sql = "UPDATE emp SET name=?, email=?, city=?, salary=?, department=?, age=? WHERE emp_id=?";
        jdbcTemplate.update(sql, employee.getName(), employee.getEmail(), employee.getCity(),
                employee.getSalary(), employee.getDepartment(), employee.getAge(), id);
        employee.setEmpId(id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM emp WHERE emp_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Employee> findByDepartment(String department) {
        String sql = "SELECT * FROM emp WHERE department = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class), department);
    }

    @Override
    public List<Employee> findBySalaryGreaterThan(int salary) {
        String sql = "SELECT * FROM emp WHERE salary > ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class), salary);
    }
}