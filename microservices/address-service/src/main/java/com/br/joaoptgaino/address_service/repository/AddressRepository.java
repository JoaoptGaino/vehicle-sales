package com.br.joaoptgaino.address_service.repository;

import com.br.joaoptgaino.address_service.model.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<AddressEntity, UUID> {
}
