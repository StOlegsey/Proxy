package com.example.proxy.controllers;

import com.example.proxy.network.Connection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.stream.Collectors;;

@Controller
public class MainController implements MainControllerInterface{

    private String InitUrl = null;

    //На будущее: When a request is received by the Spring DispatcherServlet, it is processed by the Spring filter chain.
    //Each filter in the chain can modify the request or response before it is passed to the next filter or to the servlet.

    public ResponseEntity<byte[]> proxyRequest(String body,
                                               String url,
                                               Map<String, String> additionalParams,
                                               HttpServletRequest request) {
        if(url != null) {InitUrl = url;}

        /*try {
            String servletBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            System.out.println("ServletBody: "+servletBody+" StringBody: "+body.length());
        } catch (Exception e) {
            System.out.println(e);
            //throw new RuntimeException(e);
        }*/

        System.out.println("method: "+request.getMethod()+"; request: "+ (request.getRequestURI()));

        Connection connection = new Connection(InitUrl+request.getRequestURI());
        ResponseEntity<byte[]> response = connection.makeRequest(body, request.getMethod(), additionalParams, request);

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
