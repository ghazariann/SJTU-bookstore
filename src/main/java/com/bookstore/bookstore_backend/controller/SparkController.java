package com.bookstore.bookstore_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookstore.bookstore_backend.service.SparkJobService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@RestController
@RequestMapping("/spark")
@CrossOrigin(origins = "http://localhost:3000")
public class SparkController {

    @Autowired
    private SparkJobService sparkJobService;

    @PostMapping("/submit")
    public ResponseEntity<String> submitSparkJob(@RequestBody Map<String, List<String>> keywordData) {
        try {
            List<String> keywords = keywordData.get("keywords");
            sparkJobService.triggerSparkJob(keywords);

            String output = Files.lines(Paths.get("spark_logs.txt"))
                    .collect(Collectors.joining("\n"));

            return ResponseEntity.ok(output);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error executing job: " + e.getMessage());
        }
    }
}