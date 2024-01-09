package com.example.iprwc_backend.controllers;

import com.example.iprwc_backend.daos.CartItemDao;
import com.example.iprwc_backend.daos.ProductDao;
import com.example.iprwc_backend.models.ApiResponse;
import com.example.iprwc_backend.models.CartItem;
import com.example.iprwc_backend.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api/v1/cartitems")
public class CartItemController {
    private final CartItemDao cartItemDao;

    public CartItemController(CartItemDao cartItemDao) {
        this.cartItemDao = cartItemDao;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<ArrayList<CartItem>> getAllCartItems() {
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ArrayList<CartItem> cartItems = this.cartItemDao.getAllCartItems();

        if (cartItems.isEmpty()) {
            return new ApiResponse(HttpStatus.NOT_FOUND, "no cartitems in database");
        }
        return new ApiResponse(HttpStatus.ACCEPTED, cartItems);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<ArrayList<CartItem>> getAllCartItems(@PathVariable Long id) {
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ArrayList<CartItem> cartItems = this.cartItemDao.getCartItemsBySpecificId(id);

        if (cartItems.isEmpty()) {
            return new ApiResponse(HttpStatus.NOT_FOUND, "no cartitems in database");
        }
        return new ApiResponse(HttpStatus.ACCEPTED, cartItems);
    }
}
