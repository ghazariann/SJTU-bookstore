package com.bookstore.bookstore_backend.daoImpl;

import com.bookstore.bookstore_backend.entity.UserAuth;
import com.bookstore.bookstore_backend.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bookstore.bookstore_backend.dao.UserAuthDao;

import java.util.List;

@Repository
public class UserAuthDaoImpl implements UserAuthDao {

    private final UserAuthRepository userAuthRepository;

    @Autowired
    public UserAuthDaoImpl(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

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
