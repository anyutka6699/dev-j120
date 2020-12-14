package ru.avalon.java_pp.dev_j120.controllers;


import ru.avalon.java_pp.dev_j120.models.Order;
import ru.avalon.java_pp.dev_j120.models.OrderPosition;

import java.time.LocalDate;
import java.util.*;

public class OrdersController {
    private List<Order> orders = new ArrayList<>();

    public void add(LocalDate date1, String fio, int phone, String address, int percent, Order.Status status, LocalDate date2, OrderPosition orderPosition) {
        orders.add(new Order(date1, fio, phone, address, percent,status, date2, orderPosition));
    }
    
    public void remove(int index) {
        Order o = orders.remove(index);
    }

    public int getOrdersCount() {
        return orders.size();
    }

    public Order get(int index) {
        return orders.get(index);
    }
}
