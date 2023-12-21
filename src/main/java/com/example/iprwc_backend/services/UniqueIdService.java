package com.example.iprwc_backend.services;

import com.example.iprwc_backend.models.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class UniqueIdService {

    public boolean checkIfUserIdIsUnique(ArrayList<User> users, long id) {
        if(users.isEmpty()) {
            return true;
        }
        for (User user : users) {
            if (user.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
