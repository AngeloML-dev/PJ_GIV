package com.lozano.controller;

import com.lozano.application.dto.Category.CategoryResponseDTO;
import com.lozano.application.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService service;
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> list(){
        List<CategoryResponseDTO> categoryResponses = service.list();
        return ResponseEntity.ok(categoryResponses);
    }
}
