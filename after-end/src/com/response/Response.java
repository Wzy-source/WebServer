package com.response;

import com.Cookie;

import java.io.OutputStream;
import java.util.List;

public class Response {
    private HttpStateAndDesc httpStateAndDesc; //Http状态码和状态描述

    public Response(OutputStream outputStream) {
        //todo(hxq)
    }

    //Http状态码和描述符号的getter和setter
    public void setHttpStateAndDesc(HttpStateAndDesc httpStateAndDesc){
        this.httpStateAndDesc=httpStateAndDesc;
    }
    public HttpStateAndDesc getHttpStateAndDesc(){
        return this.httpStateAndDesc;
    }


    //ContentType以及它的getter和setter
    private String ContentType= com.ContentType.DEFAULT_CONTENT_TYPE;
    public void setContentType(String contentType){
        this.ContentType=contentType;
    }
    public String getContentType(){
        return this.ContentType;
    }


    private static final String HttpVersion="HTTP/1.1";//HTTP版本

    private List<Cookie> cookies;//Cookies
    //添加Cookie
    public void addCookie(Cookie cookie){
        cookies.add(cookie);
    }



    byte[]body=new byte[0];//存储响应体内容

    StringBuilder headBulider=new StringBuilder();//存储响应头内容






}
