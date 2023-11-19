package com.bookstore.bookstore_backend.serviceImpl;

import com.bookstore.bookstore_backend.entity.UserAuth;
import com.bookstore.bookstore_backend.repository.UserAuthRepository;
import com.bookstore.bookstore_backend.service.UserAuthService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    private final UserAuthRepository userAuthRepository;

    @Override
    public UserAuth addUserAuth(UserAuth userAuth) {
        return userAuthRepository.save(userAuth);
    }

    @Override
    public UserAuth getUserAuthById(long id) {
        Optional<UserAuth> optionalUserAuth = userAuthRepository.findById(id);
        return optionalUserAuth.get();
    }

    @Override
    public List<UserAuth> listUserAuths() {
        return userAuthRepository.findAll();
    }

    @Override
    public UserAuth updateUserAuth(UserAuth userAuth) {
        UserAuth existingUserAuth = userAuthRepository.findById(userAuth.getId()).get();

        existingUserAuth.setId(userAuth.getId() != 0 ? userAuth.getId() : existingUserAuth.getId());
        existingUserAuth.setEmail(userAuth.getEmail() != null ? userAuth.getEmail() : existingUserAuth.getEmail());
        existingUserAuth
                .setPassword(userAuth.getPassword() != null ? userAuth.getPassword() : existingUserAuth.getPassword());
        return userAuthRepository.save(existingUserAuth);
    }

    @Override
    public void deleteUserAuth(long id) {
        userAuthRepository.deleteById(id);
    }
}
