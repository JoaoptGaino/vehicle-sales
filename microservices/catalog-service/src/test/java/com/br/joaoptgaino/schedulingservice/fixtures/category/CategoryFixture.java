package com.br.joaoptgaino.schedulingservice.fixtures.category;

import com.br.joaoptgaino.catalogservice.dto.category.CategoryDTO;
import com.br.joaoptgaino.catalogservice.dto.category.CategoryFormDTO;
import com.br.joaoptgaino.catalogservice.model.CategoryEntity;

import java.util.UUID;

public class CategoryFixture {
    public static UUID DEFAULT_CATEGORY_UUID = UUID.fromString("d290f1ee-6c54-4b01-90e6-d701748f0851");

    public static CategoryEntity getCategoryEntity(String name) {
        return CategoryEntity.builder()
                .id(DEFAULT_CATEGORY_UUID)
                .name(name)
                .build();
    }

    public static CategoryDTO getCategoryDTO(String name) {
        return CategoryDTO.builder()
                .id(DEFAULT_CATEGORY_UUID)
                .name(name)
                .build();
    }

    public static CategoryFormDTO getCategoryFormDTO(String name) {
        return CategoryFormDTO.builder()
                .name(name)
                .build();
    }
}
