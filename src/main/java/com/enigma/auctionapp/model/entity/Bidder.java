package com.enigma.auctionapp.model.entity;

import com.enigma.auctionapp.constant.DbPath;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = DbPath.DB_BIDDER)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Bidder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "phone", nullable = false, length = 50)
    private String mobilePhone;

    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String emailAddress;

    @OneToOne
    @JoinColumn(name = "user_credential_id")
    private UserCredential userCredential;

    @OneToMany(mappedBy = "bidder")
    private List<Transaction> transactions;

}
