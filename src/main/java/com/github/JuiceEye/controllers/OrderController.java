package com.github.JuiceEye.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.JuiceEye.exceptions.InvalidLoginCredentialsException;
import com.github.JuiceEye.models.Department;
import com.github.JuiceEye.models.Order;
import com.github.JuiceEye.services.ClientService;
import com.github.JuiceEye.services.EmployeeService;
import com.github.JuiceEye.services.OrderService;
import io.javalin.http.Context;

import java.sql.SQLException;

public class OrderController {

    private OrderService OrderService;
    private ClientService ClientService;
    private EmployeeService EmployeeService;

    public void createOrder(Context context, ObjectMapper mapper) throws JsonProcessingException, SQLException, InvalidLoginCredentialsException {
        if (ClientService.defineClient(context).getClientId() == Integer.parseInt(context.pathParam("clientId")) ||
                EmployeeService.defineEmployeeDepartment(context) == Department.SALES) {
            OrderService.create(mapper.readValue(context.body(), Order.class));
        } else {
            context.result("Вы не можете просматривать чужие аккаунты");
        }
    }

    public void getOrder(Context context) throws JsonProcessingException, SQLException, InvalidLoginCredentialsException {
        if (ClientService.defineClient(context).getClientId() == Integer.parseInt(context.pathParam("clientId")) ||
                EmployeeService.defineEmployeeDepartment(context) == Department.SALES) {
            context.result(OrderService.read(Integer.parseInt(context.pathParam("orderId"))).toString());
        } else {
            context.result("Вы не можете просматривать чужие аккаунты");
        }
    }

    public void updateOrder(Context context, ObjectMapper mapper) throws JsonProcessingException, SQLException {
        if (EmployeeService.defineEmployeeDepartment(context) == Department.SALES) {
            Order o = mapper.readValue(context.body(), Order.class);
            o.setOrderId(Integer.parseInt(context.pathParam("orderId")));
            OrderService.update(o);
        } else {
            context.result("Вы не можете просматривать чужие аккаунты");
        }
    }

    public void deleteOrder(Context context) throws JsonProcessingException, SQLException {
        if (EmployeeService.defineEmployeeDepartment(context) == Department.SALES) {
            OrderService.delete(Integer.parseInt(context.pathParam("orderId")));
        } else {
            context.result("Вы не можете просматривать чужие аккаунты");
        }
    }

    public void getAllOrders(Context context) throws JsonProcessingException, SQLException {
        if (EmployeeService.defineEmployeeDepartment(context) == Department.SALES ||
                EmployeeService.defineEmployeeDepartment(context) == Department.PRODUCTION) {
            context.result(OrderService.getAll().toString());
        } else {
            context.result("Вы не можете просматривать чужие аккаунты");
        }
    }
}

/*done*/
