package org.prth.dao;

import org.prth.config.AppConfig;
import org.prth.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    private final Connection connection;

    public EmployeeDaoImpl() throws Exception {
        this.connection = AppConfig.getConnection();
    }

    @Override
    public Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM emp WHERE emp_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToEmployee(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM emp";
        List<Employee> employees = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                employees.add(mapResultSetToEmployee(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        String sql = "INSERT INTO emp (name, email, city, salary, department, age) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getEmail());
            stmt.setString(3, employee.getCity());
            stmt.setInt(4, employee.getSalary());
            stmt.setString(5, employee.getDepartment());
            stmt.setInt(6, employee.getAge());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                employee.setEmpId(keys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public Employee updateEmployee(int id, Employee employee) {
        Employee existing = getEmployeeById(id);
        if (existing == null) return null;

        String sql = "UPDATE emp SET name = ?, email = ?, city = ?, salary = ?, department = ?, age = ? WHERE emp_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, employee.getName() != null ? employee.getName() : existing.getName());
            stmt.setString(2, employee.getEmail() != null ? employee.getEmail() : existing.getEmail());
            stmt.setString(3, employee.getCity() != null ? employee.getCity() : existing.getCity());
            stmt.setInt(4, employee.getSalary() != 0 ? employee.getSalary() : existing.getSalary());
            stmt.setString(5, employee.getDepartment() != null ? employee.getDepartment() : existing.getDepartment());
            stmt.setInt(6, employee.getAge() != 0 ? employee.getAge() : existing.getAge());
            stmt.setInt(7, id);
            stmt.executeUpdate();

            return getEmployeeById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteEmployee(int id) {
        String sql = "DELETE FROM emp WHERE emp_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Employee> getEmployeesWithMinSalary(int minSalary) {
        String sql = "SELECT * FROM emp WHERE salary >= ?";
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, minSalary);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                employees.add(mapResultSetToEmployee(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public List<Employee> getEmployeesByCity(String city){
        String sql = "SELECT * FROM emp WHERE city = ?";
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, city);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                employees.add(mapResultSetToEmployee(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private Employee mapResultSetToEmployee(ResultSet rs) throws SQLException {
        Employee emp = new Employee();
        emp.setEmpId(rs.getInt("emp_id"));
        emp.setName(rs.getString("name"));
        emp.setEmail(rs.getString("email"));
        emp.setCity(rs.getString("city"));
        emp.setSalary(rs.getInt("salary"));
        emp.setDepartment(rs.getString("department"));
        emp.setAge(rs.getInt("age"));
        return emp;
    }
}