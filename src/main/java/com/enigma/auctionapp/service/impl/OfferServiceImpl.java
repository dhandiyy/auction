package com.enigma.auctionapp.service.impl;

import com.enigma.auctionapp.model.entity.Customer;
import com.enigma.auctionapp.model.entity.Offer;
import com.enigma.auctionapp.model.entity.Product;
import com.enigma.auctionapp.model.entity.Transaction;
import com.enigma.auctionapp.model.request.OfferRequest;
import com.enigma.auctionapp.model.response.CustomerResponse;
import com.enigma.auctionapp.model.response.OfferResponse;
import com.enigma.auctionapp.model.response.TransactionResponse;
import com.enigma.auctionapp.repository.OfferRepository;
import com.enigma.auctionapp.service.CustomerService;
import com.enigma.auctionapp.service.OfferService;
import com.enigma.auctionapp.service.ProductService;
import com.enigma.auctionapp.util.StatusOffer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ProductService productService;
    private final CustomerService customerService;

    @Transactional
    @Override
    public OfferResponse create(OfferRequest offerRequest) {

        Customer customer = customerService.getByIdEntity(offerRequest.getIdCustomer());

        Product product = Product.builder()
                .id(UUID.randomUUID().toString())
                .name(offerRequest.getProductName())
                .condition(offerRequest.getCondition())
                .actualPrice(offerRequest.getActualPrice())
                .description(offerRequest.getDescription())
                .build();
        productService.create(product);

        Offer offer = Offer.builder()
                .id(UUID.randomUUID().toString())
                .openBid(offerRequest.getOpenBid())
                .multiple(offerRequest.getMultiple())
                .openDate(LocalDateTime.now())
                .closeDate(LocalDateTime.parse(offerRequest.getCloseDate()))
                .product(product)
                .statusOffer(StatusOffer.OPEN)
                .customer(customer)
                .build();
        offerRepository.createAndFlush(offer);

        product.setOffer(offer);
        System.out.println(product.getOffer().getId());
        productService.updateForSetOffer(product);



        return OfferResponse.builder()
                .id(offer.getId())
                .productName(product.getName())
                .description(product.getDescription())
                .condition(product.getCondition())
                .openBid(offer.getOpenBid())
                .statusOffer(offer.getStatusOffer())
                .build();
    }

    @Override
    public OfferResponse getById(String id) {
        Offer offer =offerRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Offer not found with id: " + id));

        List<TransactionResponse> transactionResponseList = new ArrayList<>();

        for(Transaction transaction : offer.getTransactions()){
            CustomerResponse customerResponse = CustomerResponse.builder()
                    .firstName(transaction.getCustomer().getFirstName())
                    .lastName(transaction.getCustomer().getLastName())
                    .mobilePhone(transaction.getCustomer().getMobilePhone())
                    .emailAddress(transaction.getCustomer().getEmailAddress())
                    .build();

            transactionResponseList.add(
                    TransactionResponse.builder()
                            .id(transaction.getId())
                            .createdAt(transaction.getCreatedAt())
                            .offerBid(transaction.getOfferBid())
                            .customerResponse(customerResponse)
                            .approvalStatus(transaction.getApprovalStatus())
                            .build());
        }
        return OfferResponse.builder()
                .id(offer.getId())
                .productName(offer.getProduct().getName())
                .description(offer.getProduct().getDescription())
                .condition(offer.getProduct().getCondition())
                .openBid(offer.getOpenBid())
                .statusOffer(offer.getStatusOffer())
                .transactionResponseList(transactionResponseList)
                .build();

    }

    @Override
    public OfferResponse updateCloseOffer(String id) {
        Offer offer =offerRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Offer not found with id: " + id));
        offer.setStatusOffer(StatusOffer.CLOSE);
        offerRepository.saveAndFlush(offer);

        return OfferResponse.builder()
                .id(offer.getId())
                .productName(offer.getProduct().getName())
                .description(offer.getProduct().getDescription())
                .condition(offer.getProduct().getCondition())
                .openBid(offer.getOpenBid())
                .statusOffer(offer.getStatusOffer())
                .transactionResponseList(null)
                .build();
    }

    @Override
    public Offer getByIdEntity(String id) {
        return offerRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Offer not found with id: " + id));

    }
}
