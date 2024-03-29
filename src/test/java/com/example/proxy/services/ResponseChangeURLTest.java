package com.example.proxy.services;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;

public class ResponseChangeURLTest {

    @Test
    public void changeURLsInExample(){
        String testMsg = "IC{border-bottom-color:transparent;border-left-color:transparent}.RoKmhb .tS3P5{border-bottom-color:transparent}.GgTJWe .nNMuOd .J7uuUe{-webkit-animation:qli-left-spin 1333ms cubic-bezier(0.4,0,0.2,1) infinite both;animation:qli-left-spin 1333ms cubic-bezier(0.4,0,0.2,1) infinite both}.GgTJWe .nNMuOd .sDPIC{-webkit-animation:qli-right-spin 1333ms cubic-bezier(0.4,0,0.2,1) infinite both;animation:qli-right-spin 1333ms cubic-bezier(0.4,0,0.2,1) infinite both}.BSnLb .nNMuOd .J7uuUe{-webkit-animation:qli-left-spin 1333ms cubic-bezier(0.4,0,0.2,1) infinite both;animation:qli-left-spin 1333ms cubic-bezier(0.4,0,0.2,1) infinite both;border-left-color:#fff;border-top-color:#fff}.BSnLb .nNMuOd .sDPIC{-webkit-animation:qli-right-spin 1333ms cubic-bezier(0.4,0,0.2,1) infinite both;animation:qli-right-spin 1333ms cubic-bezier(0.4,0,0.2,1) infinite both;border-right-color:#fff;border-top-color:#fff}.BSnLb .nNMuOd .tS3P5{border-color:#fff;border-bottom-color:transparent}@-webkit-keyframes qli-left-spin{0%{-webkit-transform:rotate(130deg);transform:rotate(130deg)}50%{-webkit-transform:rotate(-5deg);transform:rotate(-5deg)}100%{-webkit-transform:rotate(130deg);transform:rotate(130deg)}}@keyframes qli-left-spin{0%{-webkit-transform:rotate(130deg);transform:rotate(130deg)}50%{-webkit-transform:rotate(-5deg);transform:rotate(-5deg)}100%{-webkit-transform:rotate(130deg);transform:rotate(130deg)}}@-webkit-keyframes qli-right-spin{0%{-webkit-transform:rotate(-130deg);transform:rotate(-130deg)}50%{-webkit-transform:rotate(5deg);transform:rotate(5deg)}100%{-webkit-transform:rotate(-130deg);transform:rotate(-130deg)}}@keyframes qli-right-spin{0%{-webkit-transform:rotate(-130deg);transform:rotate(-130deg)}50%{-webkit-transform:rotate(5deg);transform:rotate(5deg)}100%{-webkit-transform:rotate(-130deg);transform:rotate(-130deg)}}.hObAcc{margin-left:4px;margin-right:4px}.gTewb{padding-left:8px;padding-right:8px}.thsZXc{background:#1a73e8}.Bb1JKe{padding-bottom:8px}.ouy7Mc{padding-left:16px;padding-right:16px}.M8CEed{padding-top:12px}sentinel{}.z1asCe{display:inline-block;fill:currentColor;height:24px;line-height:24px;position:relative;width:24px}.z1asCe svg{display:block;height:100%;width:100%}</style></head><body jsmodel=\"hspDDf \"><style>.L3eUgb{display:flex;flex-direction:column;height:100%}.o3j99{flex-shrink:0;box-sizing:border-box}.n1xJcf{height:60px}.LLD4me{min-height:150px;height:calc(100% - 560px);max-height:290px}.yr19Zb{min-height:92px}.ikrT4e{max-height:160px}.mwht9d{position:absolute;left:-1000px}.ADHj4e{padding-top:0px;padding-bottom:85px}.oWyZre{width:100%;height:500px;border-width:0}.qarstb{flex-grow:1}</style><div class=\"L3eUgb\" data-hveid=\"1\"><div class=\"o3j99 n1xJcf Ne6nSd\" role=\"navigation\"><style>.Ne6nSd{display:flex;align-items:center;padding:6px}.LX3sZb{display:inline-block;flex-grow:1}</style><div class=\"LX3sZb\"><div><div class=\"gb_Pa gb_ld gb_gb\" id=\"gb\"><div class=\"gb_Bd gb_eb gb_qd\" data-ogsr-up=\"\"><div><div class=\"gb_Id gb_J gb_3f gb_Tf\" data-ogbl=\"\"><div class=\"gb_I gb_J\"><a class=\"gb_H\" aria-label=\"Почта (откроется новая вкладка)\" data-pid=\"23\" href=\"https://mail.google.com/mail/&amp;ogbl\" target=\"_top\">Почта</a></div><div class=\"gb_I gb_J\"><a class=\"gb_H\" aria-label=\"Поиск картинок (откроется новая вкладка)\" data-pid=\"2\" href=\"https://www.google.com/imghp?hl=ru&amp;ogbl\" target=\"_top\">Картинки</a></div></div></div><div class=\"gb_Ud\"><div class=\"gb_6c\"><div class=\"gb_k gb_w gb_J\" data-ogsr-fb=\"true\" data-ogsr-alt=\"\" id=\"gbwa\"><div class=\"gb_f\"><a class=\"gb_d\" aria-label=\"Приложения Google\" href=\"https://www.google.ru/intl/ru/about/products\" aria-expanded=\"false\" role=\"button\" tabindex=\"0\"><svg class=\"gb_h\" focusable=\"false\" viewbox=\"0 0 24 24\"><path d=\"M6,8c1.1,0 2,-0.9 2,-2s-0.9,-2 -2,-2 -2,0.9 -2,2 0.9,2 2,2zM12,20c1.1,0 2,-0.9 2,-2s-0.9,-2 -2,-2 -2,0.9 -2,2 0.9,2 2,2zM6,20c1.1,0 2,-0.9 2,-2s-0.9,-2 -2,-2 -2,0.9 -2,2 0.9,2 2,2zM6,14c1.1,0 2,-0.9 2,-2s-0.9,-2 -2,-2 -2,0.9 -2,2 0.9,2 2,2zM12,14c1.1,0 2,-0.9 2,-2s-0.9,-2 -2,-2 -2,0.9 -2,2 0.9,2 2,2zM16,6c0,1.1 0.9,2 2,2s2,-0.9 2,-2 -0.9,-2 -2,-2 -2,0.9 -2,2zM12,8c1.1,0 2,-0.9 2,-2s-0.9,-2 -2,-2 -2,0.9 -2,2 0.9,2 2,2zM18,14c1.1,0 2,-0.9 2,-2s-0.9,-2 -2,-2 -2,0.9 -2,2 0.9,2 2,2zM18,20c1.1,0 2,-0.9 2,-2s-0.9,-2 -2,-2 -2,0.9 -2,2 0.9,2 2,2z\"></path><image src=\"https://ssl.gstatic.com/gb/images/bar/al-icon.png\" alt=\"\" height=\"24\" width=\"24\" style=\"border:none;display:none \\9\"></image></svg></a></div></div></div><a class=\"gb_Ba gb_md gb_Od gb_me\" aria-label=\"Войти\" href=\"https://accounts.google.com/ServiceLogin?hl=ru&amp;passive=true&amp;continue=https://www.google.com/&amp;ec=GAZAmgQ\" target=\"_top\"><span class=\"gb_Kd\">Войти</span></a></div></div></div></div></div></div><div class=\"o3j99 LLD4me yr19Zb LS8OJ\"><style>.LS8OJ{display:flex;flex-direction:column;align-items:center}.k1zIA{height:100%;margin-top:auto}</style><div class=\"k1zIA rSk4se\"><style>.rSk4se{max-height:92px;position:relative}.lnXdpd{max-height:100%;max-width:100%;object-fit:contain;object-position:center bottom;width:auto}</style><img class=\"lnXdpd\" alt=\"Google\" height=\"92\" src=\"/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png\" srcset=\"/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png 1x, /images/branding/googlelogo/2x/googlelogo_color_272x92dp.png 2x\" width=\"272\"></div></div><div class=\"o3j99 ikrT4e om7nvf\"><style>.om7nvf{padding:20px}</style><dialog class=\"spch-dlg\" id=\"spch-dlg\"><div class=\"spch\" style=\"display:none\" id=\"spch\"></div></dialog><form action=\"";
        testMsg.replaceAll("https?://", "http://localhost:8080/?url=https://");
        System.out.println(testMsg);
        System.out.println(testMsg.replaceAll("https?://", "http://localhost:8080/?url=https://"));
    }

    @Test
    public void changeURLsWithScript(){
        String testMsg = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <title>Your Page Title</title>
                    <!-- other head elements (stylesheets, meta tags, etc.) -->
                </head>
                <body>
                    <!-- Your page content here (links, images, etc.) -->

                    <script>
                        SOMESCRIPT
                        });
                    </script>
                </body>
                </html>""";
        System.out.println(testMsg);
        String jsScript = """ 
                <script>document.addEventListener("DOMContentLoaded", function() {
                  var newUrl = "http://localhost:8080/"; // Your defined URL here

                  // Change href for all <a> tags
                  document.querySelectorAll('a').forEach(function(a) {
                    a.href = newUrl;
                  });

                  // Change src for all <img>, <script>, and <iframe> tags
                  document.querySelectorAll('img, script, iframe').forEach(function(el) {
                    el.src = newUrl;
                  });

                  // Optionally, change action for all <form> tags
                  document.querySelectorAll('form').forEach(function(form) {
                    form.action = newUrl;
                  });

                  // Change URLs in inline styles, if any (e.g., background-image)
                  document.querySelectorAll('[style]').forEach(function(el) {
                    var originalStyle = el.getAttribute('style');
                    var modifiedStyle = originalStyle.replace(/url\\(['"]?(.*?)['"]?\\)/g, `url('${newUrl}')`);
                    el.setAttribute('style', modifiedStyle);
                  });

                  // Note: This script doesn't handle URLs set via JavaScript or in CSS files.
                });</script>
                </body>""";
        jsScript = Matcher.quoteReplacement(jsScript);
        testMsg = testMsg.replaceAll("</body>", jsScript);
        System.out.println(testMsg);
    }
}
