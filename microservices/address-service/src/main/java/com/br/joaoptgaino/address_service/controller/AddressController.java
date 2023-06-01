package com.br.joaoptgaino.address_service.controller;


import com.br.joaoptgaino.address_service.dto.address.AddressDTO;
import com.br.joaoptgaino.address_service.dto.address.AddressFormDTO;
import com.br.joaoptgaino.address_service.dto.address.AddressParamsDTO;
import com.br.joaoptgaino.address_service.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("addresses")
@RequiredArgsConstructor
@Slf4j
public class AddressController {
    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressDTO> create(AddressFormDTO addressFormDTO) {
        AddressDTO address = addressService.create(addressFormDTO);
        return ResponseEntity.ok(address);
    }

    @GetMapping
    public ResponseEntity<Page<AddressDTO>> findAll(@PageableDefault Pageable pageable, @RequestParam(required = false) AddressParamsDTO paramsDTO) {
        Page<AddressDTO> address = addressService.findAll(pageable, paramsDTO);
        return ResponseEntity.ok(address);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> findOne(@PathVariable UUID id) {
        AddressDTO address = addressService.findOne(id);
        return ResponseEntity.ok(address);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> update(@PathVariable UUID id, AddressFormDTO addressFormDTO) {
        AddressDTO address = addressService.update(id, addressFormDTO);
        return ResponseEntity.ok(address);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
