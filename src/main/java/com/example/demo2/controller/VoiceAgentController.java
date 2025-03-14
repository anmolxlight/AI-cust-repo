package com.example.demo2.controller;

import com.example.demo2.service.VoiceAgentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/voice-agent")
public class VoiceAgentController {
    private static final Logger logger = LoggerFactory.getLogger(VoiceAgentController.class);
    
    private final VoiceAgentService voiceAgentService;
    
    public VoiceAgentController(VoiceAgentService voiceAgentService) {
        this.voiceAgentService = voiceAgentService;
    }
    
    @PostMapping("/query")
    public ResponseEntity<byte[]> processVoiceQuery(@RequestParam("audio") MultipartFile audioFile) {
        try {
            logger.info("Received voice query, processing...");
            byte[] responseAudio = voiceAgentService.processVoiceQuery(audioFile.getBytes());
            
            return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(responseAudio);
        } catch (IOException e) {
            logger.error("Error processing voice query", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping("/start-conversation")
    public ResponseEntity<Map<String, Object>> startConversation() {
        logger.info("Starting new conversation");
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Conversation started");
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Voice Agent Service is running");
    }
} 