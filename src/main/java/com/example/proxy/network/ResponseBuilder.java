package com.example.proxy.network;

import com.example.proxy.network.decompression.BrCompressionStrategy;
import com.example.proxy.network.decompression.CompressionStrategy;
import com.example.proxy.network.decompression.GzipCompressionStrategy;
import com.example.proxy.services.ResponseChangeURL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;

@Slf4j
public class ResponseBuilder {

    private ResponseEntity<byte[]> response;
    private Charset charset;
    private String decompressedBody;
    private CompressionStrategy compression;
    private String uri;

    public ResponseBuilder setResponse(ResponseEntity<byte[]> response, String uri) {
        this.response = response;
        this.uri = uri;

        String contentEncoding = response.getHeaders().getFirst("Content-Encoding");
        charset = Objects.requireNonNull(response.getHeaders().getContentType()).getCharset();

        //Возможно стоит сделать все decompressionStrategy как Бины и засунуть их в Map как value,
        //вместо key поставить типы компресии и оттуда уже вытаскивать нужный по ключу,
        //либо можно создать аннотацию над всеми бинами с типом компрессии и найти тот, над которым наш тип

        log.info("Entering the builder for "+uri);

        if(Objects.equals(contentEncoding, "br")) compression = new BrCompressionStrategy();
        else if (Objects.equals(contentEncoding, "gzip")) compression = new GzipCompressionStrategy();
        else {
            log.info("Unknown compression");
            return this;
        }
        return this;
    }
    public ResponseBuilder decompress() throws IOException {

        byte[] compressedData = response.getBody();
        decompressedBody = compression.decompress(compressedData, charset);
        log.info(uri+"; decompressedBody==initialBody? "+ decompressedBody.equals(compression.decompress(response.getBody(), charset)));
        return this;
    }
    public ResponseBuilder changeURLs(){

        boolean write = uri.equals("https://www.google.com/");

        decompressedBody = ResponseChangeURL.changeUrls(decompressedBody, write);
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
        headers.add("Location", "https://www.google.com");
        headers.setContentLength(Objects.requireNonNull(response.getBody()).length);
        headers.remove("Transfer-Encoding");

        response = ResponseEntity.status(response.getStatusCode())
                .headers(headers)
                .body(response.getBody());
        return this;
    }

    public ResponseEntity<byte[]> build() throws IOException {
        log.info(uri+" Header Content-Length: "+response.getHeaders().getFirst("Content-Length")+"; bodyLength: "+response.getBody().length);
        //log.info(uri+"; responseBody==compressedBody? "+ Arrays.equals(response.getBody(), response.getBody()));
        log.info("All stages in builder have successfully passed for "+uri);

        return response;
    }

}
