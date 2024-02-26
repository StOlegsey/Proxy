package com.example.proxy.network.decompression;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

public class CompressionStrategyFactory{

    private final ApplicationContext applicationContext;

    public CompressionStrategyFactory(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    public CompressionStrategy getCompression(String encoding){
        return applicationContext.getBean(encoding, CompressionStrategy.class);
    }
}
