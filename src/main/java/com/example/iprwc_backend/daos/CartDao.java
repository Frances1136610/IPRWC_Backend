package com.example.iprwc_backend.daos;

import com.example.iprwc_backend.models.Cart;
import com.example.iprwc_backend.models.Customer;
import com.example.iprwc_backend.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class CartDao {
    private final CartRepository cartRepository;

    public CartDao(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    public void saveToDatabase(Cart cart) {
        this.cartRepository.save(cart);
    }

    public void deleteCartFromDatabase(long id) {
        this.cartRepository.deleteById(id);
    }
}
