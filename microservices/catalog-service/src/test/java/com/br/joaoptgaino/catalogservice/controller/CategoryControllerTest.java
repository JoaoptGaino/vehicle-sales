package com.br.joaoptgaino.catalogservice.controller;

import com.br.joaoptgaino.catalogservice.dto.category.CategoryDTO;
import com.br.joaoptgaino.catalogservice.dto.category.CategoryFormDTO;
import com.br.joaoptgaino.catalogservice.service.CategoryService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.br.joaoptgaino.catalogservice.fixtures.category.CategoryFixture.getCategoryDTO;
import static com.br.joaoptgaino.catalogservice.fixtures.category.CategoryFixture.getCategoryFormDTO;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class CategoryControllerTest {
    @Mock
    private CategoryService categoryService;

    private MockMvc mockMvc;
    private final Gson gson = new GsonBuilder()
            .create();
    private static final String ENDPOINT_CATEGORY = "/categories";

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new CategoryController(categoryService))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    public void saveCategoryShouldReturnSuccessful() throws Exception {
        CategoryDTO category = getCategoryDTO("Category");
        CategoryFormDTO formDTO = getCategoryFormDTO("Category");
        String categoryRequest = gson.toJson(category);

        when(categoryService.create(formDTO)).thenReturn(category);

        RequestBuilder request = MockMvcRequestBuilders.post(ENDPOINT_CATEGORY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(categoryRequest);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(categoryRequest));
    }

    @Test
    public void findAllCategoriesShouldReturnSuccessful() throws Exception {
        CategoryDTO category = getCategoryDTO("Category");
        Page<CategoryDTO> categoryResponse = new PageImpl<>(List.of(category));
        Pageable pageable = PageRequest.of(0, 10);

        when(categoryService.findAll(pageable)).thenReturn(categoryResponse);

        RequestBuilder request = MockMvcRequestBuilders.get(ENDPOINT_CATEGORY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(categoryResponse.toString());

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name").value(category.getName()));
    }

    @Test
    public void findOneCategoryShouldReturnSuccessful() throws Exception {
        CategoryDTO category = getCategoryDTO("Category");
        String categoryRequest = gson.toJson(category);

        when(categoryService.findOne(category.getId())).thenReturn(category);

        RequestBuilder request = MockMvcRequestBuilders.get(ENDPOINT_CATEGORY + "/" + category.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(categoryRequest);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(categoryRequest));
    }

    @Test
    public void updateCategoryShouldReturnSuccessful() throws Exception {
        CategoryDTO category = getCategoryDTO("Category");
        CategoryFormDTO formDTO = getCategoryFormDTO("Category");
        String categoryRequest = gson.toJson(category);

        when(categoryService.update(category.getId(), formDTO)).thenReturn(category);

        RequestBuilder request = MockMvcRequestBuilders.put(ENDPOINT_CATEGORY + "/" + category.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(categoryRequest);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(categoryRequest));
    }

    @Test
    public void deleteCategoryShouldReturnSuccessful() throws Exception {
        CategoryDTO category = getCategoryDTO("Category");
        String categoryRequest = gson.toJson(category);

        doNothing().when(categoryService).delete(category.getId());

        RequestBuilder request = MockMvcRequestBuilders.delete(ENDPOINT_CATEGORY + "/" + category.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(categoryRequest);

        mockMvc.perform(request)
                .andExpect(status().isNoContent());
    }
}
