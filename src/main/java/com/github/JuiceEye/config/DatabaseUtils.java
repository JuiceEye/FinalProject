package com.github.JuiceEye.config;

import com.github.JuiceEye.models.Client;
import com.github.JuiceEye.models.Employee;
import com.github.JuiceEye.models.Order;
import com.github.JuiceEye.models.Product;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;

public class DatabaseUtils {


    public static String JDBC_CONNECTION_STRING = "jdbc:sqlite:C:\\Пользователи\\coolf\\Desktop\\database\\ShopDatabase.sqlite";

    public static ConnectionSource CONNECTION_SOURCE;

    static {
        try {
            CONNECTION_SOURCE = new JdbcConnectionSource(JDBC_CONNECTION_STRING);
            TableUtils.createTableIfNotExists(CONNECTION_SOURCE, Client.class);
            TableUtils.createTableIfNotExists(CONNECTION_SOURCE, Employee.class);
            TableUtils.createTableIfNotExists(CONNECTION_SOURCE, Order.class);
            TableUtils.createTableIfNotExists(CONNECTION_SOURCE, Product.class);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

/*done*/