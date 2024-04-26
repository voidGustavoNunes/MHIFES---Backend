package com.rfid.mhifes.config;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.rfid.mhifes.model.Usuario;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private static final String ISSUER = "auth-api";

    public String generateToken(Usuario user) throws IllegalArgumentException, UnsupportedEncodingException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            Date expirationDate = Date.from(genExpirationDate());

            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getLogin())
                    .withExpiresAt(expirationDate)
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }

    public String validateToken(String token) throws IllegalArgumentException, UnsupportedEncodingException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    // EU ADICIONEI
    public String getUserNameFromToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer(ISSUER)
                    .build();
            DecodedJWT decodedJwt = verifier.verify(token);

            return decodedJwt.getSubject();
        } catch (JWTVerificationException e) {
            return null;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error: Unsupported encoding for secret", e);
        }
    }

    public boolean isValidToken(String token, UserDetails userDetails) {
        String loginUser = getUserNameFromToken(token);
        return (loginUser.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer(ISSUER)
                    .build();
            DecodedJWT decodedJwt = verifier.verify(token);

            Date expirationDate = decodedJwt.getExpiresAt();

            return expirationDate.before(new Date());
        } catch (JWTVerificationException e) {
            return true;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error: Unsupported encoding for secret", e);
        }
    }
}
