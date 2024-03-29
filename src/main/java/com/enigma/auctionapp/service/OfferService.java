package com.enigma.auctionapp.service;

import com.enigma.auctionapp.model.entity.Offer;
import com.enigma.auctionapp.model.request.OfferRequest;
import com.enigma.auctionapp.model.response.OfferResponse;

public interface OfferService {

    OfferResponse create(OfferRequest offerRequest);
    OfferResponse getById(String id);
    OfferResponse updateCloseOffer(String id);
    Offer getByIdEntity(String id);


}
