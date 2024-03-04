package com.example.proxy.network;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

@Slf4j
public class ResponseDirector {

    private ResponseBuilder builder;
    public ResponseDirector(ResponseBuilder builder) {
        this.builder = builder;
    }

    public ResponseEntity<byte[]> buildResponseEntity(Connection connection, String requestedUri){

        ResponseEntity<byte[]> initialResponse = connection.makeRequest();
        String contentEncoding = initialResponse.getHeaders().getFirst("Content-Encoding");
        String contentType = initialResponse.getHeaders().getFirst("Content-Type");
        log.info(initialResponse.getStatusCode()+"; Response from: "+requestedUri+
                "\n\tResponse Content-type: "+contentType+
                "\n\tResponse Content-encoding: "+ contentEncoding);

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
                log.info("Error in response builder: " + e);
                return initialResponse;
            }
        }
        else {
            log.info("Returning image from "+requestedUri+"; No builder usage");
            return initialResponse;
        }
    }
}
