package com.br.joaoptgaino.catalogservice.service.impl;

import com.br.joaoptgaino.catalogservice.dto.category.CategoryDTO;
import com.br.joaoptgaino.catalogservice.dto.category.CategoryFormDTO;
import com.br.joaoptgaino.catalogservice.exceptions.BusinessException;
import com.br.joaoptgaino.catalogservice.model.CategoryEntity;
import com.br.joaoptgaino.catalogservice.repository.CategoryRepository;
import com.br.joaoptgaino.catalogservice.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public CategoryDTO create(CategoryFormDTO data) {
        CategoryEntity category = new CategoryEntity();
        modelMapper.map(data, category);
        CategoryEntity createdCategory = categoryRepository.save(category);
        log.info("Category with name {} created", data.getName());
        return modelMapper.map(createdCategory, CategoryDTO.class);
    }

    @Override
    public Page<CategoryDTO> findAll(Pageable pageable) {
        List<CategoryDTO> categories = categoryRepository.findAll(pageable)
                .getContent()
                .stream().map(content -> modelMapper.map(content, CategoryDTO.class))
                .toList();
        log.info("Returned {} categories", categories.size());
        return new PageImpl<>(categories);
    }

    @Override
    public CategoryDTO findOne(UUID id) {
        CategoryEntity category = this.getCategoryOrElseThrow(id);
        log.info("Category with id {} found", id);
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO update(UUID id, CategoryFormDTO data) {
        CategoryEntity category = this.getCategoryOrElseThrow(id);
        modelMapper.map(data, category);
        CategoryEntity updatedCategory = categoryRepository.save(category);
        log.info("Category with id {} updated", id);
        return modelMapper.map(updatedCategory, CategoryDTO.class);
    }

    @Override
    public void delete(UUID id) {
        CategoryEntity category = this.getCategoryOrElseThrow(id);
        categoryRepository.delete(category);
        log.info("Category with id {} deleted", id);
    }

    private CategoryEntity getCategoryOrElseThrow(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> BusinessException.builder()
                        .httpStatus(HttpStatus.NOT_FOUND.value())
                        .message(HttpStatus.NOT_FOUND.getReasonPhrase())
                        .errors(Collections.singletonList(String.format("Category with id %s not found", id)))
                        .build());
    }
}
