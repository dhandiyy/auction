package com.enigma.auctionapp.repository;

import com.enigma.auctionapp.model.entity.Offer;
import com.enigma.auctionapp.model.entity.Product;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Modifying
    @Query(value = """
    INSERT INTO m_product (id, name, condition, actual_price, description)
    VALUES (:#{#product.id},
            :#{#product.name},
            :#{#product.condition},
            :#{#product.actualPrice},
            :#{#product.description})
    """, nativeQuery = true)
    void create(Product product);

    @Transactional
    default void createAndFlush (Product product){
        create(product);
        flush();

    }

    @Modifying
    @Transactional
    @Query(value = """
    UPDATE m_product
    SET offer_id=:#{#product.offer.id}
    WHERE id=:#{#product.id}
    """, nativeQuery = true)
    void updateForSetOffer(Product product);

}
