package com.br.joaoptgaino.catalogservice.controller;

import com.br.joaoptgaino.catalogservice.dto.vehicleperks.VehiclePerkDTO;
import com.br.joaoptgaino.catalogservice.dto.vehicleperks.VehiclePerkFormDTO;
import com.br.joaoptgaino.catalogservice.service.VehiclePerkService;
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
import java.util.Optional;

import static com.br.joaoptgaino.catalogservice.fixtures.vehicleperk.VehiclePerkFixture.getVehiclePerkDTO;
import static com.br.joaoptgaino.catalogservice.fixtures.vehicleperk.VehiclePerkFixture.getVehiclePerkFormDTO;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class VehiclePerkControllerTest {
    @Mock
    private VehiclePerkService vehiclePerkService;

    private MockMvc mockMvc;
    private final Gson gson = new GsonBuilder()
            .create();
    private static final String ENDPOINT_VEHICLE_PERK = "/perks";

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new VehiclePerkController(vehiclePerkService))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    public void saveVehiclePerkShouldReturnSuccessful() throws Exception {
        VehiclePerkDTO vehiclePerk = getVehiclePerkDTO("VehiclePerk");
        VehiclePerkFormDTO formDTO = getVehiclePerkFormDTO("VehiclePerk");
        String vehiclePerkRequest = gson.toJson(vehiclePerk);

        when(vehiclePerkService.create(formDTO)).thenReturn(vehiclePerk);

        RequestBuilder request = MockMvcRequestBuilders.post(ENDPOINT_VEHICLE_PERK)
                .contentType(MediaType.APPLICATION_JSON)
                .content(vehiclePerkRequest);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(vehiclePerkRequest));
    }

    @Test
    public void findAllVehiclePerkShouldReturnSuccessful() throws Exception {
        VehiclePerkDTO vehiclePerk = getVehiclePerkDTO("VehiclePerk");
        Page<VehiclePerkDTO> vehicleResponse = new PageImpl<>(List.of(vehiclePerk));

        Pageable pageable = PageRequest.of(0, 10);

        when(vehiclePerkService.findAll(pageable)).thenReturn(vehicleResponse);

        RequestBuilder request = MockMvcRequestBuilders.get(ENDPOINT_VEHICLE_PERK)
                .contentType(MediaType.APPLICATION_JSON)
                .content(vehicleResponse.toString());

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name").value(vehiclePerk.getName()));
    }

    @Test
    public void updateVehiclePerkShouldReturnSuccessful() throws Exception {
        VehiclePerkDTO vehiclePerk = getVehiclePerkDTO("VehiclePerk");
        VehiclePerkFormDTO formDTO = getVehiclePerkFormDTO("VehiclePerk");
        String vehiclePerkRequest = gson.toJson(vehiclePerk);

        when(vehiclePerkService.update(vehiclePerk.getId(), formDTO)).thenReturn(vehiclePerk);

        RequestBuilder request = MockMvcRequestBuilders.put(ENDPOINT_VEHICLE_PERK + "/" + vehiclePerk.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(vehiclePerkRequest);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(vehiclePerkRequest));
    }

    @Test
    public void deleteVehicleShouldReturnSuccessfulNoContent() throws Exception {
        VehiclePerkDTO vehiclePerk = getVehiclePerkDTO("VehiclePerk");

        doNothing().when(vehiclePerkService).delete(vehiclePerk.getId());

        RequestBuilder request = MockMvcRequestBuilders.delete(ENDPOINT_VEHICLE_PERK + "/" + vehiclePerk.getId())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isNoContent());
    }
}
