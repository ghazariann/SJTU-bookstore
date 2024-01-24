package com.bookstore.bookstore_backend.serviceImpl;

import com.bookstore.bookstore_backend.entity.User;
import com.bookstore.bookstore_backend.entity.UserAuth;
import com.bookstore.bookstore_backend.repository.UserRepository;
import com.bookstore.bookstore_backend.service.UserService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        UserAuth userAuth = user.getUserAuth();
        if (userAuth == null) {
            throw new IllegalArgumentException("UserAuth cannot be null when creating a User");
        }
        userAuth.setUser(user);
        user.setUserAuth(userAuth);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.get();
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).get();
        UserAuth userAuth = user.getUserAuth();
        UserAuth existingUserAuth = existingUser.getUserAuth();
        if (userAuth != null) {
            existingUserAuth.setEmail(userAuth.getEmail() != null ? userAuth.getEmail() : existingUserAuth.getEmail());
            existingUserAuth.setPassword(
                    userAuth.getPassword() != null ? userAuth.getPassword() : existingUserAuth.getPassword());
            existingUserAuth.setDisabled(
                    userAuth.getDisabled() != null ? userAuth.getDisabled() : existingUserAuth.getDisabled());
        }
        existingUser.setId(user.getId() != 0 ? user.getId() : existingUser.getId());
        existingUser.setName(user.getName() != null ? user.getName() : existingUser.getName());
        existingUser.setAddress(user.getAddress() != null ? user.getAddress() : existingUser.getAddress());
        existingUser.setTelephone(user.getTelephone() != null ? user.getTelephone() : existingUser.getTelephone());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
