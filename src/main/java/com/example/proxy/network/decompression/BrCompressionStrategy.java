package com.example.proxy.network.decompression;

import com.aayushatharva.brotli4j.Brotli4jLoader;
import com.aayushatharva.brotli4j.decoder.Decoder;
import com.aayushatharva.brotli4j.decoder.DecoderJNI;
import com.aayushatharva.brotli4j.decoder.DirectDecompress;
import com.aayushatharva.brotli4j.encoder.Encoder;

import java.io.IOException;
import java.nio.charset.Charset;

public class BrCompressionStrategy implements CompressionStrategy {

    @Override
    public String decompress(byte[] compressedData, Charset charset) throws IOException {
        Brotli4jLoader.ensureAvailability();
        DirectDecompress directDecompress = Decoder.decompress(compressedData);

        if (directDecompress.getResultStatus() == DecoderJNI.Status.DONE) {
            System.out.println("Decompression Successful");
            return new String(directDecompress.getDecompressedData(), charset);
        } else {
            System.out.println("Some Error Occurred While Decompressing");
            return "";
        }
    }

    @Override
    public byte[] compress(String decompressedData, Charset charset) throws IOException {
        Brotli4jLoader.ensureAvailability();
        return Encoder.compress(decompressedData.getBytes(charset));
    }
}
