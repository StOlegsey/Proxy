package com.example.proxy.services;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class ResponseChangeURL {
    public static String changeUrls(String originalMessage, boolean write){

        if(write) {
            try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("googleBeforeChanges.html"));
            writer.write(originalMessage);
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //https://site.com
        //http://site.com
        //https://www.site.com
        //http://www.site.com
        //www.site.com
        originalMessage = originalMessage.replaceAll("(https?://)?www\\.", "http://localhost:8080/?referred=true&url=https://www.");
        if(write) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("googleAfterChanges.html"));
                writer.write(originalMessage);
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return originalMessage;
    }
}
