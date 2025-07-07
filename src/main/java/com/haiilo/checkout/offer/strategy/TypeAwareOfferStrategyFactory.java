package com.haiilo.checkout.offer.strategy;

import com.haiilo.checkout.offer.domain.Offer;
import org.springframework.stereotype.Service;

@Service
public class TypeAwareOfferStrategyFactory implements OfferStrategyFactory {

    public OfferStrategy create(Offer offer) {
        return switch (offer.getType()) {
            case BUY_X_GET_Y -> new BuyXGetYOfferStrategy(offer.getxForYX(), offer.getxForYY());
            case X_ITEMS_FOR_Y_PRICE -> new XItemsForYPriceOfferStrategy(offer.getxForYX(), offer.getFixedPrice());
            case PERCENTAGE -> throw new UnsupportedOperationException("Percentage offers are not supported yet");
            default -> new NoOfferStrategy();
        };
    }

}
