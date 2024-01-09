package com.example.iprwc_backend.daos;

import com.example.iprwc_backend.models.Customer;
import com.example.iprwc_backend.services.UniqueIdService;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class UserDao {
    private final UserRepository userRepository;
    private final UniqueIdService uniqueIdService;

    public UserDao(UserRepository userRepository, UniqueIdService uniqueIdService) {
        this.userRepository = userRepository;
        this.uniqueIdService = uniqueIdService;
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

    public boolean isUserIdUnique(long id) {
        ArrayList<Customer> customers =
                (ArrayList<Customer>) this.userRepository.findAll();
        return this.uniqueIdService.
                checkIfUserIdIsUnique(customers, id);
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
}
