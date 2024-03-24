package com.enigma.auctionapp.model.entity;

import com.enigma.auctionapp.constant.DbPath;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = DbPath.DB_ADMIN)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "phone", nullable = false, length = 50)
    private String mobilePhone;

    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String emailAddress;

    @OneToOne
    @JoinColumn(name = "user_credential_id")
    private UserCredential userCredential;
}
