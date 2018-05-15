package com.hiberus.training.pm.core.provider;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Long id;
    private String street;
    private String city;
    private String province;
    private String county;
    private String postcode;
}
