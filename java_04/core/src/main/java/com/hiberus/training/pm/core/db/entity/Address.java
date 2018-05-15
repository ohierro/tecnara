package com.hiberus.training.pm.core.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue
    @Getter @Setter
    private Long id;

    @Column
    @Getter @Setter
    private String street;

    @Column(length = 100)
    @Getter @Setter
    private String city;

    @Column(length = 100)
    @Getter @Setter
    private String province;

    @Column(length = 100)
    @Getter @Setter
    private String county;

    @Column
    @Getter @Setter
    private String postcode;
}
