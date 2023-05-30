package com.br.joaoptgaino.catalogservice.repository;

import com.br.joaoptgaino.catalogservice.model.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface VehicleRepository extends JpaRepository<VehicleEntity, UUID>, JpaSpecificationExecutor<VehicleEntity> {

    @Query(value = "SELECT * FROM vehicle where plate = ?1", nativeQuery = true)
    Optional<VehicleEntity> findByPlate(String plate);
}
