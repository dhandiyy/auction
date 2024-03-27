package com.enigma.auctionapp.model.response;

import com.enigma.auctionapp.model.entity.Customer;
import com.enigma.auctionapp.util.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TransactionResponse {
    private String id;
    private LocalDateTime createdAt;
    private Double offerBid;
    private CustomerResponse customerResponse;
    private ApprovalStatus approvalStatus;





}
