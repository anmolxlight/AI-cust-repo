package com.example.demo2.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo2.model.YourModel;
import com.example.demo2.repository.YourModelRepository;

@Service
public class YourModelService {
    private final YourModelRepository repository;

    public YourModelService(YourModelRepository repository) {
        this.repository = repository;
    }

    // Fetch only the "data" field from all documents
    public List<Map<String, Object>> getAllDataObjects() {
        return repository.findAll().stream()
                .map(YourModel::getData) // Extract only 'data' field
                .collect(Collectors.toList());
    }

    public List<YourModel> getAllData() {
        return repository.findAll();  // Fetch all documents
    }
}
