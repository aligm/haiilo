package com.haiilo.checkout.product.rest;

import com.haiilo.checkout.product.model.ProductDTO;
import com.haiilo.checkout.product.service.ProductService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductResource {

    private final ProductService productService;

    public ProductResource(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{sku}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable(name = "sku") final String sku) {
        return ResponseEntity.ok(productService.get(sku));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Map<String, String>> createProduct(@RequestBody @Valid final ProductDTO productDTO) {
        final String createdSku = productService.create(productDTO);
        return new ResponseEntity<>(Collections.singletonMap("sku", createdSku), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{sku}")
    public ResponseEntity<Map<String, String>> updateProduct(@PathVariable(name = "sku") final String sku,
            @RequestBody @Valid final ProductDTO productDTO) {
        productService.update(sku, productDTO);
        return ResponseEntity.ok(Collections.singletonMap("sku", sku));
    }

    @DeleteMapping("/{sku}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "sku") final String sku) {
        productService.delete(sku);
        return ResponseEntity.noContent().build();
    }

}
