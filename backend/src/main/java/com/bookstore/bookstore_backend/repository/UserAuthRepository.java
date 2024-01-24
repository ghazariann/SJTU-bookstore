package com.bookstore.bookstore_backend.repository;
import com.bookstore.bookstore_backend.entity.UserAuth;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
}
