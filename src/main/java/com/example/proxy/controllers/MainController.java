package com.example.proxy.controllers;

import com.example.proxy.network.Connection;
import com.example.proxy.network.ResponseDirector;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
@Slf4j
public class MainController implements MainControllerInterface{

    @Autowired
    private ResponseDirector director;

    private String InitUrl = "localhost:8080";

    //На будущее: When a request is received by the Spring DispatcherServlet, it is processed by the Spring filter chain.
    //Each filter in the chain can modify the request or response before it is passed to the next filter or to the servlet.

    public ResponseEntity<byte[]> proxyRequest (String body,
                                               String url,
                                               Boolean referred,
                                               Map<String, String> additionalParams,
                                               HttpServletRequest request) {

        if (url != null && !referred) InitUrl = url;
        String currentUrl = InitUrl + request.getRequestURI();

        if (referred) currentUrl = url;

        Connection connection = new Connection(currentUrl, body, request.getMethod(), additionalParams, request);
        log.info("Requesting " + currentUrl);

        ResponseEntity<byte[]> response = director.buildResponseEntity(connection, currentUrl);
        log.info("Returning response for "+(currentUrl));
        return response;
    }
}