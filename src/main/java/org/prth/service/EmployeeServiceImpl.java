package org.prth.service;

import org.prth.dao.EmployeeDao;
import org.prth.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    public Optional<Employee> getEmployeeById(int id) {
        return employeeDao.getEmployeeById(id);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeDao.saveEmployee(employee);
    }

    @Override
    public Employee updateEmployee(int id, Employee employee) {
        return employeeDao.updateEmployee(id, employee);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeDao.deleteEmployee(id);
    }

    @Override
    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeDao.findByDepartment(department);
    }

    @Override
    public List<Employee> getEmployeesByCity(String city) {
        return employeeDao.findByCity(city);
    }

    @Override
    public List<Employee> getEmployeesWithSalaryGreaterThan(int salary) {
        return employeeDao.findBySalaryGreaterThan(salary);
    }
}