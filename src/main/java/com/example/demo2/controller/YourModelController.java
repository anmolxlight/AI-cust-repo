package com.example.demo2.controller;

import com.example.demo2.model.YourModel;
import com.example.demo2.service.YourModelService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/yourmodel")
public class YourModelController {

    private final YourModelService service;

    public YourModelController(@Qualifier("yourModelServiceBean") YourModelService service) {
        this.service = service;
    }

    // POST request to create a new YourModel object
    @PostMapping("/create")
    public YourModel createYourModel(@RequestBody YourModel model) {
        return service.saveModel(model);
    }
}
