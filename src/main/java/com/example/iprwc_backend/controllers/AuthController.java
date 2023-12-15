package com.example.iprwc_backend.controllers;

import com.example.iprwc_backend.Security.JwtUtil;
import com.example.iprwc_backend.daos.UserDao;
import com.example.iprwc_backend.models.ApiResponse;
import com.example.iprwc_backend.models.User;
import com.example.iprwc_backend.services.InvalidEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
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

    private final InvalidEmailService invalidEmailService;
    public AuthController(InvalidEmailService invalidEmailService) {
        this.invalidEmailService = invalidEmailService;
    }


//    @PostMapping("/register")
//    public Object registerHandler(@RequestBody User user) {
//        try {
//            if(invalidEmailService.patternMatches(user.getEmail())) {
//                String encodedPass = passwordEncoder.encode(user.getPassword());
//                user.setPassword(encodedPass);
//                userDao.saveToDatabase(user);
//                return jwtUtil.generateToken(user.getEmail());
//            } else {
//                return new ApiResponse(HttpStatus.BAD_REQUEST, "Invalid email");
//            }
//        } catch (Exception e) {
//            return new ApiResponse(HttpStatus.BAD_REQUEST, "Email already in use");
//        }
//    }

}
