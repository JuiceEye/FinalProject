package com.github.JuiceEye;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.JuiceEye.controllers.ClientController;
import com.github.JuiceEye.controllers.EmployeeController;
import com.github.JuiceEye.controllers.OrderController;
import com.github.JuiceEye.controllers.ProductController;
import com.github.JuiceEye.deserializers.ClientDeserializer;
import com.github.JuiceEye.deserializers.EmployeeDeserializer;
import com.github.JuiceEye.models.Client;
import com.github.JuiceEye.models.Employee;
import com.github.JuiceEye.serializers.ClientSerializer;
import com.github.JuiceEye.serializers.EmployeeSerializer;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create();
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(new ClientSerializer());
        module.addSerializer(new EmployeeSerializer());
        module.addDeserializer(Client.class, new ClientDeserializer());
        module.addDeserializer(Employee.class, new EmployeeDeserializer());
        mapper.registerModule(module);

        //Команды для клиента
        app.get("/clients", context -> new ClientController().getAllClients(context));
        app.get("/client/:clientId", context -> new ClientController().getClient(context));
        app.post("/client", context -> new ClientController().createClient(context, mapper));
        app.patch("/client/:clientId", context -> new ClientController().updateClient(context, mapper));
        app.delete("/client/:clientId", context -> new ClientController().deleteClient(context));


        //Команды для работников
        app.get("/employees", context -> new EmployeeController().getAllEmployees(context));
        app.get("/employee/:employeeId", context -> new EmployeeController().getEmployee(context));
        app.post("/employee", context -> new EmployeeController().createEmployee(context, mapper));
        app.patch("/employee/:employeeId", context -> new EmployeeController().updateEmployee(context, mapper));
        app.delete("/employee/:employeeId", context -> new EmployeeController().deleteEmployee(context));


        //Команды для заказов
        app.get("/orders", context -> new OrderController().getAllOrders(context));
        app.get("/order/:orderId", context -> new OrderController().getOrder(context));
        app.post("/order", context -> new OrderController().createOrder(context, mapper));
        app.patch("/order/:orderId", context -> new OrderController().updateOrder(context, mapper));
        app.delete("/order/:orderId", context -> new OrderController().deleteOrder(context));


        //Команды для продуктов
        app.get("/products", context -> new ProductController().getAllProducts(context));
        app.get("/product/:productId", context -> new ProductController().getProduct(context));
        app.post("/product", context -> new ProductController().createProduct(context, mapper));
        app.patch("/product/:productId", context -> new ProductController().updateProduct(context, mapper));
        app.delete("/product/:productId", context -> new ProductController().deleteProduct(context));
        app.start(8080);
    }
}

/*done*/