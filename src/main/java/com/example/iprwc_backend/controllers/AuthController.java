package com.example.iprwc_backend.controllers;

import com.example.iprwc_backend.Security.JwtUtil;
import com.example.iprwc_backend.daos.CartDao;
import com.example.iprwc_backend.daos.UserDao;
import com.example.iprwc_backend.models.ApiResponse;
import com.example.iprwc_backend.models.Cart;
import com.example.iprwc_backend.models.LoginCredentials;
import com.example.iprwc_backend.models.Customer;
import com.example.iprwc_backend.services.InvalidEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "http://164.92.145.160", maxAge = 4800, allowCredentials = "false")
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
    private final UserDao customerDao;
    private final CartDao cartDao;

    public AuthController(InvalidEmailService invalidEmailService, UserDao customerDao, CartDao cartDao) {
        this.invalidEmailService = invalidEmailService;
        this.customerDao = customerDao;
        this.cartDao = cartDao;
    }

    @PostMapping("/register")
    public Object registerHandler(@RequestBody Customer customer) {
        try {
            if (invalidEmailService.patternMatches(customer.getEmail())) {
                String encodedPass = passwordEncoder.encode(customer.getPassword());
                customer.setPassword(encodedPass);
                userDao.saveToDatabase(customer);
                cartDao.saveToDatabase(new Cart(customer));
                return new ApiResponse(HttpStatus.ACCEPTED, jwtUtil.generateToken(customer.getEmail()));
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
                return new ApiResponse(HttpStatus.ACCEPTED, jwtUtil.generateToken(loginCredentials.getEmail()));
            } else {
                return new ApiResponse(HttpStatus.BAD_REQUEST, "Invalid email");
            }
        } catch (AuthenticationException authExc) {
            return new ApiResponse(HttpStatus.UNAUTHORIZED, "Invalid email/password");
        }
    }

    @RequestMapping(value = "/id", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<ArrayList<Customer>> getNewCustomerId() {
        int customerId = this.customerDao.giveNewCustomerId();
        return new ApiResponse(HttpStatus.ACCEPTED, customerId);
    }

    @GetMapping("/info")
    public ApiResponse getCustomerDetails() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(customerDao.findByEmail(email).get().getEmail() + customerDao.findByEmail(email).get().getRole());
        return new ApiResponse(HttpStatus.ACCEPTED, customerDao.findByEmail(email).get());
    }
}
