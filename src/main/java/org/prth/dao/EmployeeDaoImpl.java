package org.prth.dao;

import org.prth.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

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

//    @Override
//    public Employee saveEmployee(Employee employee) {
//        String sql = "INSERT INTO emp(name, email, city, salary, department, age) VALUES (?, ?, ?, ?, ?, ?)";
//        jdbcTemplate.update(sql, employee.getName(), employee.getEmail(), employee.getCity(),
//                employee.getSalary(), employee.getDepartment(), employee.getAge());
//        String sql_get_id = ""
//        return employee;
//    }

    @Override
    public Employee saveEmployee(Employee employee) {
        String sql = "INSERT INTO emp(name, email, city, salary, department, age) VALUES (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getCity());
            ps.setInt(4, employee.getSalary());
            ps.setString(5, employee.getDepartment());
            ps.setInt(6, employee.getAge());
            return ps;
        }, keyHolder);

        // Set the generated empId to the employee object
        Number generatedId = keyHolder.getKey();
        if (generatedId != null) {
            employee.setEmpId(generatedId.intValue());
        }

        return employee;
    }


    @Override
    public Employee updateEmployee(int id, Employee employee) {
        // Step 1: Fetch current employee from DB
        String selectSql = "SELECT * FROM emp WHERE emp_id = ?";
        Employee existingEmployee = jdbcTemplate.queryForObject(selectSql,
                new BeanPropertyRowMapper<>(Employee.class), id);

        if (existingEmployee == null) {
            throw new RuntimeException("Employee with id " + id + " not found");
        }

        // Step 2: Fill missing fields with existing values
        String name = (employee.getName() != null) ? employee.getName() : existingEmployee.getName();
        String email = (employee.getEmail() != null) ? employee.getEmail() : existingEmployee.getEmail();
        String city = (employee.getCity() != null) ? employee.getCity() : existingEmployee.getCity();
        int salary = (employee.getSalary() != 0) ? employee.getSalary() : existingEmployee.getSalary();
        String department = (employee.getDepartment() != null) ? employee.getDepartment() : existingEmployee.getDepartment();
        int age = (employee.getAge() != 0) ? employee.getAge() : existingEmployee.getAge();

        // Step 3: Update with merged values
        String updateSql = "UPDATE emp SET name=?, email=?, city=?, salary=?, department=?, age=? WHERE emp_id=?";
        jdbcTemplate.update(updateSql, name, email, city, salary, department, age, id);

        // Set and return updated employee
        return new Employee(id, name, email, city, salary, department, age);
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
    public List<Employee> findByCity(String city) {
        String sql = "SELECT * FROM emp WHERE city = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class), city);
    }

    @Override
    public List<Employee> findBySalaryGreaterThan(int salary) {
        String sql = "SELECT * FROM emp WHERE salary > ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class), salary);
    }
}