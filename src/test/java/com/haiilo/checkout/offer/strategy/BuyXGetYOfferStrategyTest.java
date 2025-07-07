package com.haiilo.checkout.offer.strategy;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BuyXGetYOfferStrategyTest {

    @Test
    void buy_3_get_5_offer_applies_correctly() {
        OfferStrategy offerStrategy = new BuyXGetYOfferStrategy(3, 5);
        assertEquals(new BigDecimal("10.00"), offerStrategy.apply(3, new BigDecimal("2.00")));
        assertEquals(new BigDecimal("20.00"), offerStrategy.apply(6, new BigDecimal("2.00")));
        assertEquals(new BigDecimal("22.00"), offerStrategy.apply(7, new BigDecimal("2.00")));
        assertEquals(new BigDecimal("30.00"), offerStrategy.apply(9, new BigDecimal("2.00")));
    }

    @Test
    void fail_if_invalid_offer_parameters() {
        assertThrows(IllegalArgumentException.class, () -> new BuyXGetYOfferStrategy(0, 3));
        assertThrows(IllegalArgumentException.class, () -> new BuyXGetYOfferStrategy(3, 0));
        assertThrows(IllegalArgumentException.class, () -> new BuyXGetYOfferStrategy(3, 2));
        assertThrows(IllegalArgumentException.class, () -> new BuyXGetYOfferStrategy(-2, 3));
        assertThrows(IllegalArgumentException.class, () -> new BuyXGetYOfferStrategy(2, -3));
    }

}
