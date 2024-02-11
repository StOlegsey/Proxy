package com.example.proxy.controllers;

import com.example.proxy.network.Connection;
import com.example.proxy.network.ResponseBuilder;
import com.example.proxy.network.ResponseDirector;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.brotli.dec.BrotliInputStream;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;;

@Controller
public class MainController implements MainControllerInterface{

    private String InitUrl = null;

    private final ResponseBuilder builder = new ResponseBuilder();

    //На будущее: When a request is received by the Spring DispatcherServlet, it is processed by the Spring filter chain.
    //Each filter in the chain can modify the request or response before it is passed to the next filter or to the servlet.

    public ResponseEntity<byte[]> proxyRequest (String body,
                                               String url,
                                               Map<String, String> additionalParams,
                                               HttpServletRequest request) {
        if (url != null) InitUrl = url;

        /*try {
            String servletBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            System.out.println("ServletBody: "+servletBody+" StringBody: "+body.length());
        } catch (Exception e) {
            System.out.println(e);
            //throw new RuntimeException(e);
        }*/

        //System.out.println("method: "+request.getMethod()+"; request: "+ (request.getRequestURI()));

        //Сделать билдер ответа типо finalResponse.addConnectionb(connection).Decode(utf8, gzip).ChangeUrls.CodeTo(gzip).build(),
        //        билдер делать в другом классе прослойке, который будет провериять, нужен ли он для данного ответа и что туда передавать

        Connection connection = new Connection(InitUrl + request.getRequestURI(), body, request.getMethod(), additionalParams, request);

        ResponseDirector director = new ResponseDirector(builder, connection);
        //if (url != null) {
            //String filePath = "D:/Trash/google.txt";

            //try {
            //    String responseBody = new String(response.getBody(), StandardCharsets.ISO_8859_1);
            //    // Write the response body to the file
            //    Files.write(Paths.get(filePath), response.getBody(), StandardOpenOption.CREATE);
            //} catch (IOException e) {
            //    e.printStackTrace(); // Handle the exception appropriately
            //}

        System.out.println("Requesting " + InitUrl + request.getRequestURI());
            /*try {

                String responseBody = decompress(response.getBody());
                System.out.println(
                        "\nResponse ContentType: " + response.getHeaders().getContentType() +
                        "\nResponse body:  " + responseBody +
                        "\n================================================================================================");
            } catch (Exception e) {
                System.out.println("Exception "+e);
                //throw exception;
            }*/
        //}
        return director.buildResponseEntity();
    }
}
