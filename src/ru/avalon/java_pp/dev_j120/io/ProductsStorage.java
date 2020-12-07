package ru.avalon.java_pp.dev_j120.io;

import ru.avalon.java_pp.dev_j120.models.Product;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


import static ru.avalon.java_pp.dev_j120.config.Config.address_product;

public class ProductsStorage {

    public void saveProducts(Product save) throws IOException {
        Path myPath = Paths.get(address_product);
        List<String> line = new ArrayList<>();
        Files.write(myPath, line, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
    }
}

