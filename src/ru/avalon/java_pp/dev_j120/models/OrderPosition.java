package ru.avalon.java_pp.dev_j120.models;

import java.util.Date;
import java.io.Serializable;


public class OrderPosition implements Serializable {
    private static final long serialVersionUID = 1L;
    private Product product;
    private int quantity;

    public OrderPosition(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }




    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public long getArt() {
        return product.getArt();
    }

    public String getName() {
        return product.getName();
    }

    public String getColor() {
        return product.getColor();
    }

    public Object getPrice() {return product.getPrice();
    }
}
