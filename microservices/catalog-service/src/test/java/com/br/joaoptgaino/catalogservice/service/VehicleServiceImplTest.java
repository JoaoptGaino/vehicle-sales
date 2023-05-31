package com.br.joaoptgaino.catalogservice.service;

import com.br.joaoptgaino.catalogservice.constants.VehicleType;
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
import com.br.joaoptgaino.catalogservice.service.impl.VehicleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.br.joaoptgaino.catalogservice.fixtures.category.CategoryFixture.DEFAULT_CATEGORY_UUID;
import static com.br.joaoptgaino.catalogservice.fixtures.category.CategoryFixture.getCategoryEntity;
import static com.br.joaoptgaino.catalogservice.fixtures.vehicle.VehicleFixture.getVehicleEntity;
import static com.br.joaoptgaino.catalogservice.fixtures.vehicle.VehicleFixture.getVehicleFormDTO;
import static com.br.joaoptgaino.catalogservice.fixtures.vehicleperk.VehiclePerkFixture.DEFAULT_VEHICLE_PERK_UUID;
import static com.br.joaoptgaino.catalogservice.fixtures.vehicleperk.VehiclePerkFixture.getVehiclePerkEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class VehicleServiceImplTest {
    @Mock
    private VehicleRepository vehicleRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private VehiclePerkRepository vehiclePerkRepository;

    private final ModelMapper modelMapper = new ModelMapper();
    private VehicleServiceImpl vehicleService;


    @BeforeEach
    public void setup() {
        vehicleService = new VehicleServiceImpl(vehicleRepository, categoryRepository, vehiclePerkRepository, modelMapper);
    }

    @Test
    public void createVehicleShouldReturnSuccessful() {
        CategoryEntity category = getCategoryEntity("Category 1");
        VehiclePerkEntity vehiclePerk = getVehiclePerkEntity("Air conditioner");
        VehicleFormDTO vehicleFormDTO = getVehicleFormDTO("Chevrolet", "Omega", "ABC1234", VehicleType.CAR, "Black", 1999);
        VehicleEntity vehicle = getVehicleEntity("Chevrolet", "Omega", "ABC1234", VehicleType.CAR, "Black", 1999);

        when(categoryRepository.findById(DEFAULT_CATEGORY_UUID)).thenReturn(Optional.of(category));
        when(vehiclePerkRepository.findById(DEFAULT_VEHICLE_PERK_UUID)).thenReturn(Optional.of(vehiclePerk));
        when(vehicleRepository.save(any(VehicleEntity.class))).thenReturn(vehicle);

        VehicleDTO response = vehicleService.create(vehicleFormDTO);
        assertThat(response.getPlate()).isEqualTo(vehicle.getPlate());
    }

    @Test
    public void findAllVehicleShouldReturnSuccessful() {
        VehicleEntity vehicle = getVehicleEntity("Chevrolet", "Omega", "ABC1234", VehicleType.CAR, "Black", 1999);
        Page<VehicleEntity> page = new PageImpl<>(List.of(vehicle));
        Pageable pageable = PageRequest.of(0, 10);
        VehicleParamsDTO paramsDTO = new VehicleParamsDTO();
        when(vehicleRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(page);
        Page<VehicleDTO> response = vehicleService.findAll(pageable, paramsDTO);
        assertThat(response.getContent().get(0).getPlate()).isEqualTo(vehicle.getPlate());
    }

    @Test
    public void findOneVehicleShouldReturnSuccessful() {
        VehicleEntity vehicle = getVehicleEntity("Chevrolet", "Omega", "ABC1234", VehicleType.CAR, "Black", 1999);
        when(vehicleRepository.findByPlate("ABC1234")).thenReturn(Optional.of(vehicle));

        VehicleDTO response = vehicleService.findOne("ABC1234");
        assertThat(response.getPlate()).isEqualTo(vehicle.getPlate());
    }

    @Test
    public void findOneVehicleShouldReturnNotFound() {
        when(vehicleRepository.findByPlate("ABC1234")).thenReturn(Optional.empty());
        assertThatThrownBy(() -> vehicleService.findOne("ABC1234"))
                .isInstanceOf(BusinessException.class);
    }

    @Test
    public void updateVehicleShouldReturnSuccessful() {
        CategoryEntity category = getCategoryEntity("Category 1");
        VehiclePerkEntity vehiclePerk = getVehiclePerkEntity("Air conditioner");
        VehicleFormDTO vehicleFormDTO = getVehicleFormDTO("Chevrolet", "Omega", "ABC1234", VehicleType.CAR, "Black", 1999);
        VehicleEntity vehicle = getVehicleEntity("Chevrolet", "Omega", "ABC1234", VehicleType.CAR, "Black", 1999);

        when(vehicleRepository.findByPlate("ABC1234")).thenReturn(Optional.of(vehicle));
        when(categoryRepository.findById(DEFAULT_CATEGORY_UUID)).thenReturn(Optional.of(category));
        when(vehiclePerkRepository.findById(DEFAULT_VEHICLE_PERK_UUID)).thenReturn(Optional.of(vehiclePerk));
        when(vehicleRepository.save(any(VehicleEntity.class))).thenReturn(vehicle);

        VehicleDTO response = vehicleService.update("ABC1234", vehicleFormDTO);
        assertThat(response.getPlate()).isEqualTo(vehicle.getPlate());
    }

    @Test
    public void updateVehicleShouldReturnNotFound() {
        VehicleFormDTO vehicleFormDTO = getVehicleFormDTO("Chevrolet", "Omega", "ABC1234", VehicleType.CAR, "Black", 1999);
        when(vehicleRepository.findByPlate("ABC1234")).thenReturn(Optional.empty());
        assertThatThrownBy(() -> vehicleService.update("ABC1234", vehicleFormDTO))
                .isInstanceOf(BusinessException.class);
    }

    @Test
    public void deleteVehicleShouldReturnSuccessful() {
        VehicleEntity vehicle = getVehicleEntity("Chevrolet", "Omega", "ABC1234", VehicleType.CAR, "Black", 1999);
        when(vehicleRepository.findByPlate("ABC1234")).thenReturn(Optional.of(vehicle));
        vehicleService.delete("ABC1234");
    }

    @Test
    public void deleteVehicleShouldReturnNotFound() {
        when(vehicleRepository.findByPlate("ABC1234")).thenReturn(Optional.empty());
        assertThatThrownBy(() -> vehicleService.delete("ABC1234"))
                .isInstanceOf(BusinessException.class);
    }
}
