package com.br.joaoptgaino.address_service.repository;

import com.br.joaoptgaino.address_service.model.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CityRepository extends JpaRepository<CityEntity, UUID> {
}
