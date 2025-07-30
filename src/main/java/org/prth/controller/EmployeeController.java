package org.prth.controller;

import org.prth.model.Employee;
import org.prth.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    // Create employee
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    // Update employee
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    // Delete employee
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }

    // Find employees by department
    @GetMapping("/department")
    public List<Employee> getByDepartment(@RequestParam String department) {
        return employeeService.getEmployeesByDepartment(department);
    }

    // Find employees with salary greater than X
    @GetMapping("/salary")
    public List<Employee> getWithSalaryGreaterThan(@RequestParam int minSalary) {
        return employeeService.getEmployeesWithSalaryGreaterThan(minSalary);
    }
}