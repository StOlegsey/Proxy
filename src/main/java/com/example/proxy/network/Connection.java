package com.example.proxy.network;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Enumeration;

public class Connection {
    private final String  url;
    private final RestTemplate restTemplate;

    public Connection(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> makeRequest(String body,
                                              HttpMethod method,
                                              HttpServletRequest request){
        HttpHeaders headers = new HttpHeaders();//getHeaders(request);
        //System.out.println("HEADERS default: "+headers);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, method, entity, String.class);
        System.out.println("Made a request to: "+url);

        return ResponseEntity.status(response.getStatusCode()).headers(response.getHeaders()).body(response.getBody());
    }

    private HttpHeaders getHeaders(HttpServletRequest request){
        HttpHeaders headers = new HttpHeaders();
        Enumeration<String> headerNames = request.getHeaderNames();

        if(headerNames==null){
            return headers;}

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            Enumeration<String> values = request.getHeaders(headerName);

            while (values.hasMoreElements()) {
                String value = values.nextElement();
                headers.add(headerName, value);
            }
        }
        System.out.println("HEADERS custom: "+headers);
        return headers;
    }
}
