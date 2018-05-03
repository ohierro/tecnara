package com.hiberus.training.java03.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
    Product findByNameOrDescription(String name, String description);
}
