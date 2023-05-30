package com.br.joaoptgaino.catalogservice.service.impl;

import com.br.joaoptgaino.catalogservice.dto.vehicle.VehicleDTO;
import com.br.joaoptgaino.catalogservice.dto.vehicle.VehicleFormDTO;
import com.br.joaoptgaino.catalogservice.dto.vehicle.VehicleParamsDTO;
import com.br.joaoptgaino.catalogservice.exceptions.BusinessException;
import com.br.joaoptgaino.catalogservice.model.CategoryEntity;
import com.br.joaoptgaino.catalogservice.model.VehicleEntity;
import com.br.joaoptgaino.catalogservice.model.VehiclePerkEntity;
import com.br.joaoptgaino.catalogservice.repository.CategoryRepository;
import com.br.joaoptgaino.catalogservice.repository.VehiclePerkRepository;
import com.br.joaoptgaino.catalogservice.repository.VehicleRepository;
import com.br.joaoptgaino.catalogservice.repository.specifications.VehicleSpecification;
import com.br.joaoptgaino.catalogservice.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final CategoryRepository categoryRepository;
    private final VehiclePerkRepository perkRepository;
    private final ModelMapper modelMapper;

    @Override
    public VehicleDTO create(VehicleFormDTO data) {

        List<CategoryEntity> categories = this.getCategories(data.getCategories());
        List<VehiclePerkEntity> perks = this.getPerks(data.getPerks());
        VehicleEntity vehicle = buildVehicleEntity(data, categories, perks);
        VehicleEntity createdVehicle = vehicleRepository.save(vehicle);
        log.info("Vehicle with plate {} created", data.getPlate());
        return modelMapper.map(createdVehicle, VehicleDTO.class);
    }

    @Override
    public Page<VehicleDTO> findAll(Pageable pageable, VehicleParamsDTO paramsDTO) {
        Specification<VehicleEntity> specification = VehicleSpecification.create(paramsDTO);
        List<VehicleDTO> vehicles = vehicleRepository.findAll(specification, pageable)
                .getContent()
                .stream()
                .map(content -> modelMapper.map(content, VehicleDTO.class))
                .toList();
        log.info("Returned {} vehicles", vehicles.size());
        return new PageImpl<>(vehicles);
    }

    @Override
    public VehicleDTO findOne(String plate) {
        VehicleEntity vehicle = this.getVehicleOrElseThrow(plate);
        log.info("Vehicle with plate {} found", plate);
        return modelMapper.map(vehicle, VehicleDTO.class);
    }


    @Override
    public VehicleDTO update(String plate, VehicleFormDTO data) {
        VehicleEntity vehicle = getVehicleOrElseThrow(plate);
        modelMapper.map(data, vehicle);
        VehicleEntity updatedVehicle = vehicleRepository.save(vehicle);
        log.info("Vehicle with plate {} updated", plate);
        return modelMapper.map(updatedVehicle, VehicleDTO.class);
    }

    @Override
    public void delete(String plate) {
        VehicleEntity vehicle = this.getVehicleOrElseThrow(plate);
        vehicleRepository.delete(vehicle);
        log.info("Vehicle with plate {} deleted", plate);
    }

    private List<VehiclePerkEntity> getPerks(List<UUID> perks) {
        List<VehiclePerkEntity> perkEntities = new ArrayList<>();
        for (UUID perkId : perks) {
            Optional<VehiclePerkEntity> perk = perkRepository.findById(perkId);
            perk.ifPresent(perkEntities::add);
        }
        return perkEntities;
    }

    private List<CategoryEntity> getCategories(List<UUID> categories) {
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        for (UUID categoryId : categories) {
            Optional<CategoryEntity> category = categoryRepository.findById(categoryId);
            category.ifPresent(categoryEntities::add);
        }
        return categoryEntities;
    }

    private static VehicleEntity buildVehicleEntity(VehicleFormDTO data, List<CategoryEntity> categories, List<VehiclePerkEntity> perks) {
        return VehicleEntity.builder()
                .make(data.getMake())
                .model(data.getModel())
                .vehicleType(data.getVehicleType())
                .plate(data.getPlate())
                .color(data.getColor())
                .year(data.getYear())
                .addressId(data.getAddressId())
                .sellerName(data.getSellerName())
                .categories(categories)
                .perks(perks)
                .build();
    }

    private VehicleEntity getVehicleOrElseThrow(String plate) {
        return vehicleRepository.findByPlate(plate).orElseThrow(() -> BusinessException.builder()
                .httpStatus(HttpStatus.NOT_FOUND.value())
                .message(HttpStatus.NOT_FOUND.getReasonPhrase())
                .errors(Collections.singletonList(String.format("Vehicle with plate %s not found", plate)))
                .build());
    }
}
