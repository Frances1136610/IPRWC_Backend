package com.example.iprwc_backend.services;

import com.example.iprwc_backend.models.CartItem;
import com.example.iprwc_backend.models.Customer;
import com.example.iprwc_backend.models.Product;
import org.hibernate.annotations.Any;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class NewIdService {

    public int newCustomerId(ArrayList<Customer> customers) {
        for (int i = 0; i < customers.size() + 1; i++) {
            if (customers.size() == i) {
                return i + 1;
            }
        }
        return 1;
    }

    public int newCartItemId(ArrayList<CartItem> cartItems) {
        for (int i = 0; i < cartItems.size() + 1; i++) {
            if (cartItems.size() == i) {
                return i + 1;
            }
        }
        return 1;
    }

    public int newProductId(ArrayList<Product> products) {
        for (int i = 0; i < products.size() + 1; i++) {
            if (products.size() == i) {
                return i + 1;
            }
        }
        return 1;
    }
}
