package com.bookstore.bookstore_backend.dao;

import com.bookstore.bookstore_backend.entity.User;
import java.util.List;

public interface UserDao {
    User save(User book);
    User findById(long id);
    List<User> findAll();
    void deleteById(long id);
}