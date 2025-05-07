package com.lozano.application.service;

import com.lozano.application.dto.Product.ProductRequestDTO;
import com.lozano.application.dto.Product.ProductResponseDTO;

import java.util.List;

public interface IProductService {
    List<ProductResponseDTO> listAll();
    ProductResponseDTO findById(Long id);
    List<ProductResponseDTO> findByName(String name);
    ProductResponseDTO create(ProductRequestDTO dto);
    ProductResponseDTO update(Long id, ProductRequestDTO dto);
    void deleteById(Long id);
    void deleteByName(String name);
}
