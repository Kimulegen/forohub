package com.kimulegen.forohub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.kimulegen.forohub.domain.user.User;
import com.kimulegen.forohub.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Autowired
    private UserRepository userRepository;
    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(user.getPassword());
            return JWT.create()
                    .withIssuer("api")
                    .withSubject(user.getUsername())
                    .withClaim("id",user.getId())
                    .withExpiresAt(expirationdate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException();
        }
    }
    public String getSubject(String token){
        if (token == null) {
            throw new IllegalArgumentException("El token es nulo.");
        }
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            String username = decodedJWT.getSubject();
            if (username == null) {
                throw new IllegalArgumentException("Token no válido: Asunto no encontrado");
            }
            User user = (User) userRepository.findByEmail(username);
            if (user == null) {
                throw new IllegalArgumentException("Usuario no encontrado por nombre de usuario: " + username);
            }

            Algorithm algorithm = Algorithm.HMAC256(user.getPassword());
            DecodedJWT verifier = JWT.require(algorithm)
                    .withIssuer("api")
                    .build()
                    .verify(token);

            return verifier.getSubject();
        } catch (JWTVerificationException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Token no válido: " + e.getMessage(), e);
        }
    }
    private Instant expirationdate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
