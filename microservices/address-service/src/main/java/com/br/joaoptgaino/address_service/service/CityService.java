package com.br.joaoptgaino.address_service.service;

import com.br.joaoptgaino.address_service.dto.city.CityDTO;
import com.br.joaoptgaino.address_service.dto.city.CityParamsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CityService {
    Page<CityDTO> findAll(Pageable pageable, CityParamsDTO paramsDTO);

    CityDTO findOne(UUID id);
}
