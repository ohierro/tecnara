package com.hiberus.training.model;


import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MemoryStoreTest {

    @Test
    public void testGetProductList() {
        List<Product> products = new ArrayList();

        // creamos el producto
        Product product = new Product();
        product.setId(1);
        product.setName("Test");

        // add to list
        products.add(product);

        MemoryStore store = new MemoryStore(products);

        List<Product> result = store.getProductList();

        assertEquals(1, result.size());
        assertEquals("Test", result.get(0).getName());
    }

    @Test
    public void testGetProduct() throws NoSuchProductException {
        List<Product> products = new ArrayList();

        // creamos el producto
        Product product = new Product();
        product.setId(1);
        product.setName("Test");

        // add to list
        products.add(product);

        MemoryStore store = new MemoryStore(products);

        Product result = store.getProduct(1);

        assertEquals("Test", result.getName());
    }

    @Test
    public void testGetInvalidProduct() {
        List<Product> products = new ArrayList();

        // creamos el producto
        Product product = new Product();
        product.setId(1);
        product.setName("Test");

        // add to list
        products.add(product);

        MemoryStore store = new MemoryStore(products);

        try {
            Product result = store.getProduct(2);
            fail("Get product should thrown an exception");
        } catch (NoSuchProductException e) {
            System.out.println("error");
        }
    }

    @Test
    public void testGetQuantities() {
        Map<Product, Integer> products = new HashMap<Product, Integer>();
        Product product1 = new Product(1,"Pasta de dientes","test");
        products.put(product1, 5);
        Product product2 = new Product(2, "Cepillo", "test cepillo");
        products.put(product2, 8);


        MemoryStore store = new MemoryStore(products);

        int value = store.getProductQuantity(product2);
        assertEquals(8,value);
    }

    @Test
    public void testAddProductsUnits() throws IOException {
        Map<Product, Integer> products = new HashMap<Product, Integer>();
        Product product1 = new Product(1,"Pasta de dientes","test");
        products.put(product1, 5);
        Product product2 = new Product(2, "Cepillo", "test cepillo");
        products.put(product2, 8);

        MemoryStore store = new MemoryStore(products);

        store.addProductUnits(product1,10);

        int value = store.getProductQuantity(product1);
        assertEquals(15,value);
    }
}
