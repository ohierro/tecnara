package com.hiberus.training.pm.core.provider;


import com.hiberus.training.pm.core.provider.AddressDto;
import lombok.Getter;
import lombok.Setter;

public class EmployeeDto {
    @Setter
    @Getter
    private Long id;

    @Setter @Getter
    private String firstName;

    @Setter @Getter
    private String lastName;

    @Setter @Getter
    private Float salary;

    @Setter @Getter
    private AddressDto address;
}
