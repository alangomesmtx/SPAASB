package org.prth.model;

public class Employee {
    private int empId;
    private String name;
    private String email;
    private String city;
    private int salary;
    private String department;
    private int age;

    public Employee() {
    }

    public Employee(int empId, String name, String email, String city, int salary, String department, int age) {
        this.empId = empId;
        this.name = name;
        this.email = email;
        this.city = city;
        this.salary = salary;
        this.department = department;
        this.age = age;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
