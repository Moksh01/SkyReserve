package com.moksh.skyreserve.controllers;

import com.moksh.skyreserve.dto.LoginRequestDTO;
import com.moksh.skyreserve.dto.RegisterRequestDTO;
import com.moksh.skyreserve.entity.User;
import com.moksh.skyreserve.services.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserAuthService userAuthService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDTO request){
        User user=new User();
        user.setUser_first_name(request.getUser_first_name());
        user.setUser_last_name(request.getUser_last_name());
        user.setUserEmail(request.getUserEmail());
        user.setUser_contact_no(request.getUser_contact_no());
        user.setUser_password(passwordEncoder.encode(request.getUser_password()));
        user.setRole(request.getRole());
        userAuthService.save(user);
        return ResponseEntity.ok("User registered successfully");
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO request){
        return ResponseEntity.ok("User logged in and token generated successfully");
    }
}
