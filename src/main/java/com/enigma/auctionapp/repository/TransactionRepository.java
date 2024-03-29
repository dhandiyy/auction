package com.enigma.auctionapp.repository;

import com.enigma.auctionapp.model.entity.Transaction;
import com.enigma.auctionapp.util.ApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

    @Modifying
    @Query(value = """
    INSERT INTO t_transaction (id, created_at, offer_bid, customer_id, offer_id, approval_status)
    VALUES (:#{#transaction.id},
            :#{#transaction.createdAt},
            :#{#transaction.offerBid},
            :#{#transaction.customer.id},
            :#{#transaction.offer.id},
            :#{#transaction.approvalStatus.name()})
    """, nativeQuery = true)
    void create(Transaction transaction);

    @Query(value = """
    SELECT *
    FROM t_transaction t
    WHERE t.approval_status = :approvalStatus
    AND t.offer_id = :offerId
    ORDER BY t.created_at DESC
    LIMIT 1
    """, nativeQuery = true)
    Optional<Transaction> findLastByApprovalStatusAndOfferIdNative(String approvalStatus, String offerId);




}
