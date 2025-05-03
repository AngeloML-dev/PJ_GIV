package com.lozano.application.mapper;

import com.lozano.application.dto.Category.CategoryRequestDTO;
import com.lozano.application.dto.Category.CategoryResponseDTO;
import com.lozano.domain.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryRequestDTO dto) {
        return Category.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

    public CategoryResponseDTO toResponseDTO(Category category) {
        return CategoryResponseDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}
