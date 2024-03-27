package com.enigma.auctionapp.model.response;

import com.enigma.auctionapp.model.entity.Offer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProductResponse {
    private String id;
    private String name;
    private String condition;
    private Double actualPrice;
    private String description;

}
