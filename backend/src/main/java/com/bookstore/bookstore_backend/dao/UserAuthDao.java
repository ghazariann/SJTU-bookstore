package com.bookstore.bookstore_backend.dao;

import com.bookstore.bookstore_backend.entity.UserAuth;
import java.util.List;

public interface UserAuthDao {
    UserAuth save(UserAuth book);
    UserAuth findById(long id);
    List<UserAuth> findAll();
    void deleteById(long id);
}