package com.example.proxy.network;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Enumeration;
import java.util.Map;

@Slf4j
public class Connection {
    private final String  url;
    private String body;
    private String method;
    Map<String, String> additionalParams;
    HttpServletRequest request;
    private final RestTemplate restTemplate = new RestTemplate();

    public Connection(String url, String body, String method, Map<String, String> additionalParams, HttpServletRequest request) {
        this.url = url;
        this.body = body;
        this.method = method;
        this.additionalParams = additionalParams;
        this.request = request;
    }

    public ResponseEntity<byte[]> makeRequest(){
        HttpHeaders headers = getHeaders(request);
        //System.out.println("HEADERS default: "+headers);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
        for (Map.Entry<String, String> entry: additionalParams.entrySet()) {
                if(!entry.getKey().equals("url")) uriBuilder.queryParam(entry.getKey(), entry.getValue());
            }

        log.info("Making a request to: "+uriBuilder.toUriString());

        try {
            ResponseEntity<byte[]> response = restTemplate.exchange(
                    uriBuilder.toUriString(),
                    HttpMethod.valueOf(method),
                    entity,
                    byte[].class);

            return ResponseEntity.status(response.getStatusCode())
                    .headers(response.getHeaders())
                    .body(response.getBody());
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .headers(e.getResponseHeaders())
                    .body(e.getResponseBodyAsByteArray());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(("Unexpected error: " + e.getMessage()).getBytes());
        }

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
        //System.out.println("HEADERS custom: "+headers);
        return headers;
    }

}
