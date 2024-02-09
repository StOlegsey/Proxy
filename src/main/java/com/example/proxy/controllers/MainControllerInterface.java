package com.example.proxy.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface MainControllerInterface {

    @RequestMapping(value = "/**")
    public ResponseEntity<byte[]> proxyRequest(@RequestBody(required = false) String body,
                                               @RequestParam(required = false) String url,
                                               @RequestParam Map<String, String> additionalParams,
                                               HttpServletRequest request);

}
