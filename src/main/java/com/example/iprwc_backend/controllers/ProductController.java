package com.example.iprwc_backend.controllers;

import com.example.iprwc_backend.daos.ProductDao;
import com.example.iprwc_backend.models.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
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

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse addProduct(@RequestBody Product product) {
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.productDao.saveToDatabase(product);
        return new ApiResponse(HttpStatus.ACCEPTED, "You added a product!");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ApiResponse removeProduct(@PathVariable Long id) {
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.productDao.deleteProductFromDatabase(id);
        return new ApiResponse(HttpStatus.ACCEPTED, "You removed a product!!");
    }

    @RequestMapping(value = "/id", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<ArrayList<Product>> getNewProductId() {
        int productId = this.productDao.giveNewProductId();
        return new ApiResponse(HttpStatus.ACCEPTED, productId);
    }
}
