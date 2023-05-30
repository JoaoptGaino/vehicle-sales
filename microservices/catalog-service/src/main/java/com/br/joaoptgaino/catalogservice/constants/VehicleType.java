package com.br.joaoptgaino.catalogservice.constants;

public enum VehicleType {
    MOTORCYCLE("MOTORCYCLE"),
    CAR("CAR"),
    TRICYCLE("TRICYCLE");
    private final String type;

    VehicleType(String type) {
        this.type = type;
    }

    public String getName() {
        return type;
    }

    public static VehicleType fromString(String value) {
        if (value != null) {
            for (VehicleType vehicleType : VehicleType.values()) {
                if (value.equalsIgnoreCase(vehicleType.type)) {
                    return vehicleType;
                }
            }
        }
        throw new IllegalArgumentException("Invalid VehicleType value: " + value);
    }
}
