package com.response;

public enum HttpStateAndDesc {
    OK(200,"OK"),MOVED_PERMANENTLY(301,"Moved Permanently"),
    FOUND(302,"Found"),NOT_MODIFIED(304,"Not Modified"),
    NOT_FOUND(404,"Not Found"),METHOD_NOT_ALLOWED(405,"Method Not Allowed"),
    SERVER_ERROR(500,"Internal com.Server Error");
    private  final int state;
    private  final String desc;
    private HttpStateAndDesc(int state1, String desc1){
        this.state = state1;
        this.desc = desc1;
    }


    public int getState() {
        return state;
    }

    public String getDesc() {
        return desc;
    }
}
