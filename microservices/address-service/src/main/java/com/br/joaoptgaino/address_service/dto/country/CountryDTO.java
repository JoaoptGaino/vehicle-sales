package com.br.joaoptgaino.address_service.dto.country;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryDTO {
    private UUID id;
    private String name;
    private String iso2;
    private String iso3;
    private String capital;
    private String phoneCode;
    private String currency;
}
