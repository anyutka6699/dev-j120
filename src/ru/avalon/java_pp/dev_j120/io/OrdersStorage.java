package ru.avalon.java_pp.dev_j120.io;

import ru.avalon.java_pp.dev_j120.models.Order;
import ru.avalon.java_pp.dev_j120.models.Product;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import static ru.avalon.java_pp.dev_j120.config.Config.address_order;

public class OrdersStorage {


    public List<Order> readOrders() throws IOException, ClassNotFoundException {
        if (Files.exists(Paths.get(address_order))) {
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(address_order))) {
                return (List<Order>) ois.readObject();
            }
        } else {
            return new ArrayList<>();
        }
    }

        public void saveOrders (List<Order> orders) throws IOException {
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(address_order))) {
                for (Order o : orders) {
                    oos.writeObject(o);
                }
            }
        }

    public List<Order> ordersLoading(String file) {
        List<Order> ordersList = new ArrayList<>();
        if (Files.exists(Paths.get(address_order))) {
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(file))) {
                Order o = (Order) ois.readObject();
                while (o != null) {
                    ordersList.add(o);
                    o = (Order) ois.readObject();
                }
                return ordersList;
            } catch (EOFException ex) {
                return ordersList;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return ordersList;
        }
    }
}

