package com.hiberus.training.java03.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ProductOrder")
public class Order {

    @Column
    @Setter @Getter
    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Setter @Getter
    private Integer quantity;

    @ManyToOne
    @Setter @Getter
    private Product product;
}
