package com.enigma.auctionapp.model.entity;

import com.enigma.auctionapp.constant.DbPath;
import com.enigma.auctionapp.util.StatusTransaction;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = DbPath.DB_OFFER)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "open_bid", nullable = false)
    private Double openBid;

    @Column(name = "multiple", nullable = false)
    private Double multiple;

    @Column(name = "open_date", nullable = false)
    private LocalDateTime openDate;

    @Column(name = "close_date", nullable = false)
    private LocalDateTime closeDate;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_transaction", nullable = false)
    private StatusTransaction statusTransaction;

    @OneToMany(mappedBy = "offer")
    private List<Transaction> transactions;
}
