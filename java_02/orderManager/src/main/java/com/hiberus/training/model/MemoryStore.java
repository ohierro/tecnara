package com.hiberus.training.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryStore implements Store {

    Map<Product,Integer> products;

    public MemoryStore(List<Product> products) {
        this.products = new HashMap();

        for (Product product : products) {
            this.products.put(product,0);
        }
    }

    public MemoryStore(Map<Product,Integer> products) {
        this.products = products;
    }

    public List<Product> getProductList() {
        List<Product> returnedProducts = new ArrayList<Product>();

        for (Product product : this.products.keySet()) {
            returnedProducts.add(product);
        }

        return returnedProducts;
//        return (List<Product>) products.keySet();
    }

    public Product getProduct(int productId) throws NoSuchProductException {
        for (Product p : products.keySet()) {
            if (p.getId() == productId) return p;
        }

        throw new NoSuchProductException();
    }

    public int getProductQuantity(Product product) {
        return products.get(product);
    }

    public void addProductUnits(Product product, int quantity) throws IOException {

        if (!products.containsKey(product)) throw new IOException("El producto no existe");

        int value = products.get(product);

        value += quantity;

        products.put(product, value);
    }
}
