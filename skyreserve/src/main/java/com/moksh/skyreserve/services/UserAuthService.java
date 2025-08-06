package com.moksh.skyreserve.services;

import com.moksh.skyreserve.entity.User;
import com.moksh.skyreserve.repositories.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    public UserAuthRepository userAuthRepository;

    public UserDetails save(User user){
        return userAuthRepository.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String user_email) throws UsernameNotFoundException {
        return userAuthRepository.findByUserEmail(user_email).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
}
