package com.br.joaoptgaino.address_service.dto.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressFormDTO {
    @NotNull(message = "street cannot be null")
    @NotBlank(message = "street cannot be blank")
    private String street;

    @NotNull(message = "number cannot be null")
    @NotBlank(message = "number cannot be blank")
    private String number;

    @NotNull(message = "neighbourhood cannot be null")
    @NotBlank(message = "neighbourhood cannot be blank")
    private String neighbourhood;

    @NotNull(message = "postalCode cannot be null")
    @NotBlank(message = "postalCode cannot be blank")
    private String postalCode;

    @NotNull(message = "cityId cannot be null")
    @NotBlank(message = "cityId cannot be blank")
    private UUID cityId;
}
