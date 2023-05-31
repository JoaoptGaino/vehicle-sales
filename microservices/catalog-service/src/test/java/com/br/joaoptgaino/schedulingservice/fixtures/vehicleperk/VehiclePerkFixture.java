package com.br.joaoptgaino.schedulingservice.fixtures.vehicleperk;

import com.br.joaoptgaino.catalogservice.dto.vehicleperks.VehiclePerkDTO;
import com.br.joaoptgaino.catalogservice.dto.vehicleperks.VehiclePerkFormDTO;
import com.br.joaoptgaino.catalogservice.model.VehiclePerkEntity;

import java.util.UUID;

public class VehiclePerkFixture {
    public static UUID DEFAULT_VEHICLE_PERK_UUID = UUID.fromString("09f3fefc-8b26-4f62-b101-f82794a4c371");
    public static VehiclePerkEntity getVehiclePerkEntity(String name) {
        return VehiclePerkEntity.builder()
                .id(DEFAULT_VEHICLE_PERK_UUID)
                .name(name)
                .build();
    }

    public static VehiclePerkDTO getVehiclePerkDTO(String name) {
        return VehiclePerkDTO.builder()
                .id(DEFAULT_VEHICLE_PERK_UUID)
                .name(name)
                .build();
    }

    public static VehiclePerkFormDTO getVehiclePerkFormDTO(String name) {
        return VehiclePerkFormDTO.builder()
                .name(name)
                .build();
    }
}
