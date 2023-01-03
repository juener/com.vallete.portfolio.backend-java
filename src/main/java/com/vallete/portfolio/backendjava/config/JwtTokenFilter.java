package com.vallete.portfolio.backendjava.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtInterface jwtInterface;
    private final SecurityUserDetailsService securityUserDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String authorization = request.getHeader("Authorization");

            if (!authorization.startsWith("Bearer "))
                throw new Exception("Tokens must initializer with Bearer pattern.");

            String token = authorization.replace("Bearer ", "");

            if (!jwtInterface.isTokenValid(token))
                throw new Exception("The token is invalid.");

            UserDetails userDetails = securityUserDetailsService.loadUserByUsername(jwtInterface.getLoginByToken(token));
            UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(user);
        }catch (Exception e){

        }finally {
            filterChain.doFilter(request, response);
        }
    }
}
