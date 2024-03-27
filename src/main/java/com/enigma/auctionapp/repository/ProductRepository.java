package com.enigma.auctionapp.repository;

import com.enigma.auctionapp.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
