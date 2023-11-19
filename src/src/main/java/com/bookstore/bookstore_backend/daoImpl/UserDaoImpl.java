package com.bookstore.bookstore_backend.daoImpl;

import com.bookstore.bookstore_backend.entity.User;
import com.bookstore.bookstore_backend.repository.UserRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Repository;
import com.bookstore.bookstore_backend.dao.UserDao;

import java.util.List;

@Repository
@AllArgsConstructor
public class UserDaoImpl implements UserDao {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
}
