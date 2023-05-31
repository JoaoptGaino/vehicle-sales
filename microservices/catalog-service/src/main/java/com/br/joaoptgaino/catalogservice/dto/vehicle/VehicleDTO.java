package com.br.joaoptgaino.catalogservice.dto.vehicle;

import com.br.joaoptgaino.catalogservice.constants.VehicleType;
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
public class VehicleDTO {
    private UUID id;
    private String make;
    private String model;
    private VehicleType vehicleType;
    private String plate;
    private String color;
    private Integer year;
    private UUID addressId;
    private String sellerName;
    private List<VehicleCategoryDTO> categories;
    private List<VehiclePerkDTO> perks;
}
