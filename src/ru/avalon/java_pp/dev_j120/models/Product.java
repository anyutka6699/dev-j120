package ru.avalon.java_pp.dev_j120.models;


public class Product {
    private int art;
    private String name;
    private String color;
    private Float price;
    private int quantity;

    public Product(int art, String name, String color, Float price, int quantity) {
        this.art = art;
        this.name = name;
        this.color = color;
        this.price = price;
        this.setQuantity (quantity);
    }


    public int getArt() {
        return art;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public Float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



}
