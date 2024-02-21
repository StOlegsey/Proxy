package com.example.proxy.services;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;

@Service
public class ResponseChangeURL {
    public static String changeUrls(String originalMessage, boolean write){

        if(write) {
            try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("googleBeforeChanges.html"));
            writer.write(originalMessage);
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //https://site.com
        //http://site.com
        //https://www.site.com
        //http://www.site.com
        //www.site.com

        //https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQdinMWpOUWzMPlPASkvpaJT1VAyNkklnOp2t2kPmE&s=10 не нужно заменять начало на https://www., просто на https://
        // //www. определяет протокол по запросу, то есть по сути //www. == http(s)://www.

        originalMessage = originalMessage.replaceAll("https?://(www\\.)?|//www\\.", "http://localhost:8080/?referred=true&url=https://");
        if(write) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("googleAfterChanges.html"));
                writer.write(originalMessage);
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return originalMessage;
    }
    public static String changeUrlsWithJs(String originalMessage, boolean write){
        if(write) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("googleBeforeJS.html"));
                writer.write(originalMessage);
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        String jsScript = """ 
                <script>document.addEventListener("DOMContentLoaded", function() {
                            var newUrl = "http://localhost:8080/"; // Your defined URL here
                            document.querySelectorAll('a').forEach(function(a) {
                                    console.log('Old href:', a.href);
                                    a.href = newUrl;
                                    console.log('New href:', a.href);
                            });
                            document.querySelectorAll('img, script, iframe').forEach(function(el) {
                                console.log('Old href:', el.src);
                                el.src = newUrl;
                                console.log('New href:', el.src);
                            });
                            document.querySelectorAll('form').forEach(function(form) {
                                console.log('Old href:', form.action);
                                form.action = newUrl;
                                console.log('New href:', form.action);
                            });
                            document.querySelectorAll('[style]').forEach(function(el) {
                                console.log('Old href:', el.style.cssText);
                                el.style.cssText = el.style.cssText.replace(/url\\(['"]?([^'")]+)['"]?\\)/g, `url('${newUrl}')`);
                                console.log('New href:', el.style.cssText);
                            });
                            document.querySelectorAll('style').forEach(function(style) {
                                console.log('Old href:', style.innerHTML);
                                style.innerHTML = style.innerHTML.replace(/url\\(['"]?([^'")]+)['"]?\\)/g, `url('${newUrl}')`);
                                console.log('New href:', style.innerHTML);
                            });
                            console.log("JsScript has been completed");
                        });</script>
                </body>""";
        jsScript = Matcher.quoteReplacement(jsScript);
        originalMessage = originalMessage.replaceAll("</body>", jsScript);

        if(write) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("googleAfterJS.html"));
                writer.write(originalMessage);
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return originalMessage;
    }
}
