package com.hiberus.training.java03.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ProductOrder")
public class Order {

    @Column
    @Setter @Getter
    @Id
    private Long id;

    @Column
    @Setter @Getter
    private Integer quantity;
}
