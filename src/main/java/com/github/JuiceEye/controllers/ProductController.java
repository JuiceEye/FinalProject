package com.github.JuiceEye.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.JuiceEye.exceptions.InvalidLoginCredentialsException;
import com.github.JuiceEye.models.Client;
import com.github.JuiceEye.models.Department;
import com.github.JuiceEye.models.Product;
import com.github.JuiceEye.services.EmployeeService;
import com.github.JuiceEye.services.ProductService;
import io.javalin.http.Context;

import java.sql.SQLException;

public class ProductController {

    private ProductService ProductService;
    private EmployeeService EmployeeService;
    private com.github.JuiceEye.services.ClientService ClientService;


    public void createProduct(Context context, ObjectMapper mapper) throws JsonProcessingException, SQLException {
        if (EmployeeService.defineEmployeeDepartment(context) == Department.PRODUCTION) {
            ProductService.create(mapper.readValue(context.body(), Product.class));
        } else {
            context.result("У вас недостаточно прав для совершения этого действия");
        }
    }

    public void getProduct(Context context) throws JsonProcessingException, SQLException, InvalidLoginCredentialsException {
        if (EmployeeService.defineEmployeeDepartment(context) == Department.PRODUCTION ||
        ClientService.defineClient(context).getClass() == Client.class) {
            context.result(ProductService.read(Integer.parseInt(context.pathParam("productId"))).toString());
        } else {
            context.result("У вас недостаточно прав для совершения этого действия");
        }
    }

    public void updateProduct(Context context, ObjectMapper mapper) throws JsonProcessingException, SQLException {
        if (EmployeeService.defineEmployeeDepartment(context) == Department.PRODUCTION) {
            Product p = mapper.readValue(context.body(), Product.class);
            p.setProductId(Integer.parseInt(context.pathParam("productId")));
            ProductService.update(p);
        } else {
            context.result("У вас недостаточно прав для совершения этого действия");
        }
    }

    public void deleteProduct(Context context) throws JsonProcessingException, SQLException {
        if (EmployeeService.defineEmployeeDepartment(context) == Department.PRODUCTION) {
            ProductService.delete(Integer.parseInt(context.pathParam("productId")));
        } else {
            context.result("У вас недостаточно прав для совершения этого действия");
        }
    }

    public void getAllProducts(Context context) throws JsonProcessingException, SQLException, InvalidLoginCredentialsException {
        if (EmployeeService.defineEmployeeDepartment(context) == Department.PRODUCTION ||
                ClientService.defineClient(context).getClass() == Client.class) {
            context.result(ProductService.getAll().toString());
        } else {
            context.result("У вас недостаточно прав для совершения этого действия");
        }
    }
}

/*done*/
