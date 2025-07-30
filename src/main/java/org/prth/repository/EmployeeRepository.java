package org.prth.repository;

import org.prth.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // JpaRepository gives you basic CRUD methods
    List<Employee> findByDepartment(String department);
    List<Employee> findByCity(String city);
    List<Employee> findBySalaryGreaterThan(int salary);
}