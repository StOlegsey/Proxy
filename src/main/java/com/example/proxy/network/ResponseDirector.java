package com.example.proxy.network;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

public class ResponseDirector {

    private final ResponseBuilder builder;
    private final Connection connection;

    public ResponseDirector(ResponseBuilder builder, Connection connection) {
        this.builder = builder;
        this.connection = connection;
    }

    public ResponseEntity<byte[]> buildResponseEntity(){

        ResponseEntity<byte[]> initialResponse = connection.makeRequest();
        String contentEncoding = initialResponse.getHeaders().getFirst("Content-Encoding");
        String contentType = initialResponse.getHeaders().getFirst("Content-Type");
        System.out.println("Response Content-type: "+contentType+
                "\nResponse Content-encoding: "+ contentEncoding);

        if(contentType.startsWith("text")) {
            try {
                if(contentEncoding!=null && !contentEncoding.equals("")) {
                    return builder
                            .setResponse(initialResponse, true)
                            .decompress()
                            //.changeURLs()
                            .build();
                }
                else {
                    return builder
                            .setResponse(initialResponse, false)
                            .build();
                }
            } catch (IOException e) {
                System.out.println("Error in response builder: " + e);
                return initialResponse;
            }
        }
        else {
            System.out.println("Returning image; No builder usage");
            return initialResponse;
        }
    }
}
