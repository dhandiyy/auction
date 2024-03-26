package com.enigma.auctionapp.model.request;

import com.enigma.auctionapp.util.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AuthRequest {
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private List<ERole> roles;
}
