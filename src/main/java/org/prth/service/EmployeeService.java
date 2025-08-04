package org.prth.service;

import org.prth.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getEmployeeById(int id);
    List<Employee> getAllEmployees();
    Employee addEmployee(Employee employee);
    Employee updateEmployee(int id, Employee employee);
    boolean deleteEmployee(int id);
    List<Employee> getEmployeesWithMinSalary(int minSalary);
    List<Employee> getEmployeesByCity(String city);
}
