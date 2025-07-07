package com.haiilo.checkout.offer.strategy;

import com.haiilo.checkout.offer.OfferType;

import java.math.BigDecimal;

public class NoOfferStrategy implements TypeAwareOfferStrategy {

    @Override
    public BigDecimal apply(int quantity, BigDecimal unitPrice) {
        return new BigDecimal(quantity).multiply(unitPrice);
    }

    @Override
    public OfferType getOfferType() {
        return OfferType.NONE;
    }

}
