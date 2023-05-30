package com.br.joaoptgaino.address_service.repository.specification;

import com.br.joaoptgaino.address_service.dto.country.CountryParamsDTO;
import com.br.joaoptgaino.address_service.model.CountryEntity;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import static io.micrometer.common.util.StringUtils.isNotBlank;

@UtilityClass
public class CountrySpecification {
    public static Specification<CountryEntity> create(CountryParamsDTO countryParamsDTO) {
        return hasName(countryParamsDTO.getName())
                .and(hasIso2(countryParamsDTO.getIso2()))
                .and(hasIso3(countryParamsDTO.getIso3()))
                .and(hasCapital(countryParamsDTO.getCapital()))
                .and(hasPhoneCode(countryParamsDTO.getPhoneCode()))
                .and(hasCurrency(countryParamsDTO.getCurrency()));
    }

    public static Specification<CountryEntity> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (isNotBlank(name)) {
                return criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + name.toUpperCase() + "%");
            }
            return null;
        };
    }

    public static Specification<CountryEntity> hasIso2(String iso2) {
        return (root, query, criteriaBuilder) -> {
            if (isNotBlank(iso2)) {
                return criteriaBuilder.like(criteriaBuilder.upper(root.get("iso2")), "%" + iso2.toUpperCase() + "%");
            }
            return null;
        };
    }

    public static Specification<CountryEntity> hasIso3(String iso3) {
        return (root, query, criteriaBuilder) -> {
            if (isNotBlank(iso3)) {
                return criteriaBuilder.like(criteriaBuilder.upper(root.get("iso3")), "%" + iso3.toUpperCase() + "%");
            }
            return null;
        };
    }

    public static Specification<CountryEntity> hasCapital(String capital) {
        return (root, query, criteriaBuilder) -> {
            if (isNotBlank(capital)) {
                return criteriaBuilder.like(criteriaBuilder.upper(root.get("capital")), "%" + capital.toUpperCase() + "%");
            }
            return null;
        };
    }

    public static Specification<CountryEntity> hasPhoneCode(String phoneCode) {
        return (root, query, criteriaBuilder) -> {
            if (isNotBlank(phoneCode)) {
                return criteriaBuilder.like(criteriaBuilder.upper(root.get("phoneCode")), "%" + phoneCode.toUpperCase() + "%");
            }
            return null;
        };
    }

    public static Specification<CountryEntity> hasCurrency(String currency) {
        return (root, query, criteriaBuilder) -> {
            if (isNotBlank(currency)) {
                return criteriaBuilder.like(criteriaBuilder.upper(root.get("currency")), "%" + currency.toUpperCase() + "%");
            }
            return null;
        };
    }
}
