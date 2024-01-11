package com.example.iprwc_backend.services;

import com.example.iprwc_backend.daos.UserDao;
import com.example.iprwc_backend.models.Customer;
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
        Optional<Customer> userOptional = userDao.findByEmail(email);
        if(userOptional.isEmpty())
            throw new UsernameNotFoundException("Could not findUser with email = " + email);
        Customer customer = userOptional.get();
        if (customer.getRole().equals("ADMIN")){
            return new org.springframework.security.core.userdetails.User(
                    email,
                    customer.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        } else {
            return new org.springframework.security.core.userdetails.User(
                    email,
                    customer.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        }
    }


}