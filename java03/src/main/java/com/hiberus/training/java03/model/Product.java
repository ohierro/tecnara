package com.hiberus.training.java03.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
    @Setter @Getter
    @Column
    @Id
    private Long id;

    @Setter @Getter
    @Column(name = "my_name")
    private String name;

    @Setter @Getter
    @Column
    private String description;
}
