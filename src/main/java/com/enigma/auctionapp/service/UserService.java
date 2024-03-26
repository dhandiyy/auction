package com.enigma.auctionapp.service;

import com.enigma.auctionapp.model.entity.AppUser;
import com.enigma.auctionapp.model.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser loadUserByUserId (String id);

    UserResponse getById (String id);

}
