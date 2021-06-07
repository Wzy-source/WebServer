package com;

public class ContentType {
    //支持三种MIME
    public static final String DEFAULT_CONTENT_TYPE="text/html";
    public static final String JSON="application/json";
    public static final String X_WWW_form_urlencoded="application/x-www-form-urlencoded";
    public static final String XML="application/xml";

    public static boolean isSupproted(String str){
        return DEFAULT_CONTENT_TYPE.equals(str)||JSON.equals(str)||X_WWW_form_urlencoded.equals(str)||XML.equals(str);
    }
}
