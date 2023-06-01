package com.br.joaoptgaino.catalogservice.fixtures.vehicle;

import com.br.joaoptgaino.catalogservice.constants.VehicleType;
import com.br.joaoptgaino.catalogservice.dto.vehicle.VehicleDTO;
import com.br.joaoptgaino.catalogservice.dto.vehicle.VehicleFormDTO;
import com.br.joaoptgaino.catalogservice.model.VehicleEntity;

import java.util.List;
import java.util.UUID;

import static com.br.joaoptgaino.catalogservice.fixtures.category.CategoryFixture.DEFAULT_CATEGORY_UUID;
import static com.br.joaoptgaino.catalogservice.fixtures.vehicleperk.VehiclePerkFixture.DEFAULT_VEHICLE_PERK_UUID;

public class VehicleFixture {
    public static UUID DEFAULT_ADDRESS_UUID = UUID.fromString("6ee664ef-fe6e-4426-8477-62a5d0df7da4");
    public static UUID DEFAULT_VEHICLE_UUID = UUID.fromString("5545ea90-7f07-45be-adab-731862e1ef91");

    public static VehicleEntity getVehicleEntity(String make, String model, String plate, VehicleType vehicleType, String color, Integer year) {

        return VehicleEntity.builder()
                .id(DEFAULT_VEHICLE_UUID)
                .make(make)
                .model(model)
                .vehicleType(vehicleType)
                .plate(plate)
                .color(color)
                .year(year)
                .sellerName("João Pedro")
                .addressId(DEFAULT_ADDRESS_UUID)
                .build();
    }

    public static VehicleDTO getVehicleDTO(String make, String model, String plate, VehicleType vehicleType, String color, Integer year) {
        return VehicleDTO.builder()
                .id(DEFAULT_VEHICLE_UUID)
                .make(make)
                .model(model)
                .vehicleType(vehicleType)
                .plate(plate)
                .color(color)
                .year(year)
                .sellerName("João Pedro")
                .addressId(DEFAULT_ADDRESS_UUID)
                .build();
    }

    public static VehicleFormDTO getVehicleFormDTO(String make, String model, String plate, VehicleType vehicleType, String color, Integer year) {
        return VehicleFormDTO.builder()
                .make(make)
                .model(model)
                .vehicleType(vehicleType)
                .plate(plate)
                .color(color)
                .year(year)
                .sellerName("João Pedro")
                .addressId(DEFAULT_ADDRESS_UUID)
                .categories(List.of(DEFAULT_CATEGORY_UUID))
                .perks(List.of(DEFAULT_VEHICLE_PERK_UUID))
                .build();
    }
}
