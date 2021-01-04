package com.github.JuiceEye.services;

import com.github.JuiceEye.config.DatabaseUtils;
import com.github.JuiceEye.models.Product;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import java.sql.SQLException;
import java.util.List;

public class ProductService implements Handler {
    public Dao<Product, Integer> productDao() throws SQLException {
        return DaoManager.createDao(DatabaseUtils.CONNECTION_SOURCE, Product.class);
    }


    @Override
    public void create(Object objectProduct) throws SQLException {
        productDao().create((Product) objectProduct);
    }

    @Override
    public Object read(int id) throws SQLException {
        return productDao().queryForId(id);
    }

    @Override
    public void update(Object objectProduct) throws SQLException {
        productDao().update((Product) objectProduct);
    }

    @Override
    public void delete(int id) throws SQLException {
        productDao().delete(productDao().queryForId(id));
    }

    @Override
    public List<?> getAll() throws SQLException {
        return productDao().queryForAll();
    }
}

/*done*/