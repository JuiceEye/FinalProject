package com.github.JuiceEye.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.JuiceEye.models.Employee;
import com.github.JuiceEye.models.Department;
import com.github.JuiceEye.services.EmployeeService;
import io.javalin.http.Context;

import java.sql.SQLException;

public class EmployeeController {

    private EmployeeService EmployeeService;

    public void createEmployee(Context context, ObjectMapper mapper) throws JsonProcessingException, SQLException {
        if (EmployeeService.defineEmployeeDepartment(context) == Department.MANAGER) {
            EmployeeService.create(mapper.readValue(context.body(), Employee.class));
        }
        else {
            context.result("У вас недостаточно прав для совершения этого действия");
        }
    }

    public void getEmployee(Context context) throws JsonProcessingException, SQLException {
        if (EmployeeService.defineEmployeeDepartment(context) == Department.MANAGER) {
            context.result(EmployeeService.read(Integer.parseInt(context.pathParam("employeeId"))).toString());
        }
        else {
            context.result("У вас недостаточно прав для совершения этого действия");
        }
    }

    public void updateEmployee(Context context, ObjectMapper mapper) throws JsonProcessingException, SQLException {

        if (EmployeeService.defineEmployeeDepartment(context) == Department.MANAGER) {
            Employee e = mapper.readValue(context.body(), Employee.class);
            e.setEmployeeId(Integer.parseInt(context.pathParam("employeeId")));
            EmployeeService.update(e);
        }
        else {
            context.result("У вас недостаточно прав для совершения этого действия");
        }
    }

    public void deleteEmployee(Context context) throws JsonProcessingException, SQLException {
        if (EmployeeService.defineEmployeeDepartment(context) == Department.MANAGER) {
            EmployeeService.delete(Integer.parseInt(context.pathParam("employeeId")));
        }
        else {
            context.result("У вас недостаточно прав для совершения этого действия");
        }
    }

    public void getAllEmployees(Context context) throws JsonProcessingException, SQLException {
        if (EmployeeService.defineEmployeeDepartment(context) == Department.MANAGER) {
            context.result(EmployeeService.getAll().toString());
        }
        else {
            context.result("У вас недостаточно прав для совершения этого действия");
        }
    }
}

/*done*/
