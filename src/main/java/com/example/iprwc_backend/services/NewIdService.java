package com.example.iprwc_backend.services;

import com.example.iprwc_backend.models.Customer;
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
}
