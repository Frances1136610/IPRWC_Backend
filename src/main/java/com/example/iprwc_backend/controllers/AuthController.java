package com.example.iprwc_backend.controllers;

import com.example.iprwc_backend.Security.JwtUtil;
import com.example.iprwc_backend.daos.UserDao;
import com.example.iprwc_backend.models.ApiResponse;
import com.example.iprwc_backend.models.LoginCredentials;
import com.example.iprwc_backend.models.Customer;
import com.example.iprwc_backend.services.InvalidEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authManager;
    private final InvalidEmailService invalidEmailService;

    public AuthController(InvalidEmailService invalidEmailService) {
        this.invalidEmailService = invalidEmailService;
    }

    @PostMapping("/register")
    public Object registerHandler(@RequestBody Customer customer) {
        System.out.println("CHECK");
        try {
            System.out.println("CHECK1");
            System.out.println(customer);
            if (invalidEmailService.patternMatches(customer.getEmail())) {
                System.out.println("CHECK2");
                String encodedPass = passwordEncoder.encode(customer.getPassword());
                customer.setPassword(encodedPass);
                System.out.println("ID: " + customer.getId());
                userDao.saveToDatabase(customer);
                return jwtUtil.generateToken(customer.getEmail());
            } else {
                return new ApiResponse(HttpStatus.BAD_REQUEST, "Invalid email");
            }
        } catch (Exception e) {
            return new ApiResponse(HttpStatus.BAD_REQUEST, "Email already in use");
        }
    }

    @PostMapping("/login")
    public Object loginHandler(@RequestBody LoginCredentials loginCredentials) {
        try {
            if(invalidEmailService.patternMatches(loginCredentials.getEmail())) {
                UsernamePasswordAuthenticationToken authInputToken =
                        new UsernamePasswordAuthenticationToken(loginCredentials.getEmail(), loginCredentials.getPassword());
                authManager.authenticate(authInputToken);
                return jwtUtil.generateToken(loginCredentials.getEmail());
            } else {
                return new ApiResponse(HttpStatus.BAD_REQUEST, "Invalid email");
            }
        } catch (AuthenticationException authExc) {
            return new ApiResponse(HttpStatus.UNAUTHORIZED, "Invalid email/password");
        }
    }
}
