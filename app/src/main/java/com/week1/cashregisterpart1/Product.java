package com.week1.cashregisterpart1;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private int quantity;
    private double price;

    public Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name=name;
    }

    public void setQuantity(int quantity) {
        this.quantity=quantity;
    }

    public void setPrice(double price) {
        this.price=price;
    }
}
