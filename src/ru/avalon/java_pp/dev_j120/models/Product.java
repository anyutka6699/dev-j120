package ru.avalon.java_pp.dev_j120.models;


import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private long art;
    private String name;
    private String color;
    private BigDecimal price;
    private int quantity;

    public Product(long art, String name, String color, BigDecimal price, int quantity) {
        this.art = art;
        this.name = name;
        this.color = color;
        this.price = price;
        this.setQuantity (quantity);
    }

    public long getArt() {
        return art;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
