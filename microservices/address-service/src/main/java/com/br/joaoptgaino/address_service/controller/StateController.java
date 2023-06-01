package com.br.joaoptgaino.address_service.controller;

import com.br.joaoptgaino.address_service.dto.state.StateDTO;
import com.br.joaoptgaino.address_service.dto.state.StateParamsDTO;
import com.br.joaoptgaino.address_service.service.StateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("states")
@RequiredArgsConstructor
@Slf4j
public class StateController {
    private final StateService stateService;

    @GetMapping
    public ResponseEntity<Page<StateDTO>> findAll(@PageableDefault Pageable pageable, @RequestParam(required = false) StateParamsDTO paramsDTO) {
        Page<StateDTO> states = stateService.findAll(pageable, paramsDTO);
        return ResponseEntity.ok(states);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StateDTO> findOne(@PathVariable UUID id) {
        StateDTO state = stateService.findOne(id);
        return ResponseEntity.ok(state);
    }
}
