package com.enigma.auctionapp.service;

import com.enigma.auctionapp.model.entity.Offer;
import com.enigma.auctionapp.model.entity.Product;
import com.enigma.auctionapp.model.request.ProductRequest;
import com.enigma.auctionapp.model.response.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {

    ProductResponse update(ProductRequest productRequest);
    void create(Product product);
    Page<ProductResponse> getAll (Integer size, Integer page);
    Product getById (String id);
    void updateForSetOffer(Product product);
}
