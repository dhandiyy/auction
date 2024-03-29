package com.enigma.auctionapp.controller;

import com.enigma.auctionapp.constant.AppPath;
import com.enigma.auctionapp.model.request.TransactionRequest;
import com.enigma.auctionapp.model.response.CommonResponse;
import com.enigma.auctionapp.model.response.TransactionResponse;
import com.enigma.auctionapp.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = AppPath.TRANSACTION)
public class TransactionController {

    private final TransactionService transactionService;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody TransactionRequest transactionRequest){
        TransactionResponse transactionResponse = transactionService.create(transactionRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully Created New Transaction")
                        .data(transactionResponse)
                        .build());

    }
}
