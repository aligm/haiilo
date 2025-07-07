package com.haiilo.checkout.pricing;

import com.haiilo.checkout.AbstractDatabaseITTest;
import com.haiilo.checkout.offer.model.OfferDTO;
import com.haiilo.checkout.offer.service.OfferService;
import com.haiilo.checkout.product.model.ProductDTO;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.haiilo.checkout.offer.OfferType.X_ITEMS_FOR_Y_PRICE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest()
class CheckoutTotalCalculatorImplITTest extends AbstractDatabaseITTest {
    @Autowired
    private OfferService offerService;

    @Autowired
    private CheckoutTotalCalculatorImpl calculator;

    @BeforeEach
    void setUp() {
        OfferDTO applesOffer = createApplesOffer();
        offerService.create(applesOffer);

        OfferDTO bananasOffer = createBananasOffer();
        offerService.create(bananasOffer);
    }

    private static @NotNull OfferDTO createApplesOffer() {
        OfferDTO applesOffer = new OfferDTO();
        applesOffer.setSku("OIUFDSHK09u8uhINJK");
        applesOffer.setName("2 Apples for 45");
        applesOffer.setType(X_ITEMS_FOR_Y_PRICE);
        applesOffer.setxForYX(2);
        applesOffer.setFixedPrice(BigDecimal.valueOf(45));
        return applesOffer;
    }

    private static @NotNull OfferDTO createBananasOffer() {
        OfferDTO bananasOffer = new OfferDTO();
        bananasOffer.setSku("KLJDSF9823K");
        bananasOffer.setName("3 Bananas for 130");
        bananasOffer.setType(X_ITEMS_FOR_Y_PRICE);
        bananasOffer.setxForYX(3);
        bananasOffer.setFixedPrice(BigDecimal.valueOf(130));
        return bananasOffer;
    }

    @Test
    void peach_with_no_offer_apply_correctly() {
        assertEquals(new BigDecimal("60.00"), calculator.calculate(createPeach(1)));
        assertEquals(new BigDecimal("120.00"), calculator.calculate(createPeach(2)));
        assertEquals(new BigDecimal("180.00"), calculator.calculate(createPeach(3)));
        assertEquals(new BigDecimal("420.00"), calculator.calculate(createPeach(7)));
    }

    @Test
    void apple_2_for_45_apply_correctly() {
        assertEquals(new BigDecimal("30.00"), calculator.calculate(createApples(1)));
        assertEquals(new BigDecimal("45.00"), calculator.calculate(createApples(2)));
        assertEquals(new BigDecimal("75.00"), calculator.calculate(createApples(3)));
        assertEquals(new BigDecimal("165.00"), calculator.calculate(createApples(7)));
    }

    @Test
    void banana_3_for_130_apply_correctly() {
        assertEquals(new BigDecimal("50.00"), calculator.calculate(createBananas(1)));
        assertEquals(new BigDecimal("100.00"), calculator.calculate(createBananas(2)));
        assertEquals(new BigDecimal("130.00"), calculator.calculate(createBananas(3)));
        assertEquals(new BigDecimal("440.00"), calculator.calculate(createBananas(10)));
    }

    private List<ProductDTO> createPeach(int count) {
        List<ProductDTO> products = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            products.add(createPeachProduct());
        }
        return products;
    }

    private List<ProductDTO> createApples(int count) {
        List<ProductDTO> products = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            products.add(createAppleProduct());
        }
        return products;
    }

    private List<ProductDTO> createBananas(int count) {
        List<ProductDTO> products = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            products.add(createBananaProduct());
        }
        return products;
    }

    private ProductDTO createAppleProduct() {
        return new ProductDTO("OIUFDSHK09u8uhINJK", "Apple", new BigDecimal("30.00"));
    }

    private ProductDTO createBananaProduct() {
        return new ProductDTO("KLJDSF9823K", "Banana", new BigDecimal("50.00"));
    }

    private ProductDTO createPeachProduct() {
        return new ProductDTO("OUFIJSDNK898HJB9", "Peach", new BigDecimal("60.00"));
    }

}
