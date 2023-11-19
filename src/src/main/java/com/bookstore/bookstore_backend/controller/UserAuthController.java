package com.bookstore.bookstore_backend.controller;

import com.bookstore.bookstore_backend.entity.UserAuth;
import com.bookstore.bookstore_backend.service.UserAuthService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/userAuth")
@CrossOrigin(origins = "http://localhost:3000")
public class UserAuthController {

    private UserAuthService userAuthService;

    @PostMapping
    public ResponseEntity<UserAuth> createUserAuth(@RequestBody UserAuth userAuth) {
        UserAuth newUserAuth = userAuthService.addUserAuth(userAuth);
        return new ResponseEntity<>(newUserAuth, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAuth> getUserAuthById(@PathVariable("id") long id) {
        UserAuth userAuth = userAuthService.getUserAuthById(id);
        return new ResponseEntity<>(userAuth, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserAuth>> getAllUserAuths() {
        List<UserAuth> userAuths = userAuthService.listUserAuths();
        return new ResponseEntity<>(userAuths, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserAuth> updateUserAuth(@PathVariable("id") long id, @RequestBody UserAuth userAuth) {
        userAuth.setId(id);
        UserAuth updatedUserAuth = userAuthService.updateUserAuth(userAuth);
        return new ResponseEntity<>(updatedUserAuth, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserAuth(@PathVariable("id") long id) {
        userAuthService.deleteUserAuth(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}