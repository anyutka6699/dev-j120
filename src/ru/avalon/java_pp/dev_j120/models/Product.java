package ru.avalon.java_pp.dev_j120.models;

import javax.annotation.processing.Generated;

public class Product {
    private int art;
    private String name;
    private String color;
    private Float price;
    private int quantity;

    public Product(int art, String name, String color, Float price, int quantity){
        this.setArt (art);
        this.setName (name);
        this.setColor (color);
        this.setPrice (price);
        this.setQuantity (quantity);
    }

    public int getArt() {
        return art;
    }

    public void setArt(int art) {
        this.art = art;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

// Обеспечение уникальности
//    int art = 0;
//
//    int getUniqueId()
//    {
//        return art++;
//    }




}
