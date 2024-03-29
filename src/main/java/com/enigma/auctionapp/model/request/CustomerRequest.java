package com.enigma.auctionapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CustomerRequest {
    private String id;
    private String firstName;
    private String lastName;
    private String mobilePhone;
    private String emailAddress;
}
