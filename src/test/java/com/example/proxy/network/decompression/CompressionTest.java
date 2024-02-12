package com.example.proxy.network.decompression;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

@SpringBootTest
public class CompressionTest {

    @Test
    public void checkCompressions() throws IOException {
        CompressionStrategy compressionStrategy = new BrCompressionStrategy();
        String testHtml = "<!doctype html>\n" +
                "<html itemscope=\"\" itemtype=\"http://schema.org/WebPage\" lang=\"ru\">\n" +
                "    <head>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta content=\"origin\" name=\"referrer\">\n" +
                "        <meta content=\"/images/branding/googleg/1x/googleg_standard_color_128dp.png\" itemprop=\"image\">\n" +
                "        <title>Google</title>\n" +
                "        <script nonce=\"WannE0-5xvH-83aoJxfoNQ\">\n" +
                "            (function() {\n" +
                "                var _g = {\n" +
                "                    kEI: 'CzvKZe2HN7PSwPAPnPOo8AM',\n" +
                "                    kEXPI: '31',\n" +
                "                    kBL: 'Oxxt',\n" +
                "                    kOPI: 89978449\n" +
                "                };\n" +
                "                (function() {\n" +
                "                    var a;\n" +
                "                    (null == (a = window.google) ? 0 : a.stvsc) ? google.kEI = _g.kEI : window.google = _g;\n" +
                "                }\n" +
                "                ).call(this);\n" +
                "            }\n" +
                "            )();\n" +
                "            (function() {\n" +
                "                google.sn = 'webhp';\n" +
                "                google.kHL = 'ru';\n" +
                "            }\n" +
                "            )();\n" +
                "            (function() {\n" +
                "                var h = this || self;\n" +
                "                function l() {\n" +
                "                    return void 0 !== window.google && void 0 !== window.google.kOPI && 0 !== window.google.kOPI ? window.google.kOPI : null\n" +
                "                }\n" +
                "                ;var m, n = [];\n" +
                "                function p(a) {\n" +
                "                    for (var b; a && (!a.getAttribute || !(b = a.getAttribute(\"eid\"))); )\n" +
                "                        a = a.parentNode;\n" +
                "                    return b || m\n" +
                "                }\n" +
                "                function q(a) {\n" +
                "                    for (var b = null; a && (!a.getAttribute || !(b = a.getAttribute(\"leid\"))); )\n" +
                "                        a = a.parentNode;\n" +
                "                    return b\n" +
                "                }\n" +
                "                function r(a) {\n" +
                "                    /^http:/i.test(a) && \"https:\" === window.location.protocol && (google.ml && google.ml(Error(\"a\"), !1, {\n" +
                "                        src: a,\n" +
                "                        glmm: 1\n" +
                "                    }),\n" +
                "                    a = \"\");\n" +
                "                    return a\n" +
                "                }\n" +
                "                function t(a, b, c, d, k) {\n" +
                "                    var e = \"\";\n" +
                "                    -1 === b.search(\"&ei=\") && (e = \"&ei=\" + p(d),";

        byte[] compressed = compressionStrategy.compress(testHtml, StandardCharsets.UTF_8);
        String decompressed = compressionStrategy.decompress(compressed,StandardCharsets.UTF_8);

        assertEquals(testHtml, decompressed);
    }
}
