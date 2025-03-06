package com.codecool.server.security;

import com.codecool.server.model.UserEntity;
import com.codecool.server.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthService authService;

    @Autowired
    public UserDetailsServiceImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user;
        try {
            user = authService.getUserEntityFuture().get();
        } catch (ExecutionException | InterruptedException  e) {
            throw new UsernameNotFoundException("Failed to fetch user details for email: " + email, e);
        }

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();


        return new User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }


}
