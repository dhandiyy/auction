package com.enigma.auctionapp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.enigma.auctionapp.model.entity.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${app.auction.jwt.jwt-secret}")
    private String jwtSecret;
    @Value("${app.auction.jwt.app-name}")
    private String appName;
    @Value("${app.auction.jwt.jwt-expired.time}")
    private long expiredTime;

    public String generateToken (AppUser appUser){
        try{
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));

            String token = JWT.create()
                    .withIssuer(appName)
                    .withSubject(appUser.getId())
                    .withExpiresAt(Instant.now().plusSeconds(expiredTime))
                    .withIssuedAt(Instant.now())
                    .withClaim("role", appUser.getRoles().stream().map(Enum::toString).toList())
                    .sign(algorithm);
            return token;

        }catch (JWTCreationException e){
            throw new RuntimeException();
        }
    }

    public boolean verifyJwtToken (String token){

        try{
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return decodedJWT.getIssuer().equals(appName);
        }catch (JWTCreationException e){
            throw new RuntimeException();
        }
    }

    public Map<String, String> getUserInfoByToken (String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);

            Map<String,String> userInfo = new HashMap<>();
            userInfo.put("userId", decodedJWT.getSubject());
            userInfo.put("role", decodedJWT.getClaim("role").asString());

            return userInfo;

        }catch (JWTVerificationException e){
            throw new RuntimeException();
        }
    }
}

