package com.haiilo.checkout.offer.strategy;

import com.haiilo.checkout.offer.OfferType;

public interface TypeAwareOfferStrategy extends OfferStrategy {
    OfferType getOfferType();
}
