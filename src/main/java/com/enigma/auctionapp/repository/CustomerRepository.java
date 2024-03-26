package com.enigma.auctionapp.repository;

import com.enigma.auctionapp.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
