package com.br.joaoptgaino.address_service.service.impl;

import com.br.joaoptgaino.address_service.dto.state.StateDTO;
import com.br.joaoptgaino.address_service.dto.state.StateParamsDTO;
import com.br.joaoptgaino.address_service.repository.StateRepository;
import com.br.joaoptgaino.address_service.service.StateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class StateServiceImpl implements StateService {
    private final StateRepository stateRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<StateDTO> findAll(Pageable pageable, StateParamsDTO paramsDTO) {
        List<StateDTO> states = stateRepository.findAll()
                .stream()
                .map(content -> modelMapper.map(content, StateDTO.class))
                .toList();
        return new PageImpl<>(states);
    }

    @Override
    public StateDTO findOne(UUID id) {
        return null;
    }
}
