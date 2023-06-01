package com.br.joaoptgaino.catalogservice.controller;


import com.br.joaoptgaino.catalogservice.constants.VehicleType;
import com.br.joaoptgaino.catalogservice.dto.vehicle.VehicleDTO;
import com.br.joaoptgaino.catalogservice.dto.vehicle.VehicleFormDTO;
import com.br.joaoptgaino.catalogservice.dto.vehicle.VehicleParamsDTO;
import com.br.joaoptgaino.catalogservice.service.VehicleService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.br.joaoptgaino.catalogservice.fixtures.vehicle.VehicleFixture.getVehicleDTO;
import static com.br.joaoptgaino.catalogservice.fixtures.vehicle.VehicleFixture.getVehicleFormDTO;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class VehicleControllerTest {
    @Mock
    private VehicleService vehicleService;

    private MockMvc mockMvc;
    private final Gson gson = new GsonBuilder()
            .create();
    private static final String ENDPOINT_VEHICLE = "/vehicles";

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new VehicleController(vehicleService))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    public void saveVehicleShouldReturnSuccessful() throws Exception {
        VehicleDTO vehicle = getVehicleDTO("Chevrolet", "Omega", "ABC1234", VehicleType.CAR, "Black", 1998);
        VehicleFormDTO formDTO = getVehicleFormDTO("Chevrolet", "Omega", "ABC1234", VehicleType.CAR, "Black", 1998);
        String vehicleRequest = gson.toJson(vehicle);

        when(vehicleService.create(formDTO)).thenReturn(vehicle);

        RequestBuilder request = MockMvcRequestBuilders.post(ENDPOINT_VEHICLE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(vehicleRequest);

        mockMvc.perform(request)
                .andExpect(status().isCreated());
    }

    @Test
    public void findAllVehiclesShouldReturnSuccessful() throws Exception {
        VehicleDTO vehicle = getVehicleDTO("Chevrolet", "Omega", "ABC1234", VehicleType.CAR, "Black", 1998);
        Page<VehicleDTO> vehicleResponse = new PageImpl<>(List.of(vehicle));
        Pageable pageable = PageRequest.of(0, 10);
        VehicleParamsDTO paramsDTO = new VehicleParamsDTO();

        when(vehicleService.findAll(pageable, paramsDTO)).thenReturn(vehicleResponse);

        RequestBuilder request = MockMvcRequestBuilders.get(ENDPOINT_VEHICLE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(vehicleResponse.toString());
        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void findOneVehicleShouldReturnSuccessful() throws Exception {
        VehicleDTO vehicle = getVehicleDTO("Chevrolet", "Omega", "ABC1234", VehicleType.CAR, "Black", 1998);
        String vehicleRequest = gson.toJson(vehicle);

        when(vehicleService.findOne(vehicle.getPlate())).thenReturn(vehicle);

        RequestBuilder request = MockMvcRequestBuilders.get(ENDPOINT_VEHICLE + "/" + vehicle.getPlate())
                .contentType(MediaType.APPLICATION_JSON)
                .content(vehicleRequest);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(vehicleRequest));
    }

    @Test
    public void updateVehicleShouldReturnSuccessful() throws Exception {
        VehicleDTO vehicle = getVehicleDTO("Chevrolet", "Omega", "ABC1234", VehicleType.CAR, "Black", 1998);
        VehicleFormDTO formDTO = getVehicleFormDTO("Chevrolet", "Omega", "ABC1234", VehicleType.CAR, "Black", 1998);
        String vehicleRequest = gson.toJson(vehicle);

        when(vehicleService.update(vehicle.getPlate(), formDTO)).thenReturn(vehicle);

        RequestBuilder request = MockMvcRequestBuilders.put(ENDPOINT_VEHICLE + "/" + vehicle.getPlate())
                .contentType(MediaType.APPLICATION_JSON)
                .content(vehicleRequest);

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void deleteVehicleShouldReturnSuccessful() throws Exception {
        VehicleDTO vehicle = getVehicleDTO("Chevrolet", "Omega", "ABC1234", VehicleType.CAR, "Black", 1998);
        String vehicleRequest = gson.toJson(vehicle);

        doNothing().when(vehicleService).delete(vehicle.getPlate());

        RequestBuilder request = MockMvcRequestBuilders.delete(ENDPOINT_VEHICLE + "/" + vehicle.getPlate())
                .contentType(MediaType.APPLICATION_JSON)
                .content(vehicleRequest);

        mockMvc.perform(request)
                .andExpect(status().isNoContent());
    }
}
