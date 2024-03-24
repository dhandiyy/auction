package com.enigma.auctionapp.model.entity;

import com.enigma.auctionapp.constant.DbPath;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = DbPath.DB_PRODUCT)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "condition", nullable = false)
    private String condition;

    @Column(name = "actual_price", nullable = false)
    private Double actualPrice;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
