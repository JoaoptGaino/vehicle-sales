package com.br.joaoptgaino.catalogservice.controller;

import com.br.joaoptgaino.catalogservice.dto.category.CategoryDTO;
import com.br.joaoptgaino.catalogservice.dto.category.CategoryFormDTO;
import com.br.joaoptgaino.catalogservice.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("categories")
@Slf4j
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody @Valid CategoryFormDTO data) {
        CategoryDTO categoryDTO = categoryService.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDTO);
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> findAll(@PageableDefault Pageable pageable) {
        Page<CategoryDTO> categories = categoryService.findAll(pageable);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findOne(@PathVariable UUID id) {
        CategoryDTO categoryDTO = categoryService.findOne(id);
        return ResponseEntity.ok(categoryDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable UUID id, @RequestBody @Valid CategoryFormDTO data) {
        CategoryDTO categoryDTO = categoryService.update(id, data);
        return ResponseEntity.ok(categoryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
