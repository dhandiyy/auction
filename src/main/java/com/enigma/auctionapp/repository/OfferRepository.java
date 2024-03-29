package com.enigma.auctionapp.repository;

import com.enigma.auctionapp.model.entity.Offer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface OfferRepository extends JpaRepository<Offer, String> {

    @Modifying
    @Query(value = """
    INSERT INTO t_offer (id, multiple, open_bid, close_date, open_date, customer_id, product_id, status_transaction)
    VALUES (:#{#offer.id},
            :#{#offer.multiple},
            :#{#offer.openBid},
            :#{#offer.closeDate},
            :#{#offer.openDate},
            :#{#offer.customer.id},
            :#{#offer.product.id},
            :#{#offer.statusOffer.name()})
    """, nativeQuery = true)
    void create (Offer offer);

    @Transactional
    default void createAndFlush (Offer offer) {
        create(offer);
        flush();
    }


}
