package com.vallete.portfolio.backendjava.config;

import com.vallete.portfolio.backendjava.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class Jwt implements JwtInterface {

    @Value("${jwt.expiration-date}")
    private String expirationDateString;
    @Value("${jwt.signature-key}")
    private String signatureKey;

    @Override
    public String generateToken(User user) {
        int expirationDateInt = Integer.parseInt(expirationDateString);
        LocalDateTime expirationDateTime = LocalDateTime.now().plusMinutes(expirationDateInt);
        Instant instant = expirationDateTime.atZone(ZoneId.of("America/Sao_Paulo")).toInstant();
        Date expirationDateConverted = Date.from(instant);

        String expirationDateSimplified = expirationDateTime.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"));

        String token = Jwts.builder()
                .setExpiration(expirationDateConverted)
                .setSubject(user.getId().toString())
                .claim("name", user.getName())
                .claim("roles", "to be implemented")
                .claim("simplifiedExpirationTime", expirationDateSimplified)
                .signWith(SignatureAlgorithm.HS256, signatureKey)
                .compact();

        return token;
    }

    @Override
    public Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts.parser().setSigningKey(signatureKey).parseClaimsJws(token).getBody();
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            Claims claims = getClaims(token);
            Date expirationDateTimeClaim = claims.getExpiration();
            LocalDateTime expirationDateTime = expirationDateTimeClaim.toInstant().atZone(ZoneId.of("America/Sao_Paulo")).toLocalDateTime();

            return !LocalDateTime.now().atZone(ZoneId.of("America/Sao_Paulo")).toLocalDateTime().isAfter(expirationDateTime);
        } catch (ExpiredJwtException e) {
            return false;
        }
    }

    @Override
    public String getLoginByToken(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }
}
