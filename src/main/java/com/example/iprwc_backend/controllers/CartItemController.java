package com.example.iprwc_backend.controllers;

import com.example.iprwc_backend.daos.CartDao;
import com.example.iprwc_backend.daos.CartItemDao;
import com.example.iprwc_backend.models.ApiResponse;
import com.example.iprwc_backend.models.Cart;
import com.example.iprwc_backend.models.CartItem;
import com.example.iprwc_backend.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping(path = "/api/v1/cartitems")
public class CartItemController {
    private final CartItemDao cartItemDao;
    private final CartDao cartDao;

    public CartItemController(CartItemDao cartItemDao, CartDao cartDao) {
        this.cartItemDao = cartItemDao;
        this.cartDao = cartDao;
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
    public ApiResponse<ArrayList<CartItem>> getAllCartItemsFromCustomer(@PathVariable Long id) {
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ArrayList<CartItem> cartItems = this.cartItemDao.getCartItemsBySpecificId(id);

        if (cartItems.isEmpty()) {
            return new ApiResponse(HttpStatus.NOT_FOUND, "no cartitems in database");
        }
        return new ApiResponse(HttpStatus.ACCEPTED, cartItems);
    }

    @RequestMapping(value = "/{id}/insert", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse addCartItem(@RequestBody Product product, @PathVariable Long id) {
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Cart cart = this.cartDao.getCart(id);
        CartItem cartItem = new CartItem(this.cartItemDao.giveNewCartItemId(), cart, product, 1L);
        this.cartItemDao.savetoDatabase(cartItem);
        return new ApiResponse(HttpStatus.ACCEPTED, "You added an item to your cart!");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ApiResponse removeCartItem(@PathVariable Long id) {
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.cartItemDao.deleteFromDatabase(id);
        return new ApiResponse(HttpStatus.ACCEPTED, "You removed an item from your cart!");
    }

    @RequestMapping(value = "/change-quantity", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse updateQuantity(@RequestBody CartItem cartItem) {
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.cartItemDao.updateQuantity(cartItem.getId(), cartItem.getQuantity());
        return new ApiResponse(HttpStatus.ACCEPTED, "You updated the quantity of an item from your cart!");
    }
}
