package com.br.joaoptgaino.address_service.service.impl;

import com.br.joaoptgaino.address_service.dto.city.CityDTO;
import com.br.joaoptgaino.address_service.dto.city.CityParamsDTO;
import com.br.joaoptgaino.address_service.exceptions.BusinessException;
import com.br.joaoptgaino.address_service.model.CityEntity;
import com.br.joaoptgaino.address_service.repository.CityRepository;
import com.br.joaoptgaino.address_service.repository.specification.CitySpecification;
import com.br.joaoptgaino.address_service.service.CityService;
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
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<CityDTO> findAll(Pageable pageable, CityParamsDTO paramsDTO) {
        Specification<CityEntity> specification = CitySpecification.create(paramsDTO);
        List<CityDTO> cities = cityRepository.findAll(specification, pageable)
                .stream()
                .map(content -> modelMapper.map(content, CityDTO.class))
                .toList();
        return new PageImpl<>(cities);
    }

    @Override
    public CityDTO findOne(UUID id) {
        CityEntity city = cityRepository.findById(id).orElseThrow(() -> BusinessException.builder()
                .httpStatus(HttpStatus.NOT_FOUND.value())
                .message(HttpStatus.NOT_FOUND.getReasonPhrase())
                .errors(Collections.singletonList(String.format("City with id %s not found", id)))
                .build());
        return modelMapper.map(city, CityDTO.class);
    }
}
