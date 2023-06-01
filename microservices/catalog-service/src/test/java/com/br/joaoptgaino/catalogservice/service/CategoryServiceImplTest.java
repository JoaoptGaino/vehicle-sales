package com.br.joaoptgaino.catalogservice.service;


import com.br.joaoptgaino.catalogservice.dto.category.CategoryDTO;
import com.br.joaoptgaino.catalogservice.dto.category.CategoryFormDTO;
import com.br.joaoptgaino.catalogservice.exceptions.BusinessException;
import com.br.joaoptgaino.catalogservice.model.CategoryEntity;
import com.br.joaoptgaino.catalogservice.repository.CategoryRepository;
import com.br.joaoptgaino.catalogservice.service.impl.CategoryServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.br.joaoptgaino.catalogservice.fixtures.category.CategoryFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CategoryServiceImplTest {
    @Mock
    private CategoryRepository categoryRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private CategoryServiceImpl categoryService;

    @BeforeEach
    public void setup() {
        categoryService = new CategoryServiceImpl(categoryRepository, modelMapper);
    }

    @Test
    public void createCategoryShouldReturnSuccessful() {
        CategoryFormDTO categoryFormDTO = getCategoryFormDTO("Category 1");
        CategoryEntity category = getCategoryEntity("Category 1");
        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(category);
        CategoryDTO response = categoryService.create(categoryFormDTO);
        assertThat(response.getName()).isEqualTo(category.getName());
    }

    @Test
    public void findAllCategoryShouldReturnSuccessful() {
        CategoryEntity category = getCategoryEntity("Category 1");
        Page<CategoryEntity> page = new PageImpl<>(List.of(category));
        Pageable pageable = PageRequest.of(0, 10);

        when(categoryRepository.findAll(pageable)).thenReturn(page);
        Page<CategoryDTO> response = categoryService.findAll(pageable);
        assertThat(response.getTotalElements()).isGreaterThan(0);
    }

    @Test
    public void findOneCategoryShouldReturnSuccessful() {
        CategoryEntity category = getCategoryEntity("Category 1");
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        CategoryDTO response = categoryService.findOne(category.getId());
        assertThat(response.getName()).isEqualTo(category.getName());
    }

    @Test
    public void findOneCategoryShouldReturnNotFound() {
        when(categoryRepository.findById(DEFAULT_CATEGORY_UUID)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> categoryService.findOne(DEFAULT_CATEGORY_UUID))
                .isInstanceOf(BusinessException.class);
    }

    @Test
    public void updateCategoryShouldReturnSuccessful() {
        CategoryEntity category = getCategoryEntity("Category 1");
        CategoryFormDTO categoryFormDTO = getCategoryFormDTO("Category 1");
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(category);
        CategoryDTO response = categoryService.update(category.getId(), categoryFormDTO);
        assertThat(response.getName()).isEqualTo(category.getName());
    }

    @Test
    public void updateCategoryShouldReturnNotFound() {
        CategoryFormDTO categoryFormDTO = getCategoryFormDTO("Category 1");
        when(categoryRepository.findById(any())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> categoryService.update(DEFAULT_CATEGORY_UUID, categoryFormDTO))
                .isInstanceOf(BusinessException.class);
    }

    @Test
    public void deleteCategoryShouldReturnSuccessful() {
        CategoryEntity category = getCategoryEntity("Category 1");
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        categoryService.delete(category.getId());
    }

    @Test
    public void deleteCategoryShouldReturnNotFound() {
        when(categoryRepository.findById(any())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> categoryService.delete(DEFAULT_CATEGORY_UUID))
                .isInstanceOf(BusinessException.class);
    }
}
