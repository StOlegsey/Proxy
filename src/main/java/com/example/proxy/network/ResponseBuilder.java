package com.example.proxy.network;

import com.example.proxy.network.decompression.BrCompressionStrategy;
import com.example.proxy.network.decompression.CompressionStrategy;
import com.example.proxy.network.decompression.GzipCompressionStrategy;
import com.example.proxy.services.ResponseChangeURL;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;

public class ResponseBuilder {

    private ResponseEntity<byte[]> response;
    private String contentEncoding;
    private Charset charset;
    private String decompressedBody;
    private CompressionStrategy compression;
    private boolean compress;

    public ResponseBuilder setResponse(ResponseEntity<byte[]> response, boolean compress) {
        this.response = response;
        this.compress = compress;
        contentEncoding = response.getHeaders().getFirst("Content-Encoding");
        charset = Objects.requireNonNull(response.getHeaders().getContentType()).getCharset();

        //Возможно стоит сделать все decompressionStrategy как Бины и засунуть их в Map как value,
        //вместо key поставить типы компресии и оттуда уже вытаскивать нужный по ключу,
        //либо можно создать аннотацию над всеми бинами с типом компрессии и найти тот, над которым наш тип

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
        //System.out.println("Decoded Body: "+decodedBody);
        return this;
    }
    public ResponseBuilder changeURLs(){
        decompressedBody = ResponseChangeURL.changeUrls(decompressedBody);
        return this;
    }

    public ResponseEntity<byte[]> build() throws IOException {
        byte[] body = response.getBody();
        if(compress) {
            body = compression.compress(decompressedBody, charset);
        }
        System.out.println("All stages in builder have successfully passed");

        return ResponseEntity.status(response.getStatusCode())
                .headers(response.getHeaders())
                .body(body);
    }

}
