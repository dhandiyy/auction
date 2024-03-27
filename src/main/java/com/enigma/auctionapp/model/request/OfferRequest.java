package com.enigma.auctionapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OfferRequest {
    private String productName;
    private String description;
    private String condition;
    private Double actualPrice;
    private Double openBid;
    private Double multiple;
    private String closeDate;
    private String idCustomer;

}
