package com.example.demo2.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "yourmodel")
public class YourModel {

    @Id
    private String id;
    private String name;
    private int age;

    // Constructors
    public YourModel() {}

    public YourModel(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
