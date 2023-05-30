package com.br.joaoptgaino.address_service.dto.country;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryParamsDTO {
    private String name;
    private String iso2;
    private String iso3;
    private String capital;
    private String phoneCode;
    private String currency;
}
