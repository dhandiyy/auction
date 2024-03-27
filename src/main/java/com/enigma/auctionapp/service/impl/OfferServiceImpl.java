package com.enigma.auctionapp.service.impl;

import com.enigma.auctionapp.model.request.OfferRequest;
import com.enigma.auctionapp.model.response.OfferResponse;
import com.enigma.auctionapp.repository.OfferRepository;
import com.enigma.auctionapp.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Override
    public OfferResponse create(OfferRequest offerRequest) {
        return null;
    }

    @Override
    public OfferResponse getById(String id) {
        return null;
    }

    @Override
    public OfferResponse updateCloseOffer(String id) {
        return null;
    }
}
