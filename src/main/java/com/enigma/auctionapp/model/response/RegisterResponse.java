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
public class RegisterResponse {
    private String email;
    private String password;
    private List<ERole> role;
}
