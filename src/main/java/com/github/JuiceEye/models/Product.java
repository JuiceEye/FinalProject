package com.github.JuiceEye.models;

import com.j256.ormlite.field.DatabaseField;

import java.util.Objects;

public class Product {
    @DatabaseField(generatedId = true)
    private int productId;
    @DatabaseField
    private String productName;
    @DatabaseField
    private String description;
    @DatabaseField(foreign = true)
    private Employee created_by;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getCreated_by() {
        return created_by;
    }

    public void setCreated_by(Employee created_by) {
        this.created_by = created_by;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId &&
                Objects.equals(productName, product.productName) &&
                Objects.equals(description, product.description) &&
                Objects.equals(created_by, product.created_by);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, description, created_by);
    }

    public Product(int productId, String productName, String description, Employee created_by) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.created_by = created_by;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", created_by=" + created_by +
                '}';
    }
}

/*done*/