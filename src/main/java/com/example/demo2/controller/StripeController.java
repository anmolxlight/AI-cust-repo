package com.example.demo2.controller;

import com.example.demo2.config.StripeConfig;
import com.example.demo2.service.StripeService;
import com.stripe.exception.StripeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/stripe")
public class StripeController {
    private static final Logger logger = LoggerFactory.getLogger(StripeController.class);

    private final StripeConfig stripeConfig;
    private final StripeService stripeService;

    public StripeController(StripeConfig stripeConfig, StripeService stripeService) {
        this.stripeConfig = stripeConfig;
        this.stripeService = stripeService;
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Map<String, Object>> getCustomerDetails(@PathVariable String id) {
        logger.info("Getting customer details for: {}", id);
        Map<String, Object> customerDetails = stripeService.getCustomerDetails(id);
        return ResponseEntity.ok(customerDetails);
    }

    @PostMapping("/refund/{paymentId}")
    public ResponseEntity<Map<String, Object>> processRefund(@PathVariable String paymentId) {
        logger.info("Processing refund for payment: {}", paymentId);
        boolean success = stripeService.processRefund(paymentId);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        response.put("paymentId", paymentId);
        
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/subscriptions/{id}")
    public ResponseEntity<Map<String, Object>> cancelSubscription(@PathVariable String id) {
        logger.info("Canceling subscription: {}", id);
        boolean success = stripeService.cancelSubscription(id);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", success);
        response.put("subscriptionId", id);
        
        return ResponseEntity.ok(response);
    }

    @PostMapping("/checkout-session")
    public ResponseEntity<Map<String, Object>> createCheckoutSession(
            @RequestParam String customerId, 
            @RequestParam String priceId) {
        logger.info("Creating checkout session for customer: {}", customerId);
        String sessionUrl = stripeService.createCheckoutSession(customerId, priceId);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", sessionUrl != null);
        response.put("url", sessionUrl);
        
        return ResponseEntity.ok(response);
    }

    @PostMapping("/customer-portal")
    public ResponseEntity<Map<String, Object>> createCustomerPortal(@RequestParam String customerId) {
        logger.info("Creating customer portal for: {}", customerId);
        String portalUrl = stripeService.createCustomerPortalSession(customerId);
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", portalUrl != null);
        response.put("url", portalUrl);
        
        return ResponseEntity.ok(response);
    }
}
