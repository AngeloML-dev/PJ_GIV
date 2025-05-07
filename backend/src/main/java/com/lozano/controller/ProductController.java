package com.lozano.controller;

import com.lozano.application.dto.Product.ProductRequestDTO;
import com.lozano.application.dto.Product.ProductResponseDTO;
import com.lozano.application.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;

    @GetMapping
    public List<ProductResponseDTO> listar() {
        return productService.listAll();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/name/{name}")
    public List<ProductResponseDTO> getProductsByName(@PathVariable String name) {
        return productService.findByName(name);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updatedProduct(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO requestDTO) {
        ProductResponseDTO updatedProduct = productService.update(id, requestDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO requestDTO){
        ProductResponseDTO createdProduct = productService.create(requestDTO);
        return ResponseEntity.status(201).body(createdProduct);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id){
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deleteProductByName(@PathVariable String name) {
        productService.deleteByName(name);
        return ResponseEntity.noContent().build();
    }

}
