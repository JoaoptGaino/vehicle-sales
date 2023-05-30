package com.br.joaoptgaino.catalogservice.service;

import com.br.joaoptgaino.catalogservice.dto.vehicleperks.VehiclePerkDTO;
import com.br.joaoptgaino.catalogservice.dto.vehicleperks.VehiclePerkFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface VehiclePerkService {
    VehiclePerkDTO create(VehiclePerkFormDTO data);

    Page<VehiclePerkDTO> findAll(Pageable pageable);

    VehiclePerkDTO update(UUID id, VehiclePerkFormDTO data);

    void delete(UUID id);
}
