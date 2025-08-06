package com.moksh.skyreserve.filters;

import com.moksh.skyreserve.security.JWTAuthenticationToken;
import com.moksh.skyreserve.security.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTRefreshFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    public JWTRefreshFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(!request.getServletPath().equals("/refresh-tokens")){
            filterChain.doFilter(request,response);
            return;
        }
        String refreshToken=extractRefrshFromRequest(request);
        if(refreshToken==null){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return ;
        }
        JWTAuthenticationToken authenticationToken=new JWTAuthenticationToken(refreshToken);
        Authentication authResult=authenticationManager.authenticate(authenticationToken);
        if(authResult.isAuthenticated()){
            String newToken=jwtUtil.generateToken(authResult.getName(),15);
            response.setHeader("Authorization","Bearer "+ newToken);
        }
    }
    private String extractRefrshFromRequest(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        if(cookies==null) return null;
        String refreshToken=null;
        for(Cookie cookie:cookies){
            if("refreshToken".equals(cookie.getName())){
                refreshToken=cookie.getValue();
            }
        }
        return refreshToken;
    }
}
