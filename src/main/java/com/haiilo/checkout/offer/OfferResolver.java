package com.haiilo.checkout.offer;

import com.haiilo.checkout.offer.strategy.OfferStrategy;

public interface OfferResolver<T> {
    OfferStrategy resolve(T key);
}
