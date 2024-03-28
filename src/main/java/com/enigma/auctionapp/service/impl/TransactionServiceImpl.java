package com.enigma.auctionapp.service.impl;

import com.enigma.auctionapp.model.entity.Customer;
import com.enigma.auctionapp.model.entity.Offer;
import com.enigma.auctionapp.model.entity.Transaction;
import com.enigma.auctionapp.model.request.TransactionRequest;
import com.enigma.auctionapp.model.response.CustomerResponse;
import com.enigma.auctionapp.model.response.TransactionResponse;
import com.enigma.auctionapp.repository.TransactionRepository;
import com.enigma.auctionapp.service.CustomerService;
import com.enigma.auctionapp.service.OfferService;
import com.enigma.auctionapp.service.TransactionService;
import com.enigma.auctionapp.util.ApprovalStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final CustomerService customerService;
    private final OfferService offerService;

    @Transactional
    @Override
    public TransactionResponse create(TransactionRequest transactionRequest) {
        Customer customer = customerService.getByIdEntity(transactionRequest.getIdCustomer());
        Offer offer = offerService.getByIdEntity(transactionRequest.getIdOffer());

        CustomerResponse customerResponse = CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .mobilePhone(customer.getMobilePhone())
                .emailAddress(customer.getEmailAddress())
                .build();

        Transaction transaction = Transaction.builder()
                .createdAt(LocalDateTime.now())
                .offerBid(transactionRequest.getOfferBid())
                .customer(customer)
                .offer(offer)
                .approvalStatus(ApprovalStatus.APPROVED)
                .build();
        transactionRepository.save(transaction);

        if(!offer.getTransactions().isEmpty()){
            offer.getTransactions().sort(Comparator.comparing(Transaction::getCreatedAt));
            Transaction lastTransaction = offer.getTransactions().get(offer.getTransactions().size()-1);


            Boolean check = forOfferCheck(lastTransaction.getOfferBid(), transactionRequest.getOfferBid(), offer.getMultiple(), offer.getMultiple());
            if(!check){
                transaction.setApprovalStatus(ApprovalStatus.REJECTED);
            }
        }


        List<Transaction> transactionList = offer.getTransactions();
        transactionList.add(transaction);
        offer.setTransactions(transactionList);

        return TransactionResponse.builder()
                .id(transaction.getId())
                .createdAt(transaction.getCreatedAt())
                .offerBid(transaction.getOfferBid())
                .customerResponse(customerResponse)
                .approvalStatus(transaction.getApprovalStatus())
                .build();
    }

    static Boolean forOfferCheck (Double lastOfferBid, Double currentOfferBid, Double multiple, Double openBid){
        if((currentOfferBid - openBid) % multiple!=0){
            return false;
        } else if (currentOfferBid < openBid) {
            return false;
        } else if (lastOfferBid > currentOfferBid) {
            return false;
        }else {
            return true;
        }
    }
}