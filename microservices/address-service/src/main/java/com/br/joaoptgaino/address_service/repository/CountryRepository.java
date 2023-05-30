package com.br.joaoptgaino.address_service.repository;

import com.br.joaoptgaino.address_service.model.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CountryRepository extends JpaRepository<CountryEntity, UUID> {
}
