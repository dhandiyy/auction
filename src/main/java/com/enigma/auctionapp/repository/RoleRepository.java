package com.enigma.auctionapp.repository;

import com.enigma.auctionapp.model.entity.Role;
import com.enigma.auctionapp.util.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {

//    @Query(
//            value = "SELECT r FROM m_role r WHERE r.role = :#{#eRole.role.}",
//            nativeQuery = true
//    )
    Optional<Role> findByRole(ERole eRole);

}
