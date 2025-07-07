package com.haiilo.checkout.pricing;


import com.haiilo.checkout.product.model.ProductDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CheckoutTotalCalculator {
    BigDecimal calculate(List<ProductDTO> productDTOS);
}
