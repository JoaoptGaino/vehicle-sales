package com.br.joaoptgaino.catalogservice.controller;

import com.br.joaoptgaino.catalogservice.dto.vehicleperks.VehiclePerkDTO;
import com.br.joaoptgaino.catalogservice.dto.vehicleperks.VehiclePerkFormDTO;
import com.br.joaoptgaino.catalogservice.service.VehiclePerkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("perks")
@Slf4j
@RequiredArgsConstructor
public class VehiclePerkController {
    private final VehiclePerkService vehiclePerkService;

    @PostMapping
    public ResponseEntity<VehiclePerkDTO> create(@RequestBody @Valid VehiclePerkFormDTO data) {
        VehiclePerkDTO vehiclePerkDTO = vehiclePerkService.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiclePerkDTO);
    }

    @GetMapping
    public ResponseEntity<Page<VehiclePerkDTO>> findAll(@PageableDefault Pageable pageable) {
        Page<VehiclePerkDTO> perks = vehiclePerkService.findAll(pageable);
        return ResponseEntity.ok(perks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiclePerkDTO> findOne(@PathVariable UUID id, @RequestBody @Valid VehiclePerkFormDTO data) {
        VehiclePerkDTO perk = vehiclePerkService.update(id, data);
        return ResponseEntity.ok(perk);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        vehiclePerkService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
