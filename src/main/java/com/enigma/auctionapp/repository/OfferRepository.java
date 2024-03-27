package com.enigma.auctionapp.repository;

import com.enigma.auctionapp.model.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, String> {
}
