package com.br.joaoptgaino.address_service.dto.state;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StateParamsDTO {
    private String name;
    private String iso2;
    private String countryCode;
}
