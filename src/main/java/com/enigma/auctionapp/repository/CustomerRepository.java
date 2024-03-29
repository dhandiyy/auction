package com.enigma.auctionapp.repository;

import com.enigma.auctionapp.model.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Modifying
    @Query(value = """
    INSERT INTO m_customer (id, email, phone, first_name, last_name, user_credential_id)
    VALUES (:#{#customer.id},
            :#{#customer.emailAddress},
            :#{#customer.mobilePhone},
            :#{#customer.firstName},
            :#{#customer.lastName},
            :#{#customer.user.id})
    """, nativeQuery = true)
    void create (Customer customer);

    @Query(value = """
    SELECT * FROM m_customer WHERE id = :id
    """,nativeQuery = true)
    Optional<Customer> getByIdNative (String id);

    @Query(value = """
    SELECT * FROM m_customer
    """,nativeQuery = true)
    Page<Customer> getAllNative (Pageable pageable);


    @Modifying
    @Query(value = """
    UPDATE m_customer
    SET first_name=:#{#customer.firstName},
        last_name=:#{#customer.lastName},
        phone=:#{#customer.mobilePhone},
        email=:#{#customer.emailAddress}
    WHERE id=:#{#customer.id}
    """, nativeQuery = true)
    void update (Customer customer);



}
