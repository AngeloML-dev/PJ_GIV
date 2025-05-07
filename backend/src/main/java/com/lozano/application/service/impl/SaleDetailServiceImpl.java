package com.lozano.application.service.impl;

import com.lozano.application.dto.SaleDetail.SaleDetailRequestDTO;
import com.lozano.application.dto.SaleDetail.SaleDetailResponseDTO;
import com.lozano.application.mapper.SaleDetailMapper;
import com.lozano.application.service.ISaleDetailService;
import com.lozano.domain.entity.Product;
import com.lozano.domain.entity.Sale;
import com.lozano.domain.entity.SaleDetail;
import com.lozano.domain.repository.IProductRepository;
import com.lozano.domain.repository.ISaleDetailRepository;
import com.lozano.domain.repository.ISaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleDetailServiceImpl implements ISaleDetailService {

    private final ISaleDetailRepository saleDetailRepository;
    private final ISaleRepository saleRepository;
    private final IProductRepository productRepository;
    private final SaleDetailMapper saleDetailMapper;

    @Override
    public SaleDetailResponseDTO findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de detalle no válido");
        }
        return saleDetailRepository.findById(id)
                .map(saleDetailMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Detalle de venta no encontrado"));
    }

    @Override
    public List<SaleDetailResponseDTO> findBySaleId(Long saleId) {
        if (saleId == null || saleId <= 0) {
            throw new IllegalArgumentException("ID de venta no válido");
        }
        List<SaleDetail> details = saleDetailRepository.findBySaleId(saleId);
        if (details.isEmpty()) {
            throw new RuntimeException("No se encontraron detalles para la venta especificada");
        }
        return details.stream()
                .map(saleDetailMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<SaleDetailResponseDTO> findByProductId(Long productId) {
        if (productId == null || productId <= 0) {
            throw new IllegalArgumentException("ID de producto no válido");
        }
        List<SaleDetail> details = saleDetailRepository.findByProductId(productId);
        if (details.isEmpty()) {
            throw new RuntimeException("No se encontraron detalles para el producto especificado");
        }
        return details.stream()
                .map(saleDetailMapper::toResponseDTO)
                .toList();
    }

    @Override
    public SaleDetailResponseDTO create(SaleDetailRequestDTO dto) {
        validateSaleDetailRequestDTO(dto);

        Sale sale = saleRepository.findById(dto.getSaleId())
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        SaleDetail detail = saleDetailMapper.toEntity(dto, product);
        detail.setSale(sale);
        detail.calculateSubtotal(); // Ahora es público

        SaleDetail savedDetail = saleDetailRepository.save(detail);
        sale.calculateTotals();
        saleRepository.save(sale);

        return saleDetailMapper.toResponseDTO(savedDetail);
    }

    @Override
    public SaleDetailResponseDTO update(Long id, SaleDetailRequestDTO dto) {
        SaleDetail existingDetail = saleDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de venta no encontrado"));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        saleDetailMapper.updateEntityFromDTO(dto, existingDetail, product);
        existingDetail.calculateSubtotal();

        SaleDetail updatedDetail = saleDetailRepository.save(existingDetail);
        updatedDetail.getSale().calculateTotals(); // Recalcular totales de la venta
        saleRepository.save(updatedDetail.getSale());

        return saleDetailMapper.toResponseDTO(updatedDetail);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de detalle no válido");
        }
        SaleDetail detail = saleDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de venta no encontrado"));

        Sale sale = detail.getSale();
        saleDetailRepository.deleteById(id);
        sale.calculateTotals(); // Actualizar totales de la venta
        saleRepository.save(sale);
    }

    @Override
    public boolean existsByIdAndSaleId(Long detailId, Long saleId) {
        if (detailId == null || detailId <= 0 || saleId == null || saleId <= 0) {
            throw new IllegalArgumentException("IDs no válidos");
        }
        return saleDetailRepository.existsByIdAndSaleId(detailId, saleId);
    }

    private void validateSaleDetailRequestDTO(SaleDetailRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("DTO de detalle no puede ser nulo");
        }
        if (dto.getSaleId() == null || dto.getSaleId() <= 0) {
            throw new IllegalArgumentException("Venta no válida");
        }
        if (dto.getProductId() == null || dto.getProductId() <= 0) {
            throw new IllegalArgumentException("Producto no válido");
        }
        if (dto.getQuantity() == null || dto.getQuantity() <= 0) {
            throw new IllegalArgumentException("Cantidad no válida");
        }
    }
}
