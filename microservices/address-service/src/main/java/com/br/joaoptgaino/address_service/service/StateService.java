package com.br.joaoptgaino.address_service.service;

import com.br.joaoptgaino.address_service.dto.state.StateDTO;
import com.br.joaoptgaino.address_service.dto.state.StateParamsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface StateService {

    Page<StateDTO> findAll(Pageable pageable, StateParamsDTO paramsDTO);

    StateDTO findOne(UUID id);
}
