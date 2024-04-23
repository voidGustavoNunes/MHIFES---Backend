package com.rfid.mhifes.config;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.rfid.mhifes.model.Users;


@Service
public class TokenService {


    @Value("${api.security.token.secret}")
    private String secret;

    
    public String generateToken(Users user) throws IllegalArgumentException, UnsupportedEncodingException{
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            Date expirationDate = Date.from(genExpirationDate());

            String token = JWT.create()
                    .withIssuer("api/auth")
                    .withSubject(user.getLogin())
                    .withExpiresAt(expirationDate)
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }


    public String validateToken(String token) throws IllegalArgumentException, UnsupportedEncodingException{
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("api/auth")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "";
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
