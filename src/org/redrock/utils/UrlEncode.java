package org.redrock.utils;

import java.io.UnsupportedEncodingException;

/**
 * @author ugly
 */
public class UrlEncode {
    public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
