package com.github.JuiceEye.services;

import com.github.JuiceEye.config.DatabaseUtils;
import com.github.JuiceEye.models.Order;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;
import java.util.List;

public class OrderService implements Handler {


    public Dao<Order, Integer> orderDao() throws SQLException {
        return DaoManager.createDao(DatabaseUtils.CONNECTION_SOURCE, Order.class);
    }

    @Override
    public void create(Object objectOrder) throws SQLException {
        orderDao().create((Order) objectOrder);
    }

    @Override
    public Object read(int id) throws SQLException {
        return orderDao().queryForId(id);
    }

    @Override
    public void update(Object objectOrder) throws SQLException {
        orderDao().update((Order) objectOrder);
    }

    @Override
    public void delete(int id) throws SQLException {
        orderDao().delete(orderDao().queryForId(id));
    }

    @Override
    public List<?> getAll() throws SQLException {
        return orderDao().queryForAll();
    }
}

/*done*/