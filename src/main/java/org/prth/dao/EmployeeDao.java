package org.prth.dao;

import org.prth.model.Employee;

import java.util.List;

public interface EmployeeDao {

    Employee getEmployeeById(int id);

    List<Employee> getAllEmployees();

    Employee addEmployee(Employee employee);

    Employee updateEmployee(int id, Employee employee);

    boolean deleteEmployee(int id);

    List<Employee> getEmployeesWithMinSalary(int minSalary);
}