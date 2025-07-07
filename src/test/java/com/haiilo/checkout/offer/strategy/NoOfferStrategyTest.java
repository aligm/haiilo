package com.haiilo.checkout.offer.strategy;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class NoOfferStrategyTest {

    @Test
    void no_offer_applies_correctly() {
        OfferStrategy offerStrategy = new NoOfferStrategy();
        assertEquals(new BigDecimal("6.00"), offerStrategy.apply(3, new BigDecimal("2.00")));
        assertEquals(new BigDecimal("12.00"), offerStrategy.apply(6, new BigDecimal("2.00")));
        assertEquals(new BigDecimal("14.00"), offerStrategy.apply(7, new BigDecimal("2.00")));
        assertEquals(new BigDecimal("18.00"), offerStrategy.apply(9, new BigDecimal("2.00")));
    }

}
