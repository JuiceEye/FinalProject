package com.github.JuiceEye.models;

import com.j256.ormlite.field.DatabaseField;

import java.time.LocalDate;
import java.util.Objects;


public class Order {
    @DatabaseField(generatedId = true)
    private int orderId;
    @DatabaseField(columnName = "client", foreign = true)
    private Client client;
    @DatabaseField(columnName = "product", foreign = true)
    private Product product;
    @DatabaseField(columnName = "date")
    private LocalDate date;
    @DatabaseField(columnName = "address")
    private String address;
    @DatabaseField(columnName = "quantity")
    private int quantity;
    @DatabaseField(columnName = "price")
    private int price;
    @DatabaseField(columnName = "status")
    private OrderStatus status;


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Order() {

    }

    public Order(int orderId, Client client, Product product, LocalDate date, String address, int quantity, int price, OrderStatus status) {
        this.orderId = orderId;
        this.client = client;
        this.product = product;
        this.date = date;
        this.address = address;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId &&
                quantity == order.quantity &&
                price == order.price &&
                Objects.equals(client, order.client) &&
                Objects.equals(product, order.product) &&
                Objects.equals(date, order.date) &&
                Objects.equals(address, order.address) &&
                Objects.equals(status, order.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, client, product, date, address, quantity, price, status);
    }


    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", client=" + client +
                ", product=" + product +
                ", date=" + date +
                ", address='" + address + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}

/*done*/