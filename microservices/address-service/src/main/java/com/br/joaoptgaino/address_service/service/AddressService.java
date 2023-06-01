package com.br.joaoptgaino.address_service.service;

import com.br.joaoptgaino.address_service.dto.address.AddressDTO;
import com.br.joaoptgaino.address_service.dto.address.AddressFormDTO;
import com.br.joaoptgaino.address_service.dto.address.AddressParamsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AddressService {
    AddressDTO create(AddressFormDTO data);

    Page<AddressDTO> findAll(Pageable pageable, AddressParamsDTO paramsDTO);

    AddressDTO findOne(UUID id);

    AddressDTO update(UUID id, AddressFormDTO data);

    void delete(UUID id);
}
