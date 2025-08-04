package org.prth.model;

public class Employee {
    private int id;
    private String name;
    private String email;
    private String city;
    private int salary;
    private String department;

    public Employee() {} // Required for frameworks

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Employee(int id, String name, String email, String city, int salary, String department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.city = city;
        this.salary = salary;
        this.department = department;
    }

    // Getters and setters...
}
