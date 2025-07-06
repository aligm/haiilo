package com.haiilo.checkout.offer.service;

import com.haiilo.checkout.offer.domain.Offer;
import com.haiilo.checkout.offer.model.OfferDTO;
import com.haiilo.checkout.offer.repos.OfferRepository;
import com.haiilo.checkout.util.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OfferService {

    @Value("${app.offers.sorting_properties:name}")
    private String[] offersSortingProperties;

    private final OfferRepository offerRepository;

    public OfferService(final OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public List<OfferDTO> findAll() {
        final List<Offer> offers = offerRepository.findAll(Sort.by(offersSortingProperties));
        return offers.stream()
                .map(offer -> mapToDTO(offer, new OfferDTO()))
                .toList();
    }

    public OfferDTO get(final Long id) {
        return offerRepository.findById(id)
                .map(offer -> mapToDTO(offer, new OfferDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final OfferDTO offerDTO) {
        final Offer offer = new Offer();
        mapToEntity(offerDTO, offer);
        return offerRepository.save(offer).getId();
    }

    public void update(final Long id, final OfferDTO offerDTO) {
        final Offer offer = offerRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(offerDTO, offer);
        offerRepository.save(offer);
    }

    public void delete(final Long id) {
        offerRepository.deleteById(id);
    }

    private OfferDTO mapToDTO(final Offer offer, final OfferDTO offerDTO) {
        offerDTO.setId(offer.getId());
        offerDTO.setSku(offer.getSku());
        offerDTO.setName(offer.getName());
        offerDTO.setType(offer.getType());
        offerDTO.setDiscountPercentage(offer.getDiscountPercentage());
        offerDTO.setDescription(offer.getDescription());
        offerDTO.setxForYX(offer.getxForYX());
        offerDTO.setxForYY(offer.getxForYY());
        return offerDTO;
    }

    private Offer mapToEntity(final OfferDTO offerDTO, final Offer offer) {
        offer.setId(offerDTO.getId());
        offer.setSku(offerDTO.getSku());
        offer.setName(offerDTO.getName());
        offer.setType(offerDTO.getType());
        offer.setDiscountPercentage(offerDTO.getDiscountPercentage());
        offer.setxForYX(offerDTO.getxForYX());
        offer.setxForYY(offerDTO.getxForYY());
        offer.setDescription(offerDTO.getDescription());
        return offer;
    }

}
