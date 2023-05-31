package com.br.joaoptgaino.address_service.controller;

import com.br.joaoptgaino.address_service.dto.city.CityDTO;
import com.br.joaoptgaino.address_service.dto.city.CityParamsDTO;
import com.br.joaoptgaino.address_service.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("cities")
@RequiredArgsConstructor
@Slf4j
public class CityController {
    private final CityService cityService;

    @GetMapping
    public ResponseEntity<Page<CityDTO>> findAll(@PageableDefault Pageable pageable, @RequestParam(required = false) CityParamsDTO paramsDTO) {
        Page<CityDTO> cities = cityService.findAll(pageable, paramsDTO);
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> findOne(@PathVariable UUID id) {
        CityDTO city = cityService.findOne(id);
        return ResponseEntity.ok(city);
    }
}
