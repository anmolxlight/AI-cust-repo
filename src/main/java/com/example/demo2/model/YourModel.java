package com.example.demo2.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "call_conversations")
public class YourModel {
    @Id
    private String id;
    private String type;
    private long event_timestamp;
    private Map<String, Object> data; // Correctly mapping the 'data' object
    private String timestamp;

    public YourModel() {}

    public YourModel(String id, String type, long event_timestamp, Map<String, Object> data, String timestamp) {
        this.id = id;
        this.type = type;
        this.event_timestamp = event_timestamp;
        this.data = data;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getEvent_timestamp() {
        return event_timestamp;
    }

    public void setEvent_timestamp(long event_timestamp) {
        this.event_timestamp = event_timestamp;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
