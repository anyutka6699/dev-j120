package ru.avalon.java_pp.dev_j120.io;

import ru.avalon.java_pp.dev_j120.config.Config;
import ru.avalon.java_pp.dev_j120.models.Product;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductsStorage {

    public void saveProducts(List<Product> productsList) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Config.address_product))) {
            for (Product p : productsList) {
                writer.write(p.getArt() + ";" + p.getName() + ";" +p.getColor()+";" + p.getPrice() + ";" + p.getQuantity());
                writer.newLine();
            }
        }
    }

    public List<Product> productLoading(String filePath) throws IOException {
        List<Product> listPproducts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String row;
            while ((row = reader.readLine()) != null) {
                String[] splittedRow = row.split(";");
                Product p = new Product(Long.valueOf(splittedRow[0]), splittedRow[1], splittedRow[2],
                        BigDecimal.valueOf(Long.parseLong(splittedRow[3])), Integer.valueOf(splittedRow[4]));
                listPproducts.add(p);
            }
        }
        return listPproducts;
    }
}


