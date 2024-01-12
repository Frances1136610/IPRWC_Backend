package com.example.iprwc_backend.controllers;

import com.example.iprwc_backend.daos.UserDao;
import com.example.iprwc_backend.models.ApiResponse;
import com.example.iprwc_backend.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin(origins = "http://164.92.145.160", maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {
    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<ArrayList<Customer>> getEmployeeById(@PathVariable Long id) {
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<Customer> employee = this.userDao.getSpecificUser(id);
        if (employee.isEmpty()) {
            return new ApiResponse(HttpStatus.NOT_FOUND, "no employee with that id");
        }
        return new ApiResponse(HttpStatus.ACCEPTED, employee);
    }
}
