package com.br.joaoptgaino.address_service.repository.specification;

import com.br.joaoptgaino.address_service.dto.city.CityParamsDTO;
import com.br.joaoptgaino.address_service.model.CityEntity;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import static io.micrometer.common.util.StringUtils.isNotBlank;

@UtilityClass
public class CitySpecification {
    public static Specification<CityEntity> create(CityParamsDTO paramsDTO) {
        return hasName(paramsDTO.getName())
                .and(hasStateId(paramsDTO.getState()));
    }

    private static Specification<CityEntity> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (isNotBlank(name)) {
                return criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + name.toUpperCase() + "%");
            }
            return null;
        };
    }

    private static Specification<CityEntity> hasStateId(String stateId) {
        return (root, query, criteriaBuilder) -> {
            if (isNotBlank(stateId)) {
                return criteriaBuilder.equal(root.get("stateId"), stateId);
            }
            return null;
        };
    }
}
