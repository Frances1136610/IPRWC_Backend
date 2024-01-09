package com.example.iprwc_backend.models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "carts")
public class Cart {
    @Id
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Customer user;

    public Cart(Customer customer) {
        this.user = customer;
    }

    public long getId() {
        return id;
    }

    public Customer getUser() {
        return user;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser(Customer customer) {
        this.user = customer;
    }
}
