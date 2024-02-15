package com.example.proxy.network;

import com.example.proxy.network.decompression.BrCompressionStrategy;
import com.example.proxy.network.decompression.CompressionStrategy;
import com.example.proxy.network.decompression.GzipCompressionStrategy;
import com.example.proxy.services.ResponseChangeURL;
import com.sun.net.httpserver.Headers;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Objects;

public class ResponseBuilder {

    private ResponseEntity<byte[]> response;
    private String contentEncoding;
    private Charset charset;
    private String decompressedBody;
    private CompressionStrategy compression;
    private String uri;

    public ResponseBuilder setResponse(ResponseEntity<byte[]> response, String uri) {
        this.response = response;
        this.uri = uri;
        contentEncoding = response.getHeaders().getFirst("Content-Encoding");
        charset = Objects.requireNonNull(response.getHeaders().getContentType()).getCharset();

        //Возможно стоит сделать все decompressionStrategy как Бины и засунуть их в Map как value,
        //вместо key поставить типы компресии и оттуда уже вытаскивать нужный по ключу,
        //либо можно создать аннотацию над всеми бинами с типом компрессии и найти тот, над которым наш тип

        System.out.println("Entering the builder for "+uri);

        if(Objects.equals(contentEncoding, "br")) compression = new BrCompressionStrategy();
        else if (Objects.equals(contentEncoding, "gzip")) compression = new GzipCompressionStrategy();
        else {
            System.out.println("Unknown compression");
            return this;
        }
        return this;
    }
    public ResponseBuilder decompress() throws IOException {
        byte[] compressedData = response.getBody();
        decompressedBody = compression.decompress(compressedData, charset);

        System.out.println(uri+"; decompressedBody==initialBody? "+ decompressedBody.equals(compression.decompress(response.getBody(), charset)));

        //if(uri.equals("https://www.google.com/")){
        //    try (FileOutputStream fos = new FileOutputStream("D:Trash/googleResponseByte.bin")) {
        //        fos.write(compressedData);
        //        System.out.println("File has been written");
        //    } catch (IOException e) {
        //        e.printStackTrace();
        //    }
        //}

        return this;
    }
    public ResponseBuilder changeURLs(){
        decompressedBody = ResponseChangeURL.changeUrls(decompressedBody);
        return this;
    }

    public ResponseBuilder compress() throws IOException {

        byte[] body = compression.compress(decompressedBody, charset);
        response = ResponseEntity.status(response.getStatusCode())
                .headers(response.getHeaders())
                .body(body);
        return this;
    }

    public ResponseBuilder changeHeaders(){

        HttpHeaders headers = new HttpHeaders();
        headers.addAll(response.getHeaders());
        headers.setContentLength(Objects.requireNonNull(response.getBody()).length);
        headers.remove("Transfer-Encoding");

        response = ResponseEntity.status(response.getStatusCode())
                .headers(headers)
                .body(response.getBody());
        return this;
    }

    public ResponseEntity<byte[]> build() throws IOException {
        System.out.println(uri+" Header Content-Length: "+response.getHeaders().getFirst("Content-Length")+"; bodyLength: "+response.getBody().length+
                "; recompressedBodyLength: "+response.getBody().length);
        System.out.println(uri+"; responseBody==compressedBody? "+ Arrays.equals(response.getBody(), response.getBody()));
        System.out.println("All stages in builder have successfully passed for "+uri);

        return response;
    }

}
