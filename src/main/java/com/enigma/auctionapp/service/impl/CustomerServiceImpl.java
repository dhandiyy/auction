package com.enigma.auctionapp.service.impl;

import com.enigma.auctionapp.model.entity.Customer;
import com.enigma.auctionapp.model.request.CustomerRequest;
import com.enigma.auctionapp.model.response.CustomerResponse;
import com.enigma.auctionapp.repository.CustomerRepository;
import com.enigma.auctionapp.service.CustomerService;
import jakarta.transaction.Transactional;
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

        return getCustomerResponse(customer);
    }

    private static CustomerResponse getCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .mobilePhone(customer.getMobilePhone())
                .emailAddress(customer.getEmailAddress())
                .build();
    }

    @Override
    public Customer create(Customer customer) {
        customerRepository.create(customer);
        return customer;
    }

    @Override
    public CustomerResponse getByIdDto(String id) {
        Customer customer = this.getByIdEntity(id);
        return getCustomerResponse(customer);
    }

    @Override
    public Customer getByIdEntity(String id) {
        Customer customer = customerRepository.getByIdNative(id).orElseThrow(
                () -> new NoSuchElementException("Customer not found with id: " + id));
        return customer;
    }

    @Override
    public Page<CustomerResponse> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> bidderPage = customerRepository.getAllNative(pageable);
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

    @Transactional
    @Override
    public CustomerResponse update(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .id(customerRequest.getId())
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .emailAddress(customerRequest.getEmailAddress())
                .mobilePhone(customerRequest.getMobilePhone())
                .build();
        customerRepository.update(customer);

        return getCustomerResponse(customer);
    }
}
