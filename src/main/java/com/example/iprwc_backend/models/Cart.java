package com.example.iprwc_backend.models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "carts")
public class Cart {
    @Id
    private long id;

//    private User user;

    public Cart(long id, User user) {
        this.id = id;
//        this.user = user;
    }

    public long getId() {
        return id;
    }

//    @OneToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    public User getUser() {
//        return user;
//    }

    public void setId(long id) {
        this.id = id;
    }

//    public void setUser(User user) {
//        this.user = user;
//    }
}
