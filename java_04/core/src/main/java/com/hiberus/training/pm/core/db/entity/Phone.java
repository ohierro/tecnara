package com.hiberus.training.pm.core.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Phone {
    @Id
    @Setter @Getter
    @GeneratedValue
    private Long id;

    @Column
    @Setter @Getter
    private String type;

    @Column
    @Setter @Getter
    private String phoneNumber;

    @Column
    @Setter @Getter
    private String areaCode;

    @Setter @Getter
    @ManyToOne
    private Employee owner;
}
