package com.lozano.controller;

import com.lozano.application.dto.Category.CategoryRequestDTO;
import com.lozano.application.dto.Category.CategoryResponseDTO;
import com.lozano.application.service.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;

    @GetMapping
    public List<CategoryResponseDTO> listar() {
        return categoryService.listAll();
    }

    @GetMapping("/{id}")
    public CategoryResponseDTO getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @GetMapping("/name/{name}")
    public List<CategoryResponseDTO> getCategoriesByName(@PathVariable String name) {
        return categoryService.findByName(name);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequestDTO requestDTO) {
        CategoryResponseDTO updatedCategory = categoryService.update(id, requestDTO);
        return ResponseEntity.ok(updatedCategory);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryRequestDTO requestDTO){
        CategoryResponseDTO createdCategory = categoryService.create(requestDTO);
        return ResponseEntity.status(201).body(createdCategory);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id){
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deleteCategoryByName(@PathVariable String name) {
        categoryService.deleteByName(name);
        return ResponseEntity.noContent().build();
    }

}
