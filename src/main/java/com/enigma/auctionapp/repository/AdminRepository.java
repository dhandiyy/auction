package com.enigma.auctionapp.repository;

import com.enigma.auctionapp.model.entity.Admin;
import com.enigma.auctionapp.model.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

    @Modifying
    @Transactional
    @Query(value = """
    INSERT INTO m_admin (id, email, name, phone)
    VALUES(
        :#{#admin.id},
        :#{#admin.emailAddress},
        :#{#admin.name},
        :#{#admin.mobilePhone})
    """, nativeQuery = true)
    void create (Admin admin);


    @Query(value = """
    SELECT * FROM m_admin
    """,nativeQuery = true)
    Page<Admin> getAllNative (Pageable pageable);


    @Query(value = """
    SELECT * FROM m_admin WHERE id =:id
    """, nativeQuery = true)
    Optional<Admin> getByIdNative (String id);


    @Modifying
    @Query(value = """
    UPDATE m_admin
    SET name=:#{#admin.name},
        email=:#{#admin.emailAddress},
        phone=:#{#admin.mobilePhone}
    WHERE id=:#{#admin.id}
    """, nativeQuery = true)
    void update (Admin admin);



}
