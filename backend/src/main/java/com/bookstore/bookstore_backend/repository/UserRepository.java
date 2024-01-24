package com.bookstore.bookstore_backend.repository;
import com.bookstore.bookstore_backend.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
//    User findByUsername(String username);
}
