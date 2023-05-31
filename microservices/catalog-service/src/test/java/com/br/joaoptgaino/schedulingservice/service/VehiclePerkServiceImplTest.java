package com.br.joaoptgaino.schedulingservice.service;

import com.br.joaoptgaino.catalogservice.dto.vehicleperks.VehiclePerkDTO;
import com.br.joaoptgaino.catalogservice.dto.vehicleperks.VehiclePerkFormDTO;
import com.br.joaoptgaino.catalogservice.exceptions.BusinessException;
import com.br.joaoptgaino.catalogservice.model.VehiclePerkEntity;
import com.br.joaoptgaino.catalogservice.repository.VehiclePerkRepository;
import com.br.joaoptgaino.catalogservice.service.impl.VehiclePerkServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.br.joaoptgaino.schedulingservice.fixtures.vehicleperk.VehiclePerkFixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class VehiclePerkServiceImplTest {

    @Mock
    private VehiclePerkRepository vehiclePerkRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private VehiclePerkServiceImpl vehiclePerkService;

    @BeforeEach
    public void setup() {
        vehiclePerkService = new VehiclePerkServiceImpl(vehiclePerkRepository, modelMapper);
    }

    @Test
    public void createVehiclePerkShouldReturnSuccessful() {
        VehiclePerkFormDTO vehiclePerkFormDTO = getVehiclePerkFormDTO("VehiclePerk 1");
        VehiclePerkEntity vehiclePerk = getVehiclePerkEntity("VehiclePerk 1");
        when(vehiclePerkRepository.save(any(VehiclePerkEntity.class))).thenReturn(vehiclePerk);

        VehiclePerkDTO response = vehiclePerkService.create(vehiclePerkFormDTO);
        assertThat(response.getName()).isEqualTo(vehiclePerk.getName());
    }

    @Test
    public void findAllVehiclePerksShouldReturnSuccessful() {
        VehiclePerkEntity vehiclePerk = getVehiclePerkEntity("VehiclePerk 1");
        Page<VehiclePerkEntity> page = new PageImpl<>(List.of(vehiclePerk));
        Pageable pageable = PageRequest.of(0, 10);

        when(vehiclePerkRepository.findAll(pageable)).thenReturn(page);

        Page<VehiclePerkDTO> response = vehiclePerkService.findAll(pageable);
        assertThat(response.getTotalElements()).isGreaterThan(0);
    }

    @Test
    public void updateVehiclePerkShouldReturnSuccessful() {
        VehiclePerkEntity vehiclePerk = getVehiclePerkEntity("VehiclePerk 1");
        VehiclePerkFormDTO vehiclePerkFormDTO = getVehiclePerkFormDTO("VehiclePerk 1");
        when(vehiclePerkRepository.findById(vehiclePerk.getId())).thenReturn(java.util.Optional.of(vehiclePerk));
        when(vehiclePerkRepository.save(vehiclePerk)).thenReturn(vehiclePerk);

        VehiclePerkDTO response = vehiclePerkService.update(vehiclePerk.getId(), vehiclePerkFormDTO);
        assertThat(response.getName()).isEqualTo(vehiclePerk.getName());
    }

    @Test
    public void updateVehiclePerkShouldReturnNotFound() {
        VehiclePerkFormDTO vehiclePerkFormDTO = getVehiclePerkFormDTO("Perk");
        when(vehiclePerkRepository.findById(DEFAULT_VEHICLE_PERK_UUID)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> vehiclePerkService.update(DEFAULT_VEHICLE_PERK_UUID, vehiclePerkFormDTO))
                .isInstanceOf(BusinessException.class);
    }

    @Test
    public void deleteVehiclePerkShouldReturnSuccessful() {
        VehiclePerkEntity vehiclePerk = getVehiclePerkEntity("VehiclePerk 1");
        when(vehiclePerkRepository.findById(vehiclePerk.getId())).thenReturn(java.util.Optional.of(vehiclePerk));

        vehiclePerkService.delete(vehiclePerk.getId());
    }

    @Test
    public void deleteVehiclePerkShouldReturnNotFound() {
        when(vehiclePerkRepository.findById(DEFAULT_VEHICLE_PERK_UUID)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> vehiclePerkService.delete(DEFAULT_VEHICLE_PERK_UUID))
                .isInstanceOf(BusinessException.class);
    }
}
