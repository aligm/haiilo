package com.haiilo.checkout.offer.strategy;

import com.haiilo.checkout.offer.OfferType;

import java.math.BigDecimal;

public class XItemsForYPriceOfferStrategy implements TypeAwareOfferStrategy {

    private final int x; // Number of items to buy
    private final BigDecimal y; // Price for x items to be paid

    public XItemsForYPriceOfferStrategy(int x, BigDecimal y) {
        if (x <= 0) {
            throw new IllegalArgumentException("Invalid offer parameters: x must be > 0");
        }
        this.x = x;
        this.y = y;
    }

    @Override
    public BigDecimal apply(int quantity, BigDecimal unitPrice) {
        int groups = quantity/x;
        int remainder = quantity % x;
        return y.multiply(new BigDecimal(groups)).add(new BigDecimal(remainder).multiply(unitPrice));
    }

    @Override
    public OfferType getOfferType() {
        return OfferType.X_ITEMS_FOR_Y_PRICE;
    }

}
