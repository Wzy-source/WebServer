package com;

public class Cookie {
    //Cookie的本质是一个键值对，所以用key，value来存储键值对
    String key;
    String value;

    public Cookie(String key, String value) {
        this.key = key;
        this.value = value;
    }

    //以下为Cookie的get和set方法
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
