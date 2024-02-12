package com.example.proxy.network.decompression;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.GZIPOutputStream;

public class GzipCompressionStrategy implements CompressionStrategy {
    @Override
    public String decompress(byte[] compressedData, Charset charset) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(compressedData);
        java.util.zip.GZIPInputStream gzip = new java.util.zip.GZIPInputStream(bis);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = gzip.read(buffer)) > 0) {
            bos.write(buffer, 0, len);
        }
        return bos.toString(charset);
    }

    @Override
    public byte[] compress(String decompressedData, Charset charset) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(baos);
        gzip.write(decompressedData.getBytes(charset));
        gzip.flush();
        gzip.close();
        return baos.toByteArray();
    }
}
