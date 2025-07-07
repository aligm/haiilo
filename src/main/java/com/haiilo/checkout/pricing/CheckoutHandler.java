package com.haiilo.checkout.pricing;

import com.haiilo.checkout.product.model.ProductDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/checkouts/prices/total", produces = MediaType.APPLICATION_JSON_VALUE)
public class CheckoutHandler {

    private final CheckoutTotalCalculator checkoutTotalCalculator;

    public CheckoutHandler(CheckoutTotalCalculator checkoutTotalCalculator) {
        this.checkoutTotalCalculator = checkoutTotalCalculator;
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<BigDecimal> calculateCheckoutTotal(@RequestBody final List<ProductDTO> productDTOS) {
        final BigDecimal total = checkoutTotalCalculator.calculate(productDTOS);
        return new ResponseEntity<>(total, HttpStatus.OK);
    }



}
