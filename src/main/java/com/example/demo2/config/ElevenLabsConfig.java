package com.example.demo2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class ElevenLabsConfig {

    private static final Logger logger = LoggerFactory.getLogger(ElevenLabsConfig.class);

    @Value("${elevenlabs.api.key:NOT_FOUND}")
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }
} 