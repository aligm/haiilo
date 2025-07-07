package com.haiilo.checkout.offer.strategy;

import com.haiilo.checkout.offer.domain.Offer;

public interface OfferStrategyFactory {
    OfferStrategy create(Offer offer);
}
