package com.br.joaoptgaino.catalogservice.service.impl;

import com.br.joaoptgaino.catalogservice.dto.vehicleperks.VehiclePerkDTO;
import com.br.joaoptgaino.catalogservice.dto.vehicleperks.VehiclePerkFormDTO;
import com.br.joaoptgaino.catalogservice.exceptions.BusinessException;
import com.br.joaoptgaino.catalogservice.model.VehiclePerkEntity;
import com.br.joaoptgaino.catalogservice.repository.VehiclePerkRepository;
import com.br.joaoptgaino.catalogservice.service.VehiclePerkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class VehiclePerkServiceImpl implements VehiclePerkService {
    private final VehiclePerkRepository vehiclePerkRepository;
    private final ModelMapper modelMapper;

    @Override
    public VehiclePerkDTO create(VehiclePerkFormDTO data) {
        VehiclePerkEntity vehiclePerkEntity = new VehiclePerkEntity();
        modelMapper.map(data, vehiclePerkEntity);
        VehiclePerkEntity createdPerk = vehiclePerkRepository.save(vehiclePerkEntity);
        log.info("Perk with name {} created", data.getName());
        return modelMapper.map(createdPerk, VehiclePerkDTO.class);
    }

    @Override
    public Page<VehiclePerkDTO> findAll(Pageable pageable) {
        List<VehiclePerkDTO> perks = vehiclePerkRepository.findAll(pageable)
                .getContent()
                .stream().map(content -> modelMapper.map(content, VehiclePerkDTO.class))
                .toList();
        log.info("Returned {} perks", perks.size());
        return new PageImpl<>(perks);
    }

    @Override
    public VehiclePerkDTO update(UUID id, VehiclePerkFormDTO data) {
        VehiclePerkEntity perk = getPerkOrElseThrow(id);
        modelMapper.map(data, perk);
        VehiclePerkEntity updatedPerk = vehiclePerkRepository.save(perk);
        log.info("Perk with id {} updated", id);
        return modelMapper.map(updatedPerk, VehiclePerkDTO.class);
    }

    @Override
    public void delete(UUID id) {
        VehiclePerkEntity perk = this.getPerkOrElseThrow(id);
        vehiclePerkRepository.delete(perk);
        log.info("Perk with id {} deleted", id);
    }

    private VehiclePerkEntity getPerkOrElseThrow(UUID id) {
        return vehiclePerkRepository.findById(id)
                .orElseThrow(() -> BusinessException.builder()
                        .httpStatus(HttpStatus.NOT_FOUND.value())
                        .message(HttpStatus.NOT_FOUND.getReasonPhrase())
                        .errors(Collections.singletonList(String.format("Perk with id %s not found", id)))
                        .build());
    }

}
