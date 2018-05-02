package com.hiberus.training.model;

import com.hiberus.training.model.Product;

import java.io.IOException;
import java.util.List;

public interface Store {
    List<Product> getProductList();

    Product getProduct(int productId) throws NoSuchProductException;

    int getProductQuantity(Product product2);

    void addProductUnits(Product product, int quantity) throws IOException;
}
