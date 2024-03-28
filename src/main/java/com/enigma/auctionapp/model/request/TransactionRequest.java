package com.enigma.auctionapp.model.request;

import com.enigma.auctionapp.model.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TransactionRequest {
    private Double offerBid;
    private String idCustomer;
    private String idOffer;

}
