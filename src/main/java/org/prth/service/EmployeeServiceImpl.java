package org.prth.service;

import org.prth.model.Employee;
import org.prth.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(int id, Employee updatedEmployee) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(updatedEmployee.getName());
                    employee.setEmail(updatedEmployee.getEmail());
                    employee.setCity(updatedEmployee.getCity());
                    employee.setSalary(updatedEmployee.getSalary());
                    employee.setDepartment(updatedEmployee.getDepartment());
                    employee.setAge(updatedEmployee.getAge());
                    return employeeRepository.save(employee);
                })
                .orElseGet(() -> {
                    updatedEmployee.setEmpId(id);
                    return employeeRepository.save(updatedEmployee);
                });
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    @Override
    public List<Employee> getEmployeesByCity(String city) {
        return employeeRepository.findByCity(city);
    }
    @Override
    public List<Employee> getEmployeesWithSalaryAbove(int salary) {
        return employeeRepository.findBySalaryGreaterThan(salary);
    }
}
