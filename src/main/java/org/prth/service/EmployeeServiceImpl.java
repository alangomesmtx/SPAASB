package org.prth.service;

import org.prth.dao.EmployeeDao;
import org.prth.model.Employee;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeDao.getEmployeeById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeDao.addEmployee(employee);
    }

    @Override
    public Employee updateEmployee(int id, Employee employee) {
        return employeeDao.updateEmployee(id, employee);
    }

    @Override
    public boolean deleteEmployee(int id) {
        return employeeDao.deleteEmployee(id);
    }

    @Override
    public List<Employee> getEmployeesWithMinSalary(int minSalary) {
        return employeeDao.getEmployeesWithMinSalary(minSalary);
    }
}
