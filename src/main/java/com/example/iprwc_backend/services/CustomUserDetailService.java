package com.example.iprwc_backend.services;

import com.example.iprwc_backend.daos.UserDao;
import com.example.iprwc_backend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class CustomUserDetailService implements UserDetailsService {
    @Autowired private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userDao.findByEmail(email);
        if(userOptional.isEmpty())
            throw new UsernameNotFoundException("Could not findUser with email = " + email);
        User user = userOptional.get();
        return new org.springframework.security.core.userdetails.User(
                email,
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}