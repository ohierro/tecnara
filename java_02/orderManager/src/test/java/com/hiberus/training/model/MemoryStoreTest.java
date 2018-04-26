package com.hiberus.training.model;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
}
