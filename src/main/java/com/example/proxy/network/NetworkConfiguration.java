package com.example.proxy.network;

import com.example.proxy.network.decompression.CompressionStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class NetworkConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    /*@Bean
    public ResponseDirector responseDirector(ResponseBuilder builder, Connection connection) {
        return new ResponseDirector(responseBuilder(), connection, "someRequestedUri");
    }*/

    @Bean
    public CompressionStrategyFactory compressionStrategyFactory(){
        return new CompressionStrategyFactory(applicationContext);
    }

    @Bean
    public ResponseBuilder responseBuilder(){
        return new ResponseBuilder(compressionStrategyFactory());
    }



}
