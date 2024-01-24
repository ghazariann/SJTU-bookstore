package com.bookstore.bookstore_backend.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

@Service
public class SparkJobService {

    public void triggerSparkJob(List<String> keywords) throws IOException {
        // Create a list to hold the command and its arguments
        List<String> command = new ArrayList<>();
        command.add("cmd");
        command.add("/c");
        command.add("spark-submit");
        command.add("--class");
        command.add("com.example.KeywordCounter");
        command.add("--master");
        command.add("local[4]");
        command.add("./../spark-app/target/spark-app-1.0-SNAPSHOT.jar");

        // Add each keyword as a separate argument
        command.addAll(keywords);
        command.add("> spark_logs.txt");
        // Create ProcessBuilder with the command list
        ProcessBuilder builder = new ProcessBuilder(command);

        builder.redirectErrorStream(true);

        // // Execute the process and capture output
        Process process = builder.start();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Log output for debugging
            }
        }
    }
}