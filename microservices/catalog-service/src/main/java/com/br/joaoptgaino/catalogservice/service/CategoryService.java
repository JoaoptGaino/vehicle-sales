package com.br.joaoptgaino.catalogservice.service;

import com.br.joaoptgaino.catalogservice.dto.category.CategoryDTO;
import com.br.joaoptgaino.catalogservice.dto.category.CategoryFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CategoryService {
    CategoryDTO create(CategoryFormDTO data);
    Page<CategoryDTO> findAll(Pageable pageable);
    CategoryDTO findOne(UUID id);
    CategoryDTO update(UUID id, CategoryFormDTO data);
    void delete(UUID id);
}
