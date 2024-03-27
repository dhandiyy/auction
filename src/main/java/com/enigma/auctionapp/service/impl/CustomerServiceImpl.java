package com.enigma.auctionapp.service.impl;

import com.enigma.auctionapp.model.entity.Customer;
import com.enigma.auctionapp.model.request.CustomerRequest;
import com.enigma.auctionapp.model.response.CustomerResponse;
import com.enigma.auctionapp.repository.CustomerRepository;
import com.enigma.auctionapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse createDto(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .emailAddress(customerRequest.getEmailAddress())
                .mobilePhone(customerRequest.getMobilePhone())
                .build();
        customerRepository.save(customer);

        return getBidderResponse(customer);
    }

    private static CustomerResponse getBidderResponse(Customer customer) {
        return CustomerResponse.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .mobilePhone(customer.getMobilePhone())
                .emailAddress(customer.getEmailAddress())
                .build();
    }

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public CustomerResponse getByIdDto(String id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        return getBidderResponse(customer);
    }

    @Override
    public Customer getById(String id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Customer not found with id: " + id));
    }

    @Override
    public Page<CustomerResponse> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> bidderPage = customerRepository.findAll(pageable);
        List<CustomerResponse> customerResponseList = new ArrayList<>();

        for (Customer customer : bidderPage){
            customerResponseList.add(
                    CustomerResponse.builder()
                            .id(customer.getId())
                            .firstName(customer.getFirstName())
                            .lastName(customer.getLastName())
                            .emailAddress(customer.getEmailAddress())
                            .mobilePhone(customer.getMobilePhone())
                            .build());
        }
        return new PageImpl<>(customerResponseList, pageable, bidderPage.getTotalElements());
    }
}
