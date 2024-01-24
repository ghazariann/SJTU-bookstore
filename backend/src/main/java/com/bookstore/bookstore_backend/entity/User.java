package com.bookstore.bookstore_backend.entity;

import jakarta.persistence.*;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column()
    private String address;

    @Column()
    private String telephone;

    @Column
    private int type;

    // toString
    @Override
    public String toString() {
        return "{id=" + id + "}";
    }

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    // @JoinColumn(name = "auth_id", referencedColumnName = "id")
    private UserAuth userAuth;

    // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // private List<Order> orders;

    // @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch =
    // FetchType.LAZY)
    // private List<CartItem> cartItems;
}
