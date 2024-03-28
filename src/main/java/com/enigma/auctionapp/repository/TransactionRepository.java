package com.enigma.auctionapp.repository;

import com.enigma.auctionapp.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
