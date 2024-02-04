package com.example.proxy;

import com.example.proxy.network.Connection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProxyApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProxyApplication.class, args);
        //Connection connection = new Connection("https://www.youtube.com");
        //connection.makeRequest();
    }

}
