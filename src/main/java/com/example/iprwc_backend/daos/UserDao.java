package com.example.iprwc_backend.daos;

import com.example.iprwc_backend.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class UserDao {
    private final UserRepository userRepository;

    public UserDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveToDatabase(User user) {
        this.userRepository.save(user);
    }

    public ArrayList<User> getAllUsers() {
        return (ArrayList<User>) this.userRepository.findAll();
    }

    public Optional<User> getSpecificUser(Long id) {
        return this.userRepository.findById(id);
    }

    public void deleteUserFromDatabase(long id) {
        this.userRepository.deleteById(id);
    }
}
