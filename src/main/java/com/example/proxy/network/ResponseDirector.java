package com.example.proxy.network;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

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
        Charset charset = Objects.requireNonNull(initialResponse.getHeaders().getContentType()).getCharset();
        return builder
                .setResponse(initialResponse)
                .decode(charset, contentEncoding)
                .build();
    }
}
