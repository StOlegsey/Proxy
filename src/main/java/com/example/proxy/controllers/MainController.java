package com.example.proxy.controllers;

import com.example.proxy.network.Connection;
import com.example.proxy.network.ResponseBuilder;
import com.example.proxy.network.ResponseDirector;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import java.util.Map;

@Controller
public class MainController implements MainControllerInterface{
    private String InitUrl = "localhost:8080";
    private final ResponseBuilder builder = new ResponseBuilder();

    //На будущее: When a request is received by the Spring DispatcherServlet, it is processed by the Spring filter chain.
    //Each filter in the chain can modify the request or response before it is passed to the next filter or to the servlet.

    public ResponseEntity<byte[]> proxyRequest (String body,
                                               String url,
                                               Map<String, String> additionalParams,
                                               HttpServletRequest request) {
        if (url != null) InitUrl = url;
        Connection connection = new Connection(InitUrl + request.getRequestURI(), body, request.getMethod(), additionalParams, request);
        System.out.println("Requesting " + InitUrl + request.getRequestURI());
        ResponseDirector director = new ResponseDirector(builder, connection, (InitUrl + request.getRequestURI()));

        ResponseEntity<byte[]> response = director.buildResponseEntity();
        System.out.println("Returning response for "+((InitUrl + request.getRequestURI())));
        return response;
    }
}