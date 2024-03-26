package com.enigma.auctionapp.service.impl;

import com.enigma.auctionapp.model.entity.UserRole;
import com.enigma.auctionapp.repository.UserRoleRepository;
import com.enigma.auctionapp.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    @Override
    public UserRole create(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }
}
