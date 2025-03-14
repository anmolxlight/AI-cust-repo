package com.example.demo2.service;

import com.example.demo2.config.ElevenLabsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VoiceAgentService {
    private static final Logger logger = LoggerFactory.getLogger(VoiceAgentService.class);
    
    private final ElevenLabsConfig elevenLabsConfig;
    private final StripeService stripeService;
    
    public VoiceAgentService(ElevenLabsConfig elevenLabsConfig, StripeService stripeService) {
        this.elevenLabsConfig = elevenLabsConfig;
        this.stripeService = stripeService;
    }
    
    public byte[] processVoiceQuery(byte[] audioData) {
        try {
            // 1. Convert audio to text using ElevenLabs
            String transcribedText = transcribeAudio(audioData);
            logger.info("Transcribed text: {}", transcribedText);
            
            // 2. Determine if this is a tool request
            if (isStripeToolRequest(transcribedText)) {
                return handleStripeToolRequest(transcribedText);
            } else {
                // Regular conversation response
                return generateConversationalResponse(transcribedText);
            }
        } catch (Exception e) {
            logger.error("Error processing voice query", e);
            return textToSpeech("I'm sorry, I encountered an error processing your request.");
        }
    }
    
    private boolean isStripeToolRequest(String text) {
        String lowerText = text.toLowerCase();
        return lowerText.contains("refund") || 
               lowerText.contains("subscription") || 
               lowerText.contains("payment") ||
               lowerText.contains("cancel");
    }
    
    private byte[] handleStripeToolRequest(String text) {
        // Determine which Stripe tool to call
        if (text.toLowerCase().contains("refund")) {
            return handleRefundRequest(text);
        } else if (text.toLowerCase().contains("subscription")) {
            return handleSubscriptionRequest(text);
        } else {
            return textToSpeech("I can help with refunds and subscription management. Could you please specify what you need help with?");
        }
    }
    
    private byte[] handleRefundRequest(String text) {
        // Extract payment ID from the request
        String paymentId = extractPaymentId(text);
        
        // Process refund
        boolean refundSuccess = stripeService.processRefund(paymentId);
        
        // Generate response based on refund result
        String responseText = refundSuccess ? 
            "Your refund for payment " + paymentId + " has been processed successfully." :
            "I'm sorry, there was an issue processing your refund. Please contact customer support.";
        
        return textToSpeech(responseText);
    }
    
    private byte[] handleSubscriptionRequest(String text) {
        // This would call the appropriate Stripe subscription management methods
        return textToSpeech("I can help you manage your subscription. What would you like to do?");
    }
    
    private byte[] generateConversationalResponse(String text) {
        // This would generate a conversational response based on the input
        return textToSpeech("I understand you said: " + text + ". How can I help you today?");
    }
    
    private String transcribeAudio(byte[] audioData) {
        // Implement speech-to-text using ElevenLabs
        if (logger.isInfoEnabled()) {
            String maskedKey = elevenLabsConfig.getApiKey().substring(0, 5) + "...";
            logger.info("Transcribing audio data using ElevenLabs API with key: {}", maskedKey);
        }
        // In a real implementation, you would call the ElevenLabs API here
        return "I would like a refund for my payment XYZ123";
    }
    
    private String extractPaymentId(String text) {
        // Extract payment ID from the text
        if (text.contains("XYZ")) {
            int start = text.indexOf("XYZ");
            int end = Math.min(start + 10, text.length());
            return text.substring(start, end);
        }
        return "unknown";
    }
    
    private byte[] textToSpeech(String text) {
        // Convert text to speech using ElevenLabs API
        if (logger.isInfoEnabled()) {
            String maskedKey = elevenLabsConfig.getApiKey().substring(0, 5) + "...";
            logger.info("Converting to speech using ElevenLabs API (key: {}...): {}", maskedKey, text);
        }
        // In a real implementation, you would call the ElevenLabs API here
        return new byte[0]; // Placeholder
    }
} 