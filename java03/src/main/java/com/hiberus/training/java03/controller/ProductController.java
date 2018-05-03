package com.hiberus.training.java03.controller;

import com.hiberus.training.java03.model.Product;
import com.hiberus.training.java03.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(path = "/list")
    public List<Product> listProducts() {
        System.out.println("list products");

        List<Product> products = productRepository.findAll();
        for(Product p : products) {
            System.out.println("Product: " + p.getName());
        }

        return productRepository.findAll();
    }
}
