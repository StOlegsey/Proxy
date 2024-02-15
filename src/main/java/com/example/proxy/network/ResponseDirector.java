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
    private final String requestedUri;

    public ResponseDirector(ResponseBuilder builder, Connection connection, String requestedUri) {
        this.builder = builder;
        this.connection = connection;
        this.requestedUri = requestedUri;
    }

    public ResponseEntity<byte[]> buildResponseEntity(){

        ResponseEntity<byte[]> initialResponse = connection.makeRequest();
        String contentEncoding = initialResponse.getHeaders().getFirst("Content-Encoding");
        String contentType = initialResponse.getHeaders().getFirst("Content-Type");
        System.out.println("Response from: "+requestedUri+
                "\nResponse Content-type: "+contentType+
                "\nResponse Content-encoding: "+ contentEncoding);

        if(contentEncoding!=null && !contentEncoding.equals("") && (contentType.startsWith("text/") || contentType.contains("json") || contentType.contains("xml"))) {
            try {
                    return builder
                            .setResponse(initialResponse, requestedUri)
                            .decompress()
                            .changeURLs()
                            .compress()
                            .changeHeaders()
                            .build();

            } catch (IOException e) {
                System.out.println("Error in response builder: " + e);
                return initialResponse;
            }
        }
        else {
            System.out.println("Returning image from "+requestedUri+"; No builder usage");
            return initialResponse;
        }
    }
}
