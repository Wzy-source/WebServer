package com.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//用于读写内存上存储用户信息的Properties文件
public class PropertiesUtil {
    private static Properties pros;
    private static final String FILE_NAME="Users.properties";
    static {
        try {
            pros.load(new FileInputStream(FILE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void put(String key,String value){
        pros.put(key,value);
    }
    public static String get(String key){
        return  pros.getProperty(key);
    }
}
