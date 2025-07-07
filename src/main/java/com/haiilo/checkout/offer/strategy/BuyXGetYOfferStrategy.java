package com.haiilo.checkout.offer.strategy;

import com.haiilo.checkout.offer.OfferType;

import java.math.BigDecimal;


public class BuyXGetYOfferStrategy implements TypeAwareOfferStrategy {

    private final OfferType offerType = OfferType.BUY_X_GET_Y;
    private final int x; // Number of items to buy
    private final int y; // Number of items to get

    public BuyXGetYOfferStrategy(int x, int y) {
        if (x <= 0 || y <= x) {
            throw new IllegalArgumentException("Invalid offer parameters: x must be > 0 and y must be > x");
        }
        this.x = x;
        this.y = y;
    }

    @Override
    public BigDecimal apply(int quantity, BigDecimal unitPrice) {
        int groups = quantity/x;
        int remainder = quantity % x;
        return new BigDecimal(groups * y + remainder).multiply(unitPrice);
    }

    @Override
    public OfferType getOfferType() {
        return offerType;
    }
}
