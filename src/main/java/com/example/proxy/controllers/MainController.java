package com.example.proxy.controllers;

import com.example.proxy.network.Connection;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@RestController
public class MainController {

    private String InitUrl = null;

    @RequestMapping(value = "/**", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<String> proxyRequest(@RequestBody(required = false) String body,
                                               @RequestParam(required = false) String url,
                                               HttpMethod method,
                                               HttpServletRequest request) {
        if(url != null) {InitUrl = url;}

        System.out.println("method: "+method+" request: "+ (InitUrl + request.getRequestURI())+"\nRequest length: "+request.getContentLength());

        Connection connection = new Connection(InitUrl+request.getRequestURI(), new RestTemplate());
        ResponseEntity<String> response = connection.makeRequest(body,method,request);

        try {
            System.out.println("Response body Length: "+response.getBody().length());
        }
        catch (NullPointerException exception){
            System.out.println("Response is null");
            //throw exception;
        }
        return response;
    }
}
