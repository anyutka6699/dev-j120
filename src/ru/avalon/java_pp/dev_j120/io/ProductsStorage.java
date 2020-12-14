package ru.avalon.java_pp.dev_j120.io;

import ru.avalon.java_pp.dev_j120.models.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import static ru.avalon.java_pp.dev_j120.config.Config.address_product;

public class ProductsStorage {


    public void saveProducts(List<Product> productsList) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(address_product))) {
            for (Product p : productsList) {
                oos.writeObject(p);
            }
        }
    }
        public List<Product> productLoading (List<Product> productList) throws IOException, ClassNotFoundException {
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(address_product))) {
                Product p = (Product) ois.readObject();
                productList.add(p);
            } catch (FileNotFoundException e) {
                return productList;
            }
            return productList;
        }
}


