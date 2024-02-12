package com.example.proxy.network.decompression;

import java.io.IOException;
import java.nio.charset.Charset;

public interface CompressionStrategy {
    String decompress(byte[] compressedData, Charset charset) throws IOException;

    byte[] compress(String decompressedData, Charset charset) throws IOException;
}
