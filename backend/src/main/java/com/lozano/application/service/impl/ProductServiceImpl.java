package com.lozano.application.service.impl;

import com.lozano.application.dto.Product.ProductRequestDTO;
import com.lozano.application.dto.Product.ProductResponseDTO;
import com.lozano.application.mapper.ProductMapper;
import com.lozano.application.service.IProductService;
import com.lozano.domain.entity.Category;
import com.lozano.domain.entity.Product;
import com.lozano.domain.repository.ICategoryRepository;
import com.lozano.domain.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponseDTO> listAll() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new RuntimeException("No se encontraron productos");
        }
        return products.stream()
                .map(productMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de producto no válido");
        }
        return productRepository.findById(id)
                .map(productMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Producto no encontrada"));
    }
    @Override
    public List<ProductResponseDTO> findByName(String name) {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío");
        }
        Optional<Product> productOptional = productRepository.findByName(name);
        if (productOptional.isPresent()) {
            return List.of(productMapper.toResponseDTO(productOptional.get()));
        } else {
            throw new RuntimeException("No se encontró ningún producto con el nombre: " + name);
        }
    }

    @Override
    public ProductResponseDTO create(ProductRequestDTO dto) {
        validateProductRequestDTO(dto);

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("La categoría no es válida"));

        Product product = productMapper.toEntity(dto, category);
        productRepository.save(product);
        return productMapper.toResponseDTO(product);
    }

    @Override
    public ProductResponseDTO update(Long id, ProductRequestDTO dto) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de producto no válido");
        }

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("La categoría no es válida"));

        validateProductRequestDTO(dto);

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setMinStock(dto.getMinStock());
        product.setCategory(category);

        productRepository.save(product);
        return productMapper.toResponseDTO(product);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de producto no válido");
        }
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado");
        }
        productRepository.deleteById(id);
    }
        @Transactional
        @Override
        public void deleteByName(String name) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Nombre de producto no válido");
            }

            Optional<Product> productOptional = productRepository.findByName(name);

            if (productOptional.isEmpty()) {
                throw new RuntimeException("Producto no encontrado");
            }

            productRepository.deleteByName(name);
        }


    private void validateProductRequestDTO(ProductRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("El DTO de producto no puede ser nulo");
        }
        if (!StringUtils.hasText(dto.getName())) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
        }
        if (!StringUtils.hasText(dto.getDescription())) {
            throw new IllegalArgumentException("La descripción del producto no puede estar vacía");
        }
        if (dto.getPrice() == null || dto.getPrice() <= 0) {
            throw new IllegalArgumentException("El precio del producto debe ser mayor a cero");
        }
        if (dto.getStock() == null || dto.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        if (dto.getMinStock() == null || dto.getMinStock() < 0) {
            throw new IllegalArgumentException("El stock mínimo no puede ser negativo");
        }
        if (dto.getCategoryId() == null || dto.getCategoryId() <= 0) {
            throw new IllegalArgumentException("El ID de categoría no es válido");
        }
    }
}



