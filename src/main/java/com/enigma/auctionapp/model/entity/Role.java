package com.enigma.auctionapp.model.entity;

import com.enigma.auctionapp.constant.DbPath;
import com.enigma.auctionapp.util.ERole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = DbPath.DB_ROLE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private ERole role;

}
