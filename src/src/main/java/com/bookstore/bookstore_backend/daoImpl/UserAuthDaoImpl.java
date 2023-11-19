package com.bookstore.bookstore_backend.daoImpl;

import com.bookstore.bookstore_backend.entity.UserAuth;
import com.bookstore.bookstore_backend.repository.UserAuthRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Repository;
import com.bookstore.bookstore_backend.dao.UserAuthDao;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserAuthDaoImpl implements UserAuthDao {

    private final UserAuthRepository userAuthRepository;

    @Override
    public UserAuth save(UserAuth userAuth) {
        return userAuthRepository.save(userAuth);
    }

    @Override
    public UserAuth findById(long id) {
        return userAuthRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserAuth> findAll() {
        return userAuthRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        userAuthRepository.deleteById(id);
    }
}
