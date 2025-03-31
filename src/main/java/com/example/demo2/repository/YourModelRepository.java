package com.example.demo2.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo2.model.YourModel;

@Repository
public interface YourModelRepository extends MongoRepository<YourModel, String> {
    
    @Override
    List<YourModel> findAll();  // Fetches all documents
}
