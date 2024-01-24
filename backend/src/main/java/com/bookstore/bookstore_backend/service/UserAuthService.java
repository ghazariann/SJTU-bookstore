package com.bookstore.bookstore_backend.service;

import com.bookstore.bookstore_backend.entity.UserAuth;

import java.util.List;

public interface UserAuthService {
    UserAuth addUserAuth(UserAuth order);// POST

    UserAuth getUserAuthById(long id); // GET

    List<UserAuth> listUserAuths(); // GET

    UserAuth updateUserAuth(UserAuth order); // PUT

    void deleteUserAuth(long id); // DELETE
}
