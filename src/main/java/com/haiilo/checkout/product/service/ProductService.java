package com.haiilo.checkout.product.service;

import com.haiilo.checkout.product.domain.Product;
import com.haiilo.checkout.product.model.ProductDTO;
import com.haiilo.checkout.product.repos.ProductRepository;
import com.haiilo.checkout.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> findAll() {
        final List<Product> products = productRepository.findAll(Sort.by("name"));
        return products.stream()
                .map(product -> mapToDTO(product, new ProductDTO()))
                .toList();
    }

    public ProductDTO get(final String sku) {
        return productRepository.findBySku(sku)
                .map(product -> mapToDTO(product, new ProductDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public String create(final ProductDTO productDTO) {
        final Product product = new Product();
        mapToEntity(productDTO, product);
        return productRepository.save(product).getSku();
    }

    public void update(final String sku, final ProductDTO productDTO) {
        final Product product = productRepository.findById(sku)
                .orElseThrow(NotFoundException::new);
        mapToEntity(productDTO, product);
        productRepository.save(product);
    }

    public void delete(final String sku) {
        productRepository.deleteBySku(sku);
    }

    private ProductDTO mapToDTO(final Product product, final ProductDTO productDTO) {
        productDTO.setPrice(product.getPrice());
        productDTO.setSku(product.getSku());
        productDTO.setName(product.getName());
        return productDTO;
    }

    private Product mapToEntity(final ProductDTO productDTO, final Product product) {
        product.setPrice(productDTO.getPrice());
        product.setSku(productDTO.getSku());
        product.setName(productDTO.getName());
        return product;
    }

    public boolean skuExists(final String sku) {
        return productRepository.existsBySkuIgnoreCase(sku);
    }

}
