package com.vallete.portfolio.backendjava.config;

import com.vallete.portfolio.backendjava.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

public interface JwtInterface {

    String generateToken(User user);
    Claims getClaims(String token) throws ExpiredJwtException;
    boolean isTokenValid(String token);
    String getLoginByToken(String token);

}
