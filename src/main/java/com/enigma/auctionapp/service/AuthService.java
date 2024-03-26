package com.enigma.auctionapp.service;

import com.enigma.auctionapp.model.request.AuthRequest;
import com.enigma.auctionapp.model.response.LoginResponse;
import com.enigma.auctionapp.model.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(AuthRequest authRequest);
    LoginResponse login(AuthRequest authRequest);
}
