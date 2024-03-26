package com.enigma.auctionapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AdminRequest {
    private String name;
    private String mobilePhone;
    private String emailAddress;
}
