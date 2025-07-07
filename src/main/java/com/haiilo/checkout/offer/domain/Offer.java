package com.haiilo.checkout.offer.domain;

import com.haiilo.checkout.offer.OfferType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

import java.math.BigDecimal;


@Entity
public class Offer {

    @Id
    @SequenceGenerator(
        name = "primary_sequence",
        sequenceName = "primary_sequence",
        allocationSize = 1,
        initialValue = 10000
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "primary_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String sku;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private OfferType type;

    @Column
    private Short discountPercentage;

    @Column
    private Integer xForYX;

    @Column
    private Integer xForYY;

    @Column
    private BigDecimal fixedPrice;

    @Column
    private String description;

    public Offer() {
    }

    public Offer(Long id, String sku, String name, OfferType type, Short discountPercentage, Integer xForYX, Integer xForYY, BigDecimal fixedPrice, String description) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.type = type;
        this.discountPercentage = discountPercentage;
        this.xForYX = xForYX;
        this.xForYY = xForYY;
        this.fixedPrice = fixedPrice;
        this.description = description;
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

    public String getSku() {
        return sku;
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

    public BigDecimal getFixedPrice() {
        return fixedPrice;
    }

    public void setFixedPrice(BigDecimal fixedPrice) {
        this.fixedPrice = fixedPrice;
    }
}
