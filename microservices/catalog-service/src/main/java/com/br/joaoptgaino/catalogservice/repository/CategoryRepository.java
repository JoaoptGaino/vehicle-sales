package com.br.joaoptgaino.catalogservice.repository;

import com.br.joaoptgaino.catalogservice.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
}
