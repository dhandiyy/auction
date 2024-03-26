package com.enigma.auctionapp.service;

import com.enigma.auctionapp.model.entity.Customer;
import com.enigma.auctionapp.model.request.CustomerRequest;
import com.enigma.auctionapp.model.response.CustomerResponse;
import org.springframework.data.domain.Page;

public interface CustomerService {

    CustomerResponse createDto (CustomerRequest customerRequest);
    Customer create (Customer customer);
    CustomerResponse getByIdDto (String id);
    Customer getById (String id);
    Page<CustomerResponse> getAll (Integer page, Integer size);



}
