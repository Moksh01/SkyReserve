package com.moksh.skyreserve.filters;

import com.moksh.skyreserve.security.JWTAuthenticationToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTValidationFilter extends OncePerRequestFilter {
    private final AuthenticationManager authenticationManager;

    public JWTValidationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getServletPath();
        if (path.equals("/register") || path.equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token=extractToken(request);
        if(token!=null){
            JWTAuthenticationToken jwtAuthenticationToken=new JWTAuthenticationToken(token);
            Authentication authResult=authenticationManager.authenticate(jwtAuthenticationToken);
            if(authResult.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(authResult);
            }
        }
        filterChain.doFilter(request, response);
    }
    private String extractToken(HttpServletRequest request){
        String bearer=request.getHeader("Authorization");
        if(bearer!=null&& bearer.startsWith("Bearer ")){
            return bearer.substring(7);
        }
        return null;
    }
}
