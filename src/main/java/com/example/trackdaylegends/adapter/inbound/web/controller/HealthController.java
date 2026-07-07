package com.example.trackdaylegends.adapter.inbound.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    @GetMapping("/demo")
    public ResponseEntity<Map<String, String>> demoHealthCheck() {
        Map<String, String> status = new HashMap<>();
        status.put("status", "UP");
        status.put("message", "Track Day Legends API is running smoothly!");
        status.put("version", "1.0.0");
        return ResponseEntity.ok(status);
    }
}
