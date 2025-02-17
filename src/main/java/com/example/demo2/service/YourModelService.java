package com.example.demo2.service;

import com.example.demo2.model.YourModel;
import com.example.demo2.repository.YourModelRepository;
import org.springframework.stereotype.Service;

@Service("yourModelServiceBean") // Specify a unique name for the service bean
public class YourModelService {

    private final YourModelRepository repository;

    public YourModelService(YourModelRepository repository) {
        this.repository = repository;
    }

    public YourModel saveModel(YourModel model) {
        return repository.save(model);
    }
}
