package com.enigma.auctionapp.controller;

import com.enigma.auctionapp.constant.AppPath;
import com.enigma.auctionapp.model.request.AuthRequest;
import com.enigma.auctionapp.model.response.CommonResponse;
import com.enigma.auctionapp.model.response.LoginResponse;
import com.enigma.auctionapp.model.response.RegisterResponse;
import com.enigma.auctionapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = AppPath.AUTH)
public class AuthController {
    private final AuthService authService;
    @PostMapping(value = "/signup")
    private ResponseEntity<?> register (@RequestBody AuthRequest authRequest){
        RegisterResponse registerResponse = authService.register(authRequest);

        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .message("Successfully register new customer")
                .statusCode(HttpStatus.CREATED.value())
                .data(registerResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping(value = "/signin")
    private ResponseEntity<?> login (@RequestBody AuthRequest authRequest){
        LoginResponse loginResponse = authService.login(authRequest);

        CommonResponse<LoginResponse> commonResponse = CommonResponse.<LoginResponse>builder()
                .message("Success login")
                .statusCode(HttpStatus.OK.value())
                .data(loginResponse)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

}
