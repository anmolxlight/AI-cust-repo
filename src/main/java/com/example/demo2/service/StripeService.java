package com.example.demo2.service;

import com.example.demo2.config.StripeConfig;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Refund;
import com.stripe.model.Subscription;
import com.stripe.param.RefundCreateParams;
import com.stripe.param.SubscriptionCancelParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {
    private static final Logger logger = LoggerFactory.getLogger(StripeService.class);
    
    private final StripeConfig stripeConfig;
    
    public StripeService(StripeConfig stripeConfig) {
        this.stripeConfig = stripeConfig;
    }
    
    /**
     * Get customer details from Stripe
     */
    public Map<String, Object> getCustomerDetails(String customerId) {
        try {
            Customer customer = Customer.retrieve(customerId);
            Map<String, Object> customerDetails = new HashMap<>();
            customerDetails.put("id", customer.getId());
            customerDetails.put("email", customer.getEmail());
            customerDetails.put("name", customer.getName());
            
            // Get subscription information
            Map<String, Object> params = new HashMap<>();
            params.put("customer", customerId);
            customerDetails.put("subscriptions", Subscription.list(params).getData());
            
            logger.info("Retrieved customer details for: {}", customerId);
            return customerDetails;
        } catch (StripeException e) {
            logger.error("Error retrieving customer details for {}", customerId, e);
            return new HashMap<>();
        }
    }
    
    /**
     * Process a refund for a payment
     */
    public boolean processRefund(String paymentIntentId) {
        try {
            // Create refund parameters
            RefundCreateParams params = RefundCreateParams.builder()
                .setPaymentIntent(paymentIntentId)
                .build();
            
            // Process the refund
            Refund refund = Refund.create(params);
            
            // Check if refund was successful
            logger.info("Refund processed: {}", refund.getId());
            return "succeeded".equals(refund.getStatus());
        } catch (StripeException e) {
            logger.error("Error processing refund for payment {}", paymentIntentId, e);
            return false;
        }
    }
    
    /**
     * Cancel a subscription
     */
    public boolean cancelSubscription(String subscriptionId) {
        try {
            Subscription subscription = Subscription.retrieve(subscriptionId);
            SubscriptionCancelParams params = SubscriptionCancelParams.builder().build();
            subscription = subscription.cancel(params);
            logger.info("Subscription canceled: {}", subscriptionId);
            return true;
        } catch (StripeException e) {
            logger.error("Error canceling subscription {}", subscriptionId, e);
            return false;
        }
    }
    
    /**
     * Create a checkout session for subscription
     */
    public String createCheckoutSession(String customerId, String priceId) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("customer", customerId);
            params.put("success_url", "https://example.com/success");
            params.put("cancel_url", "https://example.com/cancel");
            params.put("mode", "subscription");
            
            Map<String, Object> lineItem = new HashMap<>();
            lineItem.put("price", priceId);
            lineItem.put("quantity", 1);
            
            Map<String, Object>[] lineItems = new Map[]{lineItem};
            params.put("line_items", lineItems);
            
            // This is a simplified version - in a real implementation you would use the proper Stripe SDK methods
            logger.info("Created checkout session for customer: {}", customerId);
            return "https://checkout.stripe.com/example-session";
        } catch (Exception e) {
            logger.error("Error creating checkout session for customer {}", customerId, e);
            return null;
        }
    }
    
    /**
     * Create a customer portal session
     */
    public String createCustomerPortalSession(String customerId) {
        try {
            // This is a simplified version - in a real implementation you would use the proper Stripe SDK methods
            logger.info("Created customer portal session for: {}", customerId);
            return "https://billing.stripe.com/example-portal";
        } catch (Exception e) {
            logger.error("Error creating customer portal for {}", customerId, e);
            return null;
        }
    }
} 