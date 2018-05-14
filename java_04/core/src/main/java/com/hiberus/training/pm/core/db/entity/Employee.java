package com.hiberus.training.pm.core.db.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue
    @Setter @Getter
    private Long id;

    @Column
    @Setter @Getter
    @NonNull
    private String firstName;

    @Column
    @Setter @Getter
    @NonNull
    private String lastName;

    @Column
    @Setter @Getter
    @NonNull
    private Float salary;

    @Column
    @Setter @Getter
    @NonNull
    private Date startDate;

    @Column
    @Setter @Getter
    private Date endDate;

    @OneToMany(mappedBy = "owner")
    @Setter @Getter
    private List<Phone> phoneList;
}
