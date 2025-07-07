package com.haiilo.checkout.offer;

import com.haiilo.checkout.offer.domain.Offer;
import com.haiilo.checkout.offer.strategy.NoOfferStrategy;
import com.haiilo.checkout.offer.strategy.OfferStrategy;
import com.haiilo.checkout.offer.strategy.OfferStrategyFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkuOfferResolver implements OfferResolver<String> {
    private final Map<String, OfferStrategy> strategyMap = new HashMap<>();
    private final List<Offer> offers;


    public SkuOfferResolver(OfferStrategyFactory offerStrategyFactory, List<Offer> offers) {
        this.offers = offers;
        initializeStrategyMap(offerStrategyFactory);
    }

    private void initializeStrategyMap(OfferStrategyFactory offerStrategyFactory) {
        offers.forEach(offer -> {
            OfferStrategy strategy = offerStrategyFactory.create(offer);
            strategyMap.put(offer.getSku(), strategy);
        });
    }

    @Override
    public OfferStrategy resolve(String key) {
        return strategyMap.getOrDefault(key, new NoOfferStrategy());
    }

}
