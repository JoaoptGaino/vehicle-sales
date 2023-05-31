package com.br.joaoptgaino.address_service.service.impl;

import com.br.joaoptgaino.address_service.dto.address.AddressDTO;
import com.br.joaoptgaino.address_service.dto.address.AddressFormDTO;
import com.br.joaoptgaino.address_service.dto.address.AddressParamsDTO;
import com.br.joaoptgaino.address_service.exceptions.BusinessException;
import com.br.joaoptgaino.address_service.model.AddressEntity;
import com.br.joaoptgaino.address_service.repository.AddressRepository;
import com.br.joaoptgaino.address_service.service.AddressService;
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
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    @Override
    public AddressDTO create(AddressFormDTO data) {
        AddressEntity address = new AddressEntity();
        modelMapper.map(data, address);
        AddressEntity createdAddress = addressRepository.save(address);
        log.info("Address with street {} created", data.getStreet());
        return modelMapper.map(createdAddress, AddressDTO.class);

    }

    @Override
    public Page<AddressDTO> findAll(Pageable pageable, AddressParamsDTO paramsDTO) {
        List<AddressDTO> addresses = addressRepository.findAll(pageable)
                .getContent()
                .stream().map(content -> modelMapper.map(content, AddressDTO.class))
                .toList();
        log.info("Returned {} addresses", addresses.size());
        return new PageImpl<>(addresses);
    }

    @Override
    public AddressDTO findOne(UUID id) {
        AddressEntity address = this.getAddressOrElseThrow(id);
        log.info("Address with id {} found", id);
        return modelMapper.map(address, AddressDTO.class);
    }

    @Override
    public AddressDTO update(UUID id, AddressFormDTO data) {
        AddressEntity address = this.getAddressOrElseThrow(id);
        modelMapper.map(data, address);
        AddressEntity updatedAddress = addressRepository.save(address);
        log.info("Address with id {} updated", id);
        return modelMapper.map(updatedAddress, AddressDTO.class);
    }

    @Override
    public void delete(UUID id) {
        AddressEntity address = this.getAddressOrElseThrow(id);
        addressRepository.delete(address);
        log.info("Address with id {} deleted", id);
    }

    private AddressEntity getAddressOrElseThrow(UUID id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> BusinessException.builder()
                        .httpStatus(HttpStatus.NOT_FOUND.value())
                        .message(HttpStatus.NOT_FOUND.getReasonPhrase())
                        .errors(Collections.singletonList(String.format("Address with id %s not found", id)))
                        .build());
    }
}
