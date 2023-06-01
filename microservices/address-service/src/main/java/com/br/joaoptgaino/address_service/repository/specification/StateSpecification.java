package com.br.joaoptgaino.address_service.repository.specification;

import com.br.joaoptgaino.address_service.dto.state.StateParamsDTO;
import com.br.joaoptgaino.address_service.model.StateEntity;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.util.BitSet;

import static io.micrometer.common.util.StringUtils.isNotBlank;

@UtilityClass
public class StateSpecification {
    public static Specification<StateEntity> create(StateParamsDTO paramsDTO) {
        return hasName(paramsDTO.getName())
                .and(hasCountryCode(paramsDTO.getCountryCode()))
                .and(hasIso2(paramsDTO.getIso2()));
    }

    private static Specification<StateEntity> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (isNotBlank(name)) {
                return criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + name.toUpperCase() + "%");
            }
            return null;
        };
    }

    private static Specification<StateEntity> hasCountryCode(String countryCode) {
        return (root, query, criteriaBuilder) -> {
            if (isNotBlank(countryCode)) {
                return criteriaBuilder.like(criteriaBuilder.upper(root.get("countryCode")), "%" + countryCode.toUpperCase() + "%");
            }
            return null;
        };
    }

    private static Specification<StateEntity> hasIso2(String iso2) {
        return (root, query, criteriaBuilder) -> {
            if (isNotBlank(iso2)) {
                return criteriaBuilder.like(criteriaBuilder.upper(root.get("iso2")), "%" + iso2.toUpperCase() + "%");
            }
            return null;
        };
    }
}
