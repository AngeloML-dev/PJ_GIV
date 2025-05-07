package com.lozano.application.service.impl;

import com.lozano.application.dto.Category.CategoryRequestDTO;
import com.lozano.application.dto.Category.CategoryResponseDTO;
import com.lozano.application.mapper.CategoryMapper;
import com.lozano.application.service.ICategoryService;
import com.lozano.domain.entity.Category;
import com.lozano.domain.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final ICategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponseDTO> listAll() {
        List<Category> categorys = categoryRepository.findAll();
        if (categorys.isEmpty()) {
            throw new RuntimeException("No se encontraron categorias");
        }
        return categorys.stream()
                .map(categoryMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDTO findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de categoria no válido");
        }
        return categoryRepository.findById(id)
                .map(categoryMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    @Override
    public List<CategoryResponseDTO> findByName(String name) {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException("El nombre de categoria no puede estar vacío");
        }
        Optional<Category> categoryOptional = categoryRepository.findByName(name);
        if (categoryOptional.isPresent()) {
            return List.of(categoryMapper.toResponseDTO(categoryOptional.get()));
        } else {
            throw new RuntimeException("No se encontró ningúna categoria con el nombre: " + name);
        }
    }

    @Override
    public CategoryResponseDTO create(CategoryRequestDTO dto) {
        validateCategoryRequestDTO(dto);

        Category category = categoryMapper.toEntity(dto);
        categoryRepository.save(category);
        return categoryMapper.toResponseDTO(category);
    }

    @Override
    public CategoryResponseDTO update(Long id, CategoryRequestDTO dto) {
        validateCategoryRequestDTO(dto);

        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        categoryRepository.save(category);
        return categoryMapper.toResponseDTO(category);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de categoria no válido");
        }
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Categoria no encontrado");
        }
        categoryRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre de usuario no válido");
        }

        Optional<Category> categoryOptional = categoryRepository.findByName(name);

        if (categoryOptional.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        categoryRepository.deleteByName(name);
    }

    private void validateCategoryRequestDTO(CategoryRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("El DTO de categoría no puede ser nulo");
        }
        if (!StringUtils.hasText(dto.getName())) {
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío");
        }
        if (!StringUtils.hasText(dto.getDescription())) {
            throw new IllegalArgumentException("La descripción de la categoría no puede estar vacía");
        }
    }
}



