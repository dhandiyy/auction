package com.enigma.auctionapp.model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)public class AdminResponse {
    private String id;
    private String name;
    private String mobilePhone;
    private String emailAddress;
}
