package com.haiilo.checkout.offer.strategy;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class XItemsForYPriceOfferStrategyTest {

    @Test
    void apple_2_for_45_apply_correctly() {
        OfferStrategy offerStrategy = new XItemsForYPriceOfferStrategy(2, new BigDecimal("45.00"));
        assertEquals(new BigDecimal("30.00"), offerStrategy.apply(1, new BigDecimal("30.00")));
        assertEquals(new BigDecimal("45.00"), offerStrategy.apply(2, new BigDecimal("30.00")));
        assertEquals(new BigDecimal("75.00"), offerStrategy.apply(3, new BigDecimal("30.00")));
        assertEquals(new BigDecimal("165.00"), offerStrategy.apply(7, new BigDecimal("30.00")));
    }

    @Test
    void banana_3_for_130_apply_correctly() {
        OfferStrategy offerStrategy = new XItemsForYPriceOfferStrategy(3, new BigDecimal("130.00"));
        assertEquals(new BigDecimal("50.00"), offerStrategy.apply(1, new BigDecimal("50.00")));
        assertEquals(new BigDecimal("100.00"), offerStrategy.apply(2, new BigDecimal("50.00")));
        assertEquals(new BigDecimal("130.00"), offerStrategy.apply(3, new BigDecimal("50.00")));
        assertEquals(new BigDecimal("440.00"), offerStrategy.apply(10, new BigDecimal("50.00")));
    }

    @Test
    void fail_if_invalid_offer_parameters() {
        assertThrows(IllegalArgumentException.class, () -> new XItemsForYPriceOfferStrategy(0, BigDecimal.TEN));
        assertThrows(IllegalArgumentException.class, () -> new XItemsForYPriceOfferStrategy(-1, BigDecimal.TEN));
    }

    @Test
    void buy_3_for_10_applies_correctly() {
        OfferStrategy offerStrategy = new XItemsForYPriceOfferStrategy(3, new BigDecimal("10.00"));
        assertEquals(new BigDecimal("10.00"), offerStrategy.apply(3, new BigDecimal("5.00")));
        assertEquals(new BigDecimal("20.00"), offerStrategy.apply(6, new BigDecimal("5.00")));
        assertEquals(new BigDecimal("25.00"), offerStrategy.apply(7, new BigDecimal("5.00")));
        assertEquals(new BigDecimal("30.00"), offerStrategy.apply(9, new BigDecimal("5.00")));
    }

}
