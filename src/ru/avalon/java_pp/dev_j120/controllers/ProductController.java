package ru.avalon.java_pp.dev_j120.controllers;
import ru.avalon.java_pp.dev_j120.config.Config;
import ru.avalon.java_pp.dev_j120.io.ProductsStorage;
import ru.avalon.java_pp.dev_j120.models.Product;
import java.io.IOException;
import java.util.List;

public class ProductController {
    public ProductsStorage productStorage = new ProductsStorage();
    private List<Product> products = productStorage.productLoading(Config.address_product );

    public ProductController() throws IOException {
    }

    public void save() throws IOException {
        productStorage.saveProducts(products);

    }

    public List<Product> productLoading(String path) throws IOException {
        products.addAll(productStorage.productLoading(path));
        return this.products;
    }

    public int getProductsCount() {
        return this.products.size();
    }

    public Product getProduct(int index) {
        return products.get(index);
    }

    public Object[] getProducts() {
        Object[] result = new Object[products.size()];
        for (int i = 0; i < result.length; i++) {
            Product p = products.get(i);
            if (p.getQuantity() > 0) {
                result[i] = p.getArt() + "; " + p.getName() + ";  " + p.getColor() + ";  " + p.getPrice();
            }
        }
        return result;
    }

    public Product searchProduct(long art) {
        for(Product p : products) {
            if(p.getArt() == art) {
                return p;
            }
        }
        return null;
    }

    public void decreaseProducts(Product p, int Q) {
        int currentStockQuantity = p.getQuantity();
        if (currentStockQuantity - Q < 0) {
            throw new RuntimeException(
                    String.format(p.toString()));// обработка исключения (удаляем больше чем есть)
        }
        p.setQuantity(currentStockQuantity - Q);
    }

}
