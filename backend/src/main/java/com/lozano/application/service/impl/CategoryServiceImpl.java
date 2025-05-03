package com.lozano.application.service.impl;

import com.lozano.application.dto.Category.CategoryRequestDTO;
import com.lozano.application.dto.Category.CategoryResponseDTO;
import com.lozano.application.mapper.CategoryMapper;
import com.lozano.application.service.ICategoryService;
import com.lozano.domain.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final ICategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponseDTO> listAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDTO getById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public CategoryResponseDTO create(CategoryRequestDTO dto) {
        var entity = categoryMapper.toEntity(dto);
        return categoryMapper.toResponseDTO(categoryRepository.save(entity));
    }

    @Override
    public CategoryResponseDTO update(Long id, CategoryRequestDTO dto) {
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        return categoryMapper.toResponseDTO(categoryRepository.save(category));
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
