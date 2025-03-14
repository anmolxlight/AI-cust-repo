package com.example.demo2.config;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class StripeConfig {

    private static final Logger logger = LoggerFactory.getLogger(StripeConfig.class);

    @Value("${stripe.secret.key:NOT_FOUND}")
    private String secretKey;

    public String getSecretKey() {
        return secretKey;
    }

    @PostConstruct
    public void init() {
        try {
            if (secretKey == null || secretKey.equals("NOT_FOUND")) {
                logger.error("Stripe Secret Key Not Found!");
            } else {
                Stripe.apiKey = secretKey;
                logger.info("Stripe Secret Key Loaded Successfully.");
            }
        } catch (Exception e) {
            logger.error("Error initializing Stripe: ", e);
        }
    }
}
