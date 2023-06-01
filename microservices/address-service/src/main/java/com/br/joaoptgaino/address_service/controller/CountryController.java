package com.br.joaoptgaino.address_service.controller;

import com.br.joaoptgaino.address_service.dto.country.CountryDTO;
import com.br.joaoptgaino.address_service.dto.country.CountryParamsDTO;
import com.br.joaoptgaino.address_service.service.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("countries")
@Slf4j
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<Page<CountryDTO>> findAll(@PageableDefault Pageable pageable, @RequestParam(required = false) CountryParamsDTO paramsDTO) {
        Page<CountryDTO> countries = countryService.findAll(pageable, paramsDTO);
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDTO> findOne(@PathVariable UUID id) {
        CountryDTO country = countryService.findOne(id);
        return ResponseEntity.ok(country);
    }
}
