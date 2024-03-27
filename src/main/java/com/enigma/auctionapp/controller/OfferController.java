package com.enigma.auctionapp.controller;

import com.enigma.auctionapp.constant.AppPath;
import com.enigma.auctionapp.model.request.OfferRequest;
import com.enigma.auctionapp.model.response.CommonResponse;
import com.enigma.auctionapp.model.response.OfferResponse;
import com.enigma.auctionapp.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = AppPath.OFFER)
public class OfferController {
    private final OfferService offerService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_BIDDER', 'ROLE_OWNER')")
    public ResponseEntity<?> create(@RequestBody OfferRequest offerRequest){
        OfferResponse offerResponse = offerService.create(offerRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully Created New Offer and Product")
                        .data(offerResponse)
                        .build());
    }

    @PutMapping(AppPath.GET_BY_ID)
    @PreAuthorize("hasAnyRole('ROLE_BIDDER', 'ROLE_OWNER')")
    public ResponseEntity<?> updateCloseOffer(@PathVariable String id){
        OfferResponse offerResponse = offerService.updateCloseOffer(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Close Order")
                        .data(offerResponse)
                        .build());
    }

    @GetMapping(AppPath.GET_BY_ID)
    @PreAuthorize("hasAnyRole('ROLE_BIDDER', 'ROLE_OWNER')")
    public ResponseEntity<?> getById(@PathVariable String id){
        OfferResponse offerResponse = offerService.getById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully Get Offer")
                        .data(offerResponse)
                        .build());

    }
}
