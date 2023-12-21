package com.example.iprwc_backend.daos;

import com.example.iprwc_backend.models.User;
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

    public boolean isUserIdUnique(long id) {
        ArrayList<User> users =
                (ArrayList<User>) this.userRepository.findAll();
        return this.uniqueIdService.
                checkIfUserIdIsUnique(users, id);
    }
}
