package com.me.sensor.utils;

public class util {
    public static boolean ispal(String text){
        String inverse = new StringBuilder(text).reverse().toString();
        return text.equalsIgnoreCase(inverse);
    
            }
    }
