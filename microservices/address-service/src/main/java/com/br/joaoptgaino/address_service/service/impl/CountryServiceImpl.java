package com.br.joaoptgaino.address_service.service.impl;


import com.br.joaoptgaino.address_service.dto.country.CountryDTO;
import com.br.joaoptgaino.address_service.dto.country.CountryParamsDTO;
import com.br.joaoptgaino.address_service.exceptions.BusinessException;
import com.br.joaoptgaino.address_service.model.CountryEntity;
import com.br.joaoptgaino.address_service.repository.CountryRepository;
import com.br.joaoptgaino.address_service.repository.specification.CountrySpecification;
import com.br.joaoptgaino.address_service.service.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<CountryDTO> findAll(Pageable pageable, CountryParamsDTO paramsDTO) {
        Specification<CountryEntity> specification = CountrySpecification.create(paramsDTO);
        List<CountryDTO> countries = countryRepository.findAll(specification, pageable)
                .getContent()
                .stream()
                .map(content -> modelMapper.map(content, CountryDTO.class))
                .toList();
        return new PageImpl<>(countries);
    }

    @Override
    public CountryDTO findOne(UUID id) {
        CountryEntity country = countryRepository.findById(id).orElseThrow(() -> BusinessException.builder()
                .httpStatus(HttpStatus.NOT_FOUND.value())
                .message(HttpStatus.NOT_FOUND.getReasonPhrase())
                .errors(Collections.singletonList(String.format("Country with id %s not found", id)))
                .build());
        return modelMapper.map(country, CountryDTO.class);
    }
}
