package org.prth.dao.impl;

import org.prth.dao.EmployeeDao;
import org.prth.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    private final Connection connection;

    public EmployeeDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Employee getEmployeeById(int id) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM emp WHERE emp_id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp");
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void saveEmployee(Employee employee) {
        String sql = "INSERT INTO emp (name, email, city, salary, department, age) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getEmail());
            stmt.setString(3, employee.getCity());
            stmt.setInt(4, employee.getSalary());
            stmt.setString(5, employee.getDepartment());
            stmt.setInt(6, employee.getAge());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        String sql = "UPDATE emp SET name=?, email=?, city=?, salary=?, department=?, age=? WHERE emp_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getEmail());
            stmt.setString(3, employee.getCity());
            stmt.setInt(4, employee.getSalary());
            stmt.setString(5, employee.getDepartment());
            stmt.setInt(6, employee.getAge());
            stmt.setInt(7, employee.getEmpId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(int id) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM emp WHERE emp_id=?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Employee mapRow(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getInt("emp_id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("city"),
                rs.getInt("salary"),
                rs.getString("department"),
                rs.getInt("age")
        );
    }
}
