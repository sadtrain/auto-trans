package com.sadtrain.autotrans.api.util;

import java.util.regex.Matcher;

/**
 * Created on 2019/9/16.
 *
 */
public class InfoExtractor {
    /**
     * extract url from a string.
     *
     * @param s
     * @return
     */
    public static String extractUrl(String s) {
        Matcher matcher = Patterns.WEB_URL.matcher(s); //extract URL
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    public static String extractIp(String s) {
        Matcher matcher = Patterns.IP_ADDRESS.matcher(s); //extract IP address
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    public static String extractEmail(String s) {
        Matcher matcher = Patterns.EMAIL_ADDRESS.matcher(s);    //extract email address
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
}