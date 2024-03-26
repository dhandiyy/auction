package com.enigma.auctionapp.repository;

import com.enigma.auctionapp.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, String> {

}
