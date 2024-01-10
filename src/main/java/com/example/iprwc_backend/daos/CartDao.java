package com.example.iprwc_backend.daos;

import com.example.iprwc_backend.models.Cart;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class CartDao {
    private final CartRepository cartRepository;

    public CartDao(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    public Cart getCart(Long id) {
        ArrayList<Cart> carts = (ArrayList<Cart>) cartRepository.findAll();

        for (Cart cart : carts) {
            if (cart.getUser().getId() == id) {
                return cart;
            }
        }
        return null;
    }

    public void saveToDatabase(Cart cart) {
        this.cartRepository.save(cart);
    }

    public void deleteCartFromDatabase(long id) {
        this.cartRepository.deleteById(id);
    }
}
