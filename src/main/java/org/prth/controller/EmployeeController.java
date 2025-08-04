package org.prth.controller;

import org.prth.model.Employee;
import org.prth.service.EmployeeService;
import org.prth.service.EmployeeServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeController {

    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @GET
    @Path("/all")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "API is working!";
    }

    @GET
    @Path("/{id}")
    public Response getEmployeeById(@PathParam("id") int id) {
        Employee emp = employeeService.getEmployeeById(id);
        if (emp != null) {
            return Response.ok(emp).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Employee not found").build();
        }
    }

    @POST
    public Response addEmployee(Employee emp) {
        boolean added = employeeService.addEmployee(emp);
        if (added) {
            return Response.status(Response.Status.CREATED).entity(emp).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to create employee").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateEmployee(@PathParam("id") int id, Employee emp) {
        emp.setId(id);
        boolean updated = employeeService.updateEmployee(emp);
        if (updated) {
            return Response.ok(emp).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Employee not found").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEmployee(@PathParam("id") int id) {
        boolean deleted = employeeService.deleteEmployee(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Employee not found").build();
        }
    }
}