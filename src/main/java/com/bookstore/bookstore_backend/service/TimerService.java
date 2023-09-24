package com.bookstore.bookstore_backend.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TimerService {

    private long startTime;

    public void startTimer() {
        this.startTime = System.currentTimeMillis();
        System.out.println("Logged in: " + this.startTime);
    }

    public long stopTimer() {
        long elapsedTime = (System.currentTimeMillis() - this.startTime) / 1000; // Convert to seconds
        System.out.println("Online time: " + elapsedTime);
        return elapsedTime;

    }
}