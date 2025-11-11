package com.sillas.sillas.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sillas.sillas.entities.User;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenConfig {

    /*
    Aqui criaremos o token e selando ele informações e algoritmos para dar mais segurança
    a aplicação. Neste caso usaremos uma palavra secreta, porém o ideal é sempre usar
    pares de chaves(publica e privada)
     */

    private final String secret = "secret";

    Algorithm algorithm = Algorithm.HMAC256(secret);

    public String generateToken(User user){
        return JWT.create()
                .withClaim("user_id", user.getUser_id())
                .withSubject(user.getUsername())
                .withExpiresAt(Instant.now().plusSeconds(86600))
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

    public Optional<JWTUserData> validadeToken(String token) {

        try {
            DecodedJWT decode = JWT.require(this.algorithm).build().verify(token);

            return Optional.of(JWTUserData.builder()
                    .userid(decode.getClaim("user_id").asLong())
                    .username(decode.getSubject()).build());
        }catch (JWTVerificationException e) {
            return Optional.empty();
        }
    }

}
