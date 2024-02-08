package com.example.proxy.controllers;

import com.example.proxy.network.Connection;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;

@Controller
public class MainController {

    private String InitUrl = null;

    @RequestMapping(value = "/**")
    public ResponseEntity<byte[]> proxyRequest(@RequestBody(required = false) String body,
                                               @RequestParam(required = false) String url,
                                               @RequestParam Map<String, String> additionalParams,
                                               HttpMethod method,
                                               HttpServletRequest request) {
        if(url != null) {InitUrl = url;}

        System.out.println("method: "+method+"; request: "+ (request.getRequestURI()));

        /*if(Objects.equals(request.getRequestURI(), "/search")) {
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                Enumeration<String> values = request.getHeaders(headerName);

                while (values.hasMoreElements()) {
                    String value = values.nextElement();
                    System.out.println("HeaderName: " + headerName + " value: " + value);
                }
            }
        }*/
        //for (Map.Entry<String, String> entry: additionalParams.entrySet()) {
        //    System.out.println(entry.getKey() + ":" + entry.getValue());
        //}

        Connection connection = new Connection(InitUrl+request.getRequestURI());
        ResponseEntity<byte[]> response = connection.makeRequest(body,method, additionalParams, request);

        try {
            System.out.println("Response body Length: "+response.getBody().toString().length()+
                    "\nResponse ContentType: "+response.getHeaders().getContentType());
        }
        catch (NullPointerException exception){
            System.out.println("Response is null");
            //throw exception;
        }
        return response;
    }
}
