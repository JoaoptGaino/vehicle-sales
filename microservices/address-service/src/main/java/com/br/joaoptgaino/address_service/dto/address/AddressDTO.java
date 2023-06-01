package com.br.joaoptgaino.address_service.dto.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {
    private UUID id;
    private String street;
    private String number;
    private String neighbourhood;
    private String postalCode;
    private String city;
    private String state;
    private String country;
}
