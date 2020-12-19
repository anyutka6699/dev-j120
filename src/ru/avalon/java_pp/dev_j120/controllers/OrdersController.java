package ru.avalon.java_pp.dev_j120.controllers;
import ru.avalon.java_pp.dev_j120.config.Config;
import ru.avalon.java_pp.dev_j120.models.Order;
import ru.avalon.java_pp.dev_j120.models.OrderPosition;
import ru.avalon.java_pp.dev_j120.io.OrdersStorage;
import java.io.IOException;
import java.util.*;

public class OrdersController {
    public OrdersStorage ordersStorage = new OrdersStorage();
    private List<Order> orders = ordersStorage.ordersLoading(Config.address_order);
    private List<Order> clients = new ArrayList<>();
    private Set<Order> order = new HashSet<>();


    public void add(Date date1, String fio, Integer phone, String address,
                    Integer percent, Order.Status status, Date date2, List<OrderPosition> orderPosition) {
        int amountProducts = 0;
        for (OrderPosition op : orderPosition) {
            amountProducts += op.getQuantity();
        }
        orders.add(new Order(new Date(), fio, phone, address, status,
                new Date(), orderPosition));
    }

    public void remove(int index) {
        Order c = orders.remove(index);
    }

    public void save() throws IOException {
        ordersStorage.saveOrders(orders);
    }

    public int getOrdersQ() {
        return this.orders.size();
    }

    public Order getOrder(int index) {
        return orders.get(index);
    }

}
