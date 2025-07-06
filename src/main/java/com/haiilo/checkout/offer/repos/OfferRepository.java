package com.haiilo.checkout.offer.repos;

import com.haiilo.checkout.offer.domain.Offer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OfferRepository extends JpaRepository<Offer, Long> {
}
