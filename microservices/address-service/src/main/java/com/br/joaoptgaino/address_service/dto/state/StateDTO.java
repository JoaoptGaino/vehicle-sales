package com.br.joaoptgaino.address_service.dto.state;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StateDTO {
    private UUID id;
    private String name;
    private String iso2;
    private String countryCode;
    private List<StateCity> cities;
    private String countryName;
}
