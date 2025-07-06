package com.haiilo.checkout.offer.model;

import com.haiilo.checkout.offer.OfferType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;


public class OfferDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String sku;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    private OfferType type;

    @PositiveOrZero
    private Short discountPercentage;

    @Positive
    private Integer xForYX;

    @Positive
    private Integer xForYY;

    private String description;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Short discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public OfferType getType() {
        return type;
    }

    public void setType(OfferType type) {
        this.type = type;
    }

    public Integer getxForYX() {
        return xForYX;
    }

    public void setxForYX(Integer xForYX) {
        this.xForYX = xForYX;
    }

    public Integer getxForYY() {
        return xForYY;
    }

    public void setxForYY(Integer xForYY) {
        this.xForYY = xForYY;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
