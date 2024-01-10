package com.example.iprwc_backend.daos;

import com.example.iprwc_backend.models.CartItem;
import com.example.iprwc_backend.models.Customer;
import com.example.iprwc_backend.services.NewIdService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class CartItemDao {
    private final CartItemRepository cartItemRepository;
    private final NewIdService newIdService;

    public CartItemDao(CartItemRepository cartItemRepository, NewIdService newIdService) {
        this.cartItemRepository = cartItemRepository;
        this.newIdService = newIdService;
    }

    public void savetoDatabase(CartItem cartItem) { this.cartItemRepository.save(cartItem); }

    public ArrayList<CartItem> getAllCartItems() {
        return (ArrayList<CartItem>) this.cartItemRepository.findAll();
    }

    public ArrayList<CartItem> getCartItemsBySpecificId(Long id) {
        ArrayList<CartItem> cartItems = (ArrayList<CartItem>) cartItemRepository.findAll();
        ArrayList<CartItem> cartItemsList = new ArrayList<>();

        for(CartItem cartItem : cartItems) {
            if(cartItem.getCart().getUser().getId() == id) {
                cartItemsList.add(cartItem);
            }
        }
        return cartItemsList;
    }

    public Optional<CartItem> getCartItemBySpecificId(Long id) {
        return this.cartItemRepository.findById(id);
    }

    public void deleteFromDatabase(long id) {
        this.cartItemRepository.deleteById(id);
    }

    public void updateQuantity(long id, long quantity) {
        this.cartItemRepository.findById(id).ifPresent(
                cartItem -> {
                    cartItem.setQuantity(quantity);
                    cartItemRepository.save(cartItem);
                }
        );
    }

    public Long giveNewCartItemId() {
        ArrayList<CartItem> cartItems = (ArrayList<CartItem>) this.cartItemRepository.findAll();
        return (Long) (long) this.newIdService.newCartItemId(cartItems);
    }
}
