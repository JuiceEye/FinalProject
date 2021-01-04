package com.github.JuiceEye.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.JuiceEye.exceptions.InvalidLoginCredentialsException;
import com.github.JuiceEye.models.Client;
import com.github.JuiceEye.models.Department;
import com.github.JuiceEye.services.ClientService;
import com.github.JuiceEye.services.EmployeeService;
import io.javalin.http.Context;

import java.sql.SQLException;

public class ClientController {

    private ClientService ClientService;
    private EmployeeService EmployeeService;

    public void createClient(Context context, ObjectMapper mapper) throws JsonProcessingException, SQLException {
            ClientService.create(mapper.readValue(context.body(), Client.class));
    }

    public void getClient(Context context) throws JsonProcessingException, SQLException {
        try {
            if (ClientService.defineClient(context).getClientId() == Integer.parseInt(context.pathParam("clientId")) ||
                    EmployeeService.defineEmployeeDepartment(context) == Department.MANAGER) {
                context.result(ClientService.read(Integer.parseInt(context.pathParam("clientId"))).toString());
            } else {
                context.result("Вы не можете просматривать чужие аккаунты");
            }
        }
        catch (InvalidLoginCredentialsException ilce) {
            context.result("Неверный логин или пароль");
        }
    }

    public void updateClient(Context context, ObjectMapper mapper) throws JsonProcessingException, SQLException {
        try {
            if (ClientService.defineClient(context).getClientId() == Integer.parseInt(context.pathParam("clientId")) ||
                    EmployeeService.defineEmployeeDepartment(context) == Department.MANAGER) {
                Client c = mapper.readValue(context.body(), Client.class);
                c.setClientId(Integer.parseInt(context.pathParam("clientId")));
                ClientService.update(c);
            } else {
                context.result("Вы не можете обновить данные чужого аккаунта");
            }
        } catch (InvalidLoginCredentialsException ilce) {
            context.result("Неверный логин или пароль");
        }
    }

    public void deleteClient(Context context) throws JsonProcessingException, SQLException {
        try {
            if (ClientService.defineClient(context).getClientId() == Integer.parseInt(context.pathParam("clientId")) ||
                    EmployeeService.defineEmployeeDepartment(context) == Department.MANAGER) {
                ClientService.delete(Integer.parseInt(context.pathParam("clientId")));
            } else {
                context.result("Вы не можете удалять чужие аккаунты");
            }
        } catch (InvalidLoginCredentialsException ilce) {
            context.result("Неверный логин или пароль");
        }
    }

    public void getAllClients(Context context) throws JsonProcessingException, SQLException {
        if (EmployeeService.defineEmployeeDepartment(context) == Department.MANAGER) {
            context.result(ClientService.getAll().toString());
        } else {
            context.result("У вас недостаточно прав для совершения этого действия");
        }
    }
}

/*done*/