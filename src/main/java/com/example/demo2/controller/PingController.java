package com.example.demo2.controller;

import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    private final MongoTemplate mongoTemplate;

    public PingController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping("/ping")
    public ResponseEntity<String> checkDbConnection() {
        try {
            // Try to ping MongoDB
            mongoTemplate.getDb().runCommand(new BsonDocument("ping", new BsonInt64(1)));
            return ResponseEntity.ok("MongoDB Connection Successful");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body("MongoDB Connection Failed: " + e.getMessage());
        }
    }
} 