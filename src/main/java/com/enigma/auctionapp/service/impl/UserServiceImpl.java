package com.enigma.auctionapp.service.impl;

import com.enigma.auctionapp.model.entity.AppUser;
import com.enigma.auctionapp.model.entity.User;
import com.enigma.auctionapp.model.response.UserResponse;
import com.enigma.auctionapp.repository.UserRepository;
import com.enigma.auctionapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public AppUser loadUserByUserId(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Invalid Credential"));
        return getAppUser(user);
    }

    @Override
    public UserResponse getById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Invalid Credential"));

        return UserResponse.builder()
                .role(user.getUserRoles().stream().map(userRole -> userRole.getRole().getRole()).toList())
                .email(user.getEmail())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Invalid Credential"));
        return getAppUser(user);
    }

    private AppUser getAppUser(User user) {
        return AppUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getUserRoles().stream().map(userRole -> userRole.getRole().getRole()).toList())
                .build();
    }
}