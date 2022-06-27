package com.mobile.services;


import com.mobile.models.dtos.OfferDto;

import java.util.List;
import java.util.UUID;

public interface OfferService {

    List<OfferDto> getAllOffers();

    boolean addOffer(OfferDto offerDto);

    OfferDto getOfferDetails(UUID id);
    boolean updateOffer(OfferDto offerDto);
    boolean deleteOffer(UUID id);
}
