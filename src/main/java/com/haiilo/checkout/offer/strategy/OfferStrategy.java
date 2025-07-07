package com.haiilo.checkout.offer.strategy;

import java.math.BigDecimal;

public interface OfferStrategy {
    BigDecimal apply(int quantity, BigDecimal unitPrice);
}
