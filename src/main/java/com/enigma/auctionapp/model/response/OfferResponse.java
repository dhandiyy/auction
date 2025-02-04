package com.enigma.auctionapp.model.response;

import com.enigma.auctionapp.model.entity.Transaction;
import com.enigma.auctionapp.util.StatusOffer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OfferResponse {
    private String id;
    private String productName;
    private String description;
    private String condition;
    private Double openBid;
    private StatusOffer statusOffer;
    private List<TransactionResponse> transactionResponseList;

}
