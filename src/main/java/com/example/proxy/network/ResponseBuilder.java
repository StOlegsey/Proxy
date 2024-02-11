package com.example.proxy.network;

import jakarta.servlet.http.HttpServletRequest;
import org.brotli.dec.BrotliInputStream;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class ResponseBuilder {

    private ResponseEntity<byte[]> response;
    public ResponseBuilder setResponse(ResponseEntity<byte[]> response) {
        this.response = response;
        return this;
    }

    public ResponseBuilder decode(Charset charsets, String encoding) {

        System.out.println("Response charsets: "+charsets+"; encoding: "+encoding);
        return this;
    }

    public ResponseBuilder decompress(byte[] compressedData) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(compressedData);
        try (BrotliInputStream bris = new BrotliInputStream(bis);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bris.read(buffer)) > 0) {
                bos.write(buffer, 0, len);
            }
            //return new String(bos.toByteArray(), StandardCharsets.UTF_8);
            return this;
        }
    }

    public ResponseEntity<byte[]> build() {
        return response;
    }
}
