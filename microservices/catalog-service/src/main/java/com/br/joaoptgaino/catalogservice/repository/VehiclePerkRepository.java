package com.br.joaoptgaino.catalogservice.repository;

import com.br.joaoptgaino.catalogservice.model.VehiclePerkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VehiclePerkRepository extends JpaRepository<VehiclePerkEntity, UUID> {
}
