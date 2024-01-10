package com.example.iprwc_backend.daos;

import com.example.iprwc_backend.models.Customer;
import com.example.iprwc_backend.services.NewIdService;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class UserDao {
    private final UserRepository userRepository;
    private final NewIdService newIdService;

    public UserDao(UserRepository userRepository, NewIdService newIdService) {
        this.userRepository = userRepository;
        this.newIdService = newIdService;
    }

    public void saveToDatabase(Customer customer) {
        this.userRepository.save(customer);
    }

    public ArrayList<Customer> getAllUsers() {
        return (ArrayList<Customer>) this.userRepository.findAll();
    }

    public Optional<Customer> getSpecificUser(Long id) {
        return this.userRepository.findById(id);
    }

    public void deleteUserFromDatabase(long id) {
        this.userRepository.deleteById(id);
    }

    public Optional<Customer> findByEmail(String email) {
        ArrayList<Customer> customers = (ArrayList<Customer>) userRepository.findAll();

        for (Customer customer : customers) {
            if (customer.getEmail().contains(email)) {
                return Optional.ofNullable(customer);
            }
        }
        return Optional.empty();
    }

    public int giveNewCustomerId() {
        ArrayList<Customer> customers = (ArrayList<Customer>) this.userRepository.findAll();
        return this.newIdService.newCustomerId(customers);
    }
}
