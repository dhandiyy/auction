package com.enigma.auctionapp.service;

import com.enigma.auctionapp.model.request.TransactionRequest;
import com.enigma.auctionapp.model.response.TransactionResponse;

public interface TransactionService {

    TransactionResponse create(TransactionRequest transactionRequest);

}
