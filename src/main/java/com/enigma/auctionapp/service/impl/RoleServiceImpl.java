package com.enigma.auctionapp.service.impl;

import com.enigma.auctionapp.model.entity.Role;
import com.enigma.auctionapp.repository.RoleRepository;
import com.enigma.auctionapp.service.RoleService;
import com.enigma.auctionapp.util.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getOrSave(ERole role) {
        Optional<Role> optionalRole = roleRepository.findByRole(role);
        if(!optionalRole.isEmpty()){
            return optionalRole.get();
        }

        Role roleCurrent = Role.builder()
                .role(role)
                .build();
        return roleRepository.saveAndFlush(roleCurrent);
    }
}
