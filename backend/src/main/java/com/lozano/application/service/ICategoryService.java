package com.lozano.application.service;

import com.lozano.application.dto.Category.CategoryRequestDTO;
import com.lozano.application.dto.Category.CategoryResponseDTO;

import java.util.List;

import java.util.List;

public interface ICategoryService {
    List<CategoryResponseDTO> listAll();
    CategoryResponseDTO getById(Long id);
    CategoryResponseDTO create(CategoryRequestDTO dto);
    CategoryResponseDTO update(Long id, CategoryRequestDTO dto);
    void delete(Long id);
}
