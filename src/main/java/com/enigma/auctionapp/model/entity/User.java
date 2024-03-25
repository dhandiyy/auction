package com.enigma.auctionapp.model.entity;

import com.enigma.auctionapp.constant.DbPath;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = DbPath.DB_USER_CREDENTIAL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany (mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Role> role;
}
