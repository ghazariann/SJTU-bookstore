package com.bookstore.bookstore_backend.controller;

import com.bookstore.bookstore_backend.entity.User;
import com.bookstore.bookstore_backend.entity.UserAuth;
import com.bookstore.bookstore_backend.service.TimerService;
import com.bookstore.bookstore_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import jakarta.servlet.http.HttpSession;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {
    private UserService userService;
    private TimerService timerService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserAuth credentials, HttpSession session) {
        String email = credentials.getEmail();
        String password = credentials.getPassword();

        List<User> allUsers = userService.listUsers();
        for (User user : allUsers) {
            UserAuth userAuth = user.getUserAuth();
            if (userAuth.getEmail().equals(email) && userAuth.getPassword().equals(password)) {
                if (userAuth.getDisabled()) {
                    return new ResponseEntity<>(Collections.singletonMap("message", "Account is disabled"),
                            HttpStatus.FORBIDDEN);
                }
                System.out.println("Session ID: " + session.getId());
                timerService.startTimer();
                return new ResponseEntity<>(user, HttpStatus.OK); // Return the user object
            }
        }
        return new ResponseEntity<>(Collections.singletonMap("message", "Invalid email or password"),
                HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout(HttpSession session) {
        System.out.println("Session ID: " + session.getId());
        long elapsedTime = timerService.stopTimer();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("message", "Logged out successfully");
        responseMap.put("elapsedTime", elapsedTime);
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }
}
