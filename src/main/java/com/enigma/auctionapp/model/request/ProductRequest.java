package com.enigma.auctionapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProductRequest {
    private String id;
    private String name;
    private String description;
    private String condition;
    private Double actualPrice;

}
