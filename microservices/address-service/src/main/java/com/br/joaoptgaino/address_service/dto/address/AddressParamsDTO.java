package com.br.joaoptgaino.address_service.dto.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressParamsDTO {
    public String street;
    public String number;
    public String neighbourhood;
    public String postalCode;
    public String city;
}
