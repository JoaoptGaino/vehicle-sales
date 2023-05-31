package com.br.joaoptgaino.schedulingservice.fixtures.vehicle;

import com.br.joaoptgaino.catalogservice.constants.VehicleType;
import com.br.joaoptgaino.catalogservice.dto.vehicle.VehicleDTO;
import com.br.joaoptgaino.catalogservice.dto.vehicle.VehicleFormDTO;
import com.br.joaoptgaino.catalogservice.model.VehicleEntity;

import java.util.UUID;

public class VehicleFixture {
    public static UUID DEFAULT_ADDRESS_UUID = UUID.fromString("6ee664ef-fe6e-4426-8477-62a5d0df7da4");
    public static UUID DEFAULT_VEHICLE_UUID = UUID.fromString("5545ea90-7f07-45be-adab-731862e1ef91");

    public static VehicleEntity getVehicleEntity(String plate) {

        return VehicleEntity.builder()
                .id(DEFAULT_VEHICLE_UUID)
                .make("Chevrolet")
                .model("Omega")
                .vehicleType(VehicleType.CAR)
                .plate(plate)
                .color("Black")
                .year(1998)
                .sellerName("João Pedro")
                .addressId(DEFAULT_ADDRESS_UUID)
                .build();
    }

    public static VehicleDTO getVehicleDTO(String plate) {
        return VehicleDTO.builder()
                .id(DEFAULT_VEHICLE_UUID)
                .make("Chevrolet")
                .model("Omega")
                .vehicleType(VehicleType.CAR)
                .plate(plate)
                .color("Black")
                .year(1998)
                .sellerName("João Pedro")
                .addressId(DEFAULT_ADDRESS_UUID)
                .build();
    }

    public static VehicleFormDTO getVehicleFormDTO(String plate) {
        return VehicleFormDTO.builder()
                .make("Chevrolet")
                .model("Omega")
                .vehicleType(VehicleType.CAR)
                .plate(plate)
                .color("Black")
                .year(1998)
                .sellerName("João Pedro")
                .addressId(DEFAULT_ADDRESS_UUID)
                .build();
    }
}
