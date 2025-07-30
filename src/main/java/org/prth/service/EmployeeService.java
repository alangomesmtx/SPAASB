package org.prth.service;

import org.prth.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeById(int id);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(int id, Employee employee);

    void deleteEmployee(int id);

    List<Employee> getEmployeesByDepartment(String department);

    List<Employee> getEmployeesByCity(String city);

    List<Employee> getEmployeesWithSalaryGreaterThan(int salary);
}