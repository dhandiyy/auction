package com.enigma.auctionapp.model.response;

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
public class LoginResponse {
    private String email;
    private List<ERole> role;
    private String token;
}
