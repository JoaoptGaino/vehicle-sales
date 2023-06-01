package com.br.joaoptgaino.address_service.repository;

import com.br.joaoptgaino.address_service.model.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface StateRepository extends JpaRepository<StateEntity, UUID>, JpaSpecificationExecutor<StateEntity> {
}
