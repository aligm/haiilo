package com.haiilo.checkout.pricing;

import com.haiilo.checkout.offer.SkuOfferResolver;
import com.haiilo.checkout.offer.domain.Offer;
import com.haiilo.checkout.offer.repos.OfferRepository;
import com.haiilo.checkout.offer.strategy.OfferStrategy;
import com.haiilo.checkout.offer.strategy.OfferStrategyFactory;
import com.haiilo.checkout.product.model.ProductDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

@Service
public class CheckoutTotalCalculatorImpl implements CheckoutTotalCalculator {

    private final OfferRepository offerRepository;
    private final OfferStrategyFactory offerStrategyFactory;

    public CheckoutTotalCalculatorImpl(OfferRepository offerRepository, OfferStrategyFactory offerStrategyFactory) {
        this.offerRepository = offerRepository;
        this.offerStrategyFactory = offerStrategyFactory;
    }

    @Override
    public BigDecimal calculate(List<ProductDTO> productDTOS) {
        List<Offer> offers = offerRepository.findAll();
        SkuOfferResolver offerResolver = new SkuOfferResolver(offerStrategyFactory, offers);
        Map<ProductDTO, Integer> basket = productDTOS.stream()
                .collect(groupingBy(productDTO -> productDTO, summingInt(e -> 1)));
        return basket.keySet().stream().map(product -> {
            OfferStrategy strategy = offerResolver.resolve(product.getSku());
            return strategy.apply(basket.get(product), product.getPrice());
        }).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

}
