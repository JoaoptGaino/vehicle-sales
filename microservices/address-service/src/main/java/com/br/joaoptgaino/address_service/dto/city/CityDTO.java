package com.br.joaoptgaino.address_service.dto.city;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityDTO {
    private UUID id;
    private String name;
    private String stateName;
    private String stateCountryName;
}
