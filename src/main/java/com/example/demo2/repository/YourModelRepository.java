package com.example.demo2.repository;

import com.example.demo2.model.YourModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YourModelRepository extends MongoRepository<YourModel, String> {
}
