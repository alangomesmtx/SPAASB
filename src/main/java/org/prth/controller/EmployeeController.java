package org.prth.controller;

import org.prth.model.Employee;
import org.prth.service.EmployeeService;
import org.prth.service.EmployeeServiceImpl;
import org.prth.dao.EmployeeDaoImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeController {

    private final EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDaoImpl());

    public EmployeeController() throws Exception {
    }

    @GET
    public Response getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return Response.ok(employees).build();
    }

    @GET
    @Path("/id")
    public Response getEmployeeById(@QueryParam("id") int id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return Response.ok(employee).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Employee not found").build();
        }
    }

    @POST
    @Path("/add")
    public Response addEmployee(Employee employee) {
        Employee savedEmployee = employeeService.addEmployee(employee);
        return Response.status(Response.Status.CREATED).entity(savedEmployee).build();
    }

    @PUT
    @Path("/update/id")
    public Response updateEmployee(@QueryParam("id") int id, Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        return Response.ok(updatedEmployee).build();
    }

    @DELETE
    @Path("/delete/id")
    public Response deleteEmployee(@QueryParam("id") int id) {
        Employee employee = employeeService.getEmployeeById(id);
        boolean deleted = employeeService.deleteEmployee(id);
        if (deleted) {
            return Response.status(Response.Status.OK).entity(employee).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Employee not found").build();
        }
    }

    @GET
    @Path("/getEmployeesWithMinSalary")
    public Response getEmployeesWithMinSalary(@QueryParam("minSalary") int minSalary) {
        List<Employee> employees = employeeService.getEmployeesWithMinSalary(minSalary);
        return Response.ok(employees).build();
    }

    @GET
    @Path("/getEmployeesByCity")
    public Response getEmployeesByCity(@QueryParam("city") String city) {
        List<Employee> employees = employeeService.getEmployeesByCity(city);
        return Response.ok(employees).build();
    }
}