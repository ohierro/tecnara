package com.hiberus.training.java03.controller;

import com.hiberus.training.java03.model.Product;
import com.hiberus.training.java03.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public List<Product> listProducts(@RequestParam(required = false) String limit) {
        System.out.println("list products");

        List<Product> products = productRepository.findAll();
        for(Product p : products) {
            System.out.println("Product: " + p.getName());
        }

        return productRepository.findAll();
    }

    @RequestMapping(path = "/products", method = RequestMethod.POST)
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @RequestMapping(path = "/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);

        if (!product.isPresent()) {
            return new ResponseEntity<>("El producto solicitado no existe", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }
}
