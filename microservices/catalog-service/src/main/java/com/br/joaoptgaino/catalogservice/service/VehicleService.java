package com.br.joaoptgaino.catalogservice.service;

import com.br.joaoptgaino.catalogservice.dto.vehicle.VehicleDTO;
import com.br.joaoptgaino.catalogservice.dto.vehicle.VehicleFormDTO;
import com.br.joaoptgaino.catalogservice.dto.vehicle.VehicleParamsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VehicleService {

    VehicleDTO create(VehicleFormDTO data);

    Page<VehicleDTO> findAll(Pageable pageable, VehicleParamsDTO paramsDTO);

    VehicleDTO findOne(String plate);

    VehicleDTO update(String plate, VehicleFormDTO data);

    void delete(String plate);
}
