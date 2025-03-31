package com.example.demo2.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo2.service.YourModelService;

@RestController
@RequestMapping("/yourmodel")
public class YourModelController {
    private final YourModelService service;

    @Autowired
    public YourModelController(YourModelService service) {
        this.service = service;
    }

    @GetMapping("/getAllDataObjects")
    public List<Map<String, Object>> getAllDataObjects() {
        return service.getAllDataObjects();
    }
}
