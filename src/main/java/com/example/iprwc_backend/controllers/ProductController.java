package com.example.iprwc_backend.controllers;

import com.example.iprwc_backend.daos.ProductDao;
import com.example.iprwc_backend.models.ApiResponse;
import com.example.iprwc_backend.models.Product;
import com.example.iprwc_backend.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/product")
public class ProductController {
    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<ArrayList<Product>> getAllProducts() {
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ArrayList<Product> products = this.productDao.getAllProducts();

        if (products.isEmpty()) {
            return new ApiResponse(HttpStatus.NOT_FOUND, "no products in database");
        }
        return new ApiResponse(HttpStatus.ACCEPTED, products);
    }
}
