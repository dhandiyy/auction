package com.enigma.auctionapp.service.impl;

import com.enigma.auctionapp.model.entity.*;
import com.enigma.auctionapp.model.request.AuthRequest;
import com.enigma.auctionapp.model.response.LoginResponse;
import com.enigma.auctionapp.model.response.RegisterResponse;
import com.enigma.auctionapp.repository.UserRepository;
import com.enigma.auctionapp.security.JwtUtil;
import com.enigma.auctionapp.service.AuthService;
import com.enigma.auctionapp.service.CustomerService;
import com.enigma.auctionapp.service.RoleService;
import com.enigma.auctionapp.service.UserRoleService;
import com.enigma.auctionapp.util.ERole;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final CustomerService customerService;
    private final UserRoleService userRoleService;

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public RegisterResponse register(AuthRequest authRequest) {
        try{
            List<Role> roleList = new ArrayList<>();
            List<UserRole>userRoleList = new ArrayList<>();
            System.out.println(authRequest.getRoles());
            System.out.println(authRequest.toString());
            System.out.println(authRequest.getEmail());

            for(ERole eRole : authRequest.getRoles()){

                Role currentRole =roleService.getOrSave(eRole);
                roleList.add(currentRole);

                UserRole userRole = UserRole.builder()
                        .role(currentRole)
                        .build();
                userRoleList.add(userRole);
            }
            User user = User.builder()
                    .password(passwordEncoder.encode(authRequest.getPassword()))
                    .email(authRequest.getEmail())
                    .userRoles(userRoleList)
                    .build();
            userRepository.saveAndFlush(user);

            for(UserRole userRole: userRoleList){
                userRole.setUser(user);
                userRoleService.create(userRole);
            }

            Customer customer = Customer.builder()
                    .firstName(authRequest.getFirstName())
                    .lastName(authRequest.getLastName())
                    .mobilePhone(authRequest.getPhone())
                    .emailAddress(authRequest.getEmail())
                    .user(user)
                    .build();
            customerService.create(customer);

            return RegisterResponse.builder()
                    .email(customer.getEmailAddress())
                    .password(user.getPassword())
                    .role(user.getUserRoles().stream().map(userRole -> userRole.getRole().getRole()).toList())
                    .build();
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User Already Exist");
        }
    }

    @Override
    public LoginResponse login(AuthRequest authRequest) {
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail().toLowerCase(),
                        authRequest.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser appUser = (AppUser) authentication.getPrincipal();
        String token = jwtUtil.generateToken(appUser);

        return LoginResponse.builder()
                .email(appUser.getEmail())
                .token(token)
                .role(appUser.getRoles())
                .build();
    }
}
