package com.haiilo.checkout.product.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;


public class ProductDTO {

    @NotNull
    @Size(max = 255)
    @ProductSkuUnique
    private String sku;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @PositiveOrZero
    private BigDecimal price;

    public String getSku() {
        return sku;
    }

    public void setSku(final String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
