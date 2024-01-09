package com.example.iprwc_backend.services;

import com.example.iprwc_backend.models.Customer;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class UniqueIdService {

    public boolean checkIfUserIdIsUnique(ArrayList<Customer> customers, long id) {
        if(customers.isEmpty()) {
            return true;
        }
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
