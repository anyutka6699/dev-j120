package ru.avalon.java_pp.dev_j120.io;

import ru.avalon.java_pp.dev_j120.config.Config;
import ru.avalon.java_pp.dev_j120.models.Product;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static ru.avalon.java_pp.dev_j120.config.Config.address_product;

public class ProductsStorage {


    public void saveProducts(List<Product> productsList) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(Config.address_product))) {
            for (Product p : productsList) {
                oos.writeObject(p);
            }
        }
    }
        public List<Product> productLoading (String file) throws IOException {
            List<Product> plist = new ArrayList<>();
            try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))) {
                Product p = (Product) reader.readObject();
                while (p!=null){
                    plist.add(p);
                    p = (Product) reader.readObject();
                }
            } catch (Exception e){
                return plist;
            }
            return plist;
        }
}


