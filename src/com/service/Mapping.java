package com.service;

import java.util.HashMap;
import java.util.Map;

public class Mapping {
    private static Map<String,String> map=new HashMap<>();
    static {
        map.put("/SignUpPage","service.RegisterService");
        map.put("/","service.LoginService");
    }

    //根据URL，获取对应的服务类的名字
    public static String getMappingClassName(String name){
        if(map.containsKey(name)) {
            return map.get(name);
        }else{
            return null;
        }
    }
}

