package com.lozano.application.service;

import com.lozano.application.dto.Category.CategoryRequestDTO;
import com.lozano.application.dto.Category.CategoryResponseDTO;

import java.util.List;

import java.util.List;

public interface ICategoryService {
    List<CategoryResponseDTO> listAll();
    CategoryResponseDTO findById(Long id);
    List<CategoryResponseDTO> findByName(String name);
    CategoryResponseDTO create(CategoryRequestDTO dto);
    CategoryResponseDTO update(Long id, CategoryRequestDTO dto);
    void deleteById(Long id);
    void deleteByName(String name);
}
