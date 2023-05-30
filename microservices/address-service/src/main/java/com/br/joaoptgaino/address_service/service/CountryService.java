package com.br.joaoptgaino.address_service.service;

import com.br.joaoptgaino.address_service.dto.country.CountryDTO;
import com.br.joaoptgaino.address_service.dto.country.CountryParamsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CountryService {
    Page<CountryDTO> findAll(Pageable pageable, CountryParamsDTO paramsDTO);

    CountryDTO findOne(UUID id);
}
