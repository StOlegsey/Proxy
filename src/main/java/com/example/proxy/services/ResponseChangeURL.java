package com.example.proxy.services;

import org.springframework.stereotype.Service;

@Service
public class ResponseChangeURL {
    public static String changeUrls(String originalMessage){
        originalMessage.replaceAll("https://", "http://localhost:8080/?url=https://");
        return originalMessage;
    }
}
