package ru.avalon.java_pp.dev_j120.io;

import ru.avalon.java_pp.dev_j120.models.Order;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


import static ru.avalon.java_pp.dev_j120.config.Config.address_order;

public class OrdersStorage {

    public List<Order> readOrders() throws IOException, ClassNotFoundException{
        if (Files.exists(Paths.get(address_order))) {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(address_order));
            return (List<Order>) ois.readObject();
        } else {
            Files.createFile(Path.of(address_order));
           // ObjectOutputStream oos = new ObjectOutputStream(
           //         new FileOutputStream(address_order));
            ObjectInputStream inp = new ObjectInputStream(
                    new FileInputStream(address_order));
           return (List<Order>) inp.readObject();
        }
    }

    public void saveOrders(List<Order>orders) throws IOException {
                try (ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream(address_order))) {
                    oos.writeObject(orders);
                }
            }
        }
