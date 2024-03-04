package com.example.proxy.network.decompression;
import com.aayushatharva.brotli4j.Brotli4jLoader;
import com.aayushatharva.brotli4j.encoder.Encoder;
import lombok.extern.slf4j.Slf4j;
import org.brotli.dec.BrotliInputStream;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

@Component("br")
@Slf4j
public class BrCompressionStrategy implements CompressionStrategy {

    @Override
    public String decompress(byte[] compressedData, Charset charset) throws IOException {
        /*Brotli4jLoader.ensureAvailability();
        DirectDecompress directDecompress = Decoder.decompress(compressedData);

        if (directDecompress.getResultStatus() == DecoderJNI.Status.DONE) {
            System.out.println("Brotli Decompression Successful");
            return new String(directDecompress.getDecompressedData(), charset);
        } else {
            System.out.println("Some Error Occurred While Brotli Decompressing");
            return "";
        }*/
        BrotliInputStream brotliInput = new BrotliInputStream(new ByteArrayInputStream(compressedData));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        while ((read = brotliInput.read(buffer)) != -1) {
            baos.write(buffer, 0, read);
        }
        log.info("Br Decompression Successful");
        return baos.toString(charset);

    }

    @Override
    public byte[] compress(String decompressedData, Charset charset) throws IOException {
        Brotli4jLoader.ensureAvailability();
        return Encoder.compress(decompressedData.getBytes(charset));
        /*byte[] inputData = decompressedData.getBytes(charset);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BrotliOutputStream brotliOutput = new BrotliOutputStream(baos);
        brotliOutput.write(inputData);
        brotliOutput.close();
        return baos.toByteArray();*/
    }
}
