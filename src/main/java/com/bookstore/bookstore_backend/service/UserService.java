package com.bookstore.bookstore_backend.service;

import com.bookstore.bookstore_backend.entity.User;

import java.util.List;

public interface UserService {
    User addUser(User order);// POST

    User getUserById(long id); // GET

    List<User> listUsers(); // GET

    User updateUser(User order); // PUT

    void deleteUser(long id); // DELETE
}
