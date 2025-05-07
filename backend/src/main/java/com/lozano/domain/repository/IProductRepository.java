package com.lozano.domain.repository;
import com.lozano.domain.entity.Category;
import com.lozano.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    void deleteByName(String name);
}
