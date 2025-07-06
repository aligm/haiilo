package com.haiilo.checkout.product.repos;

import com.haiilo.checkout.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, String> {

    boolean existsBySkuIgnoreCase(String sku);

    Optional<Product> findBySku(String sku);

    void deleteBySku(String sku);
}
