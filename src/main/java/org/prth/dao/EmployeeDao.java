package org.prth.dao;

import org.prth.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {
    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeById(int id);

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(int id, Employee employee);

    void deleteEmployee(int id);

    List<Employee> findByDepartment(String department);

    List<Employee> findByCity(String city);

    List<Employee> findBySalaryGreaterThan(int salary);
}