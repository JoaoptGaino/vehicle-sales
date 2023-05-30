package com.br.joaoptgaino.catalogservice.repository.specifications;

import com.br.joaoptgaino.catalogservice.dto.vehicle.VehicleParamsDTO;
import com.br.joaoptgaino.catalogservice.model.VehicleEntity;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import static io.micrometer.common.util.StringUtils.isNotBlank;

@UtilityClass
public class VehicleSpecification {
    public static Specification<VehicleEntity> create(VehicleParamsDTO paramsDTO) {
        return hasMake(paramsDTO.getMake())
                .and(hasModel(paramsDTO.getModel()))
                .and(hasVehicleType(paramsDTO.getVehicleType().toString()))
                .and(hasPlate(paramsDTO.getPlate()))
                .and(hasColor(paramsDTO.getColor()))
                .and(hasYear(paramsDTO.getYear()));
    }

    public static Specification<VehicleEntity> hasMake(String make) {
        return (root, query, criteriaBuilder) -> {
            if (isNotBlank(make)) {
                return criteriaBuilder.like(criteriaBuilder.upper(root.get("make")), "%" + make.toUpperCase() + "%");
            }
            return null;
        };
    }

    public static Specification<VehicleEntity> hasModel(String model) {
        return (root, query, criteriaBuilder) -> {
            if (isNotBlank(model)) {
                return criteriaBuilder.like(criteriaBuilder.upper(root.get("model")), "%" + model.toUpperCase() + "%");
            }
            return null;
        };
    }

    public static Specification<VehicleEntity> hasVehicleType(String vehicleType) {
        return (root, query, criteriaBuilder) -> {
            if (isNotBlank(vehicleType)) {
                return criteriaBuilder.like(criteriaBuilder.upper(root.get("vehicleType")), "%" + vehicleType.toUpperCase() + "%");
            }
            return null;
        };
    }

    public static Specification<VehicleEntity> hasPlate(String plate) {
        return (root, query, criteriaBuilder) -> {
            if (isNotBlank(plate)) {
                return criteriaBuilder.like(criteriaBuilder.upper(root.get("plate")), "%" + plate.toUpperCase() + "%");
            }
            return null;
        };
    }

    public static Specification<VehicleEntity> hasColor(String color) {
        return (root, query, criteriaBuilder) -> {
            if (isNotBlank(color)) {
                return criteriaBuilder.like(criteriaBuilder.upper(root.get("color")), "%" + color.toUpperCase() + "%");
            }
            return null;
        };
    }

    public static Specification<VehicleEntity> hasYear(Integer year) {
        return (root, query, criteriaBuilder) -> {
            if (year != null) {
                return criteriaBuilder.equal(root.get("year"), year);
            }
            return null;
        };
    }
}
