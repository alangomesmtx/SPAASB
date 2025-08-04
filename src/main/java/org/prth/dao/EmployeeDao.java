package org.prth.dao;

import org.prth.model.Employee;
import java.util.List;

public interface EmployeeDao {
    Employee getEmployeeById(int id);
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployee(int id);
}
